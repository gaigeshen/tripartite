package work.gaigeshen.tripartite.his.procurement.openapi;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class HisProcurementClientRequestResponseInterceptor extends AbstractInterceptor {

    private final HisProcurementConfig hisProcurementConfig;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public HisProcurementClientRequestResponseInterceptor(HisProcurementConfig hisProcurementConfig) {
        if (Objects.isNull(hisProcurementConfig)) {
            throw new IllegalArgumentException("his procurement config cannot be null");
        }
        this.hisProcurementConfig = hisProcurementConfig;
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        long timestamp = System.currentTimeMillis() / 1000;
        String bodyContent = new String(request.bodyBytes(), StandardCharsets.UTF_8);
        String account = hisProcurementConfig.getAccount();
        byte[] secretBytes = hisProcurementConfig.getSecret().getBytes(StandardCharsets.UTF_8);
        try {
            SecretKey secretKey = new SecretKeySpec(secretBytes, "HmacSm3");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            mac.update((timestamp + "\n").getBytes(StandardCharsets.UTF_8));
            mac.update(bodyContent.getBytes(StandardCharsets.UTF_8));
            byte[] digest = mac.doFinal();
            String digestResult = Hex.toHexString(digest).toLowerCase();
            Headers headers = request.headers();
            headers.putValue("x-ca-key", account);
            headers.putValue("x-ca-signature", timestamp + ":" + digestResult);
        } catch (Exception e) {
            throw new InterceptingException("update request signature error", e);
        }
    }

    @Override
    protected void validateResponse(Request request, Response response) throws InterceptingException {
        String rawResponse;
        try {
            rawResponse = response.bodyString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InterceptingException("could not read raw response", e);
        }
        Map<String, Object> decodedResponse = JsonCodec.instance().decodeObject(rawResponse);
        String infcode = (String) decodedResponse.get("infcode");
        if (!Objects.equals("0", infcode)) {
            throw new InterceptingException(rawResponse);
        }
        Map<?, ?> output = (Map<?, ?>) decodedResponse.get("output");
        if (Objects.isNull(output) || !output.containsKey("data")) {
            throw new InterceptingException("response output or output data not found: " + rawResponse);
        }
        response.buffered(JsonCodec.instance().encode(output.get("data")).getBytes(StandardCharsets.UTF_8));
    }
}
