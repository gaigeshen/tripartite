package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;
import work.gaigeshen.tripartite.ding.openapi.parameters.DingParameters;
import work.gaigeshen.tripartite.ding.openapi.response.DingResponse;

/**
 *
 * @author gaigeshen
 */
public interface DingClient {

    DingConfig getDingConfig();

    <R extends DingResponse> R execute(DingParameters parameters, Class<R> responseClass, String uri) throws DingClientException;
}
