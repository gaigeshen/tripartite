package work.gaigeshen.tripartite.ding.openapi.client.interceptor;

import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * 用于校验钉钉响应内容
 *
 * @author gaigeshen
 */
public class DingResponseValidationInterceptor extends AbstractInterceptor {

    public static DingResponseValidationInterceptor INSTANCE = new DingResponseValidationInterceptor();

    @Override
    protected void updateRequest(Request request) throws InterceptingException {}

    @Override
    protected void validateResponse(Request request, Response response) throws InterceptingException {
        String rawResponse;
        try {
            rawResponse = response.bodyString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InterceptingException("could not read raw response", e);
        }
        Map<String, Object> decodedResponse = JsonCodec.instance().decodeObject(rawResponse);
        Number errcode = (Number) decodedResponse.get("errcode");
        if (Objects.nonNull(errcode) && !Objects.equals(errcode.intValue(), 0)) {
            throw new InterceptingException("[ " + errcode + " ] [ " + decodedResponse.get("errmsg") + " ]");
        }
        response.buffered(rawResponse.getBytes(StandardCharsets.UTF_8));
    }
}
