package work.gaigeshen.tripartite.ding.openapi.response.api;

import work.gaigeshen.tripartite.ding.openapi.response.DingApiResponse;

/**
 *
 * @author gaigeshen
 */
public class DingAccessTokenResponse extends DingApiResponse {

    public String accessToken;

    public Long expireIn;
}
