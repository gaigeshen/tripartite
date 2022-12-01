package work.gaigeshen.tripartite.ding.openapi.response.api;

import work.gaigeshen.tripartite.ding.openapi.response.DingApiResponse;

/**
 *
 * @author gaigeshen
 */
public class DingAuthCorpAccessTokenResponse extends DingApiResponse {

    public String accessToken;

    public Long expireIn;
}
