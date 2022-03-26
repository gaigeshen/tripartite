package work.gaigeshen.tripartite.ding.openapi.response.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.response.DingResponse;

/**
 *
 * @author gaigeshen
 */
public class DingAccessTokenResponse implements DingResponse {

  public String accessToken;

  public Long expireIn;

}
