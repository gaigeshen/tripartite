package work.gaigeshen.tripartite.qyweixin.openapi.response.accesstoken;

import work.gaigeshen.tripartite.qyweixin.openapi.response.QyWeixinResponse;

/**
 *
 * @author gaigeshen
 */
public class QyWeixinProviderAccessTokenResponse extends QyWeixinResponse {

    public String provider_access_token;

    public Long expires_in;
}
