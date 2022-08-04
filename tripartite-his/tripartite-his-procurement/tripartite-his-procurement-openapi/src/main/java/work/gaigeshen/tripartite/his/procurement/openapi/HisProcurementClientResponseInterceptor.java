package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonCodec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementClientResponseInterceptor extends AbstractInterceptor {

  public static final HisProcurementClientResponseInterceptor INSTANCE = new HisProcurementClientResponseInterceptor();

  @Override
  protected void updateRequest(Request request) throws InterceptingException { }

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
