package work.gaigeshen.tripartite.qyweixin.openapi.response.api;

import work.gaigeshen.tripartite.qyweixin.openapi.response.QyWeixinApiResponse;

/**
 *
 * @author gaigeshen
 */
public class QyWeixinAccessTokenResponse extends QyWeixinApiResponse {

    public String access_token;

    public Long expires_in;
}
