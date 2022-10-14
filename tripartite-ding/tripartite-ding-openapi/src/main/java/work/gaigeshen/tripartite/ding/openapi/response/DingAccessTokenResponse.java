package work.gaigeshen.tripartite.ding.openapi.response;

import work.gaigeshen.tripartite.core.client.response.ClientResponse;

/**
 *
 * @author gaigeshen
 */
public class DingAccessTokenResponse implements ClientResponse {

    private String accessToken;

    private Long expireIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }
}
