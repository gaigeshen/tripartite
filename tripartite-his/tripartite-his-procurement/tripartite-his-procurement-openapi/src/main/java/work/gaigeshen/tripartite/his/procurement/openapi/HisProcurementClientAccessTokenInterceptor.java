package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessToken;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenHelper;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenManager;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementAccessTokenParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementAccessTokenResponse;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementClientAccessTokenInterceptor extends HisProcurementClientRequestResponseInterceptor {

  private final HisProcurementClient hisProcurementClient;

  private final HisProcurementAccessTokenManager hisProcurementAccessTokenManager;

  public HisProcurementClientAccessTokenInterceptor(HisProcurementClient client,
                                                    HisProcurementAccessTokenManager accessTokenManager) {
    super(client.getHisProcurementConfig());
    this.hisProcurementClient = client;
    this.hisProcurementAccessTokenManager = accessTokenManager;
  }

  @Override
  protected void updateRequest(Request request) throws InterceptingException {
    HisProcurementConfig config = hisProcurementClient.getHisProcurementConfig();
    HisProcurementAccessToken accessToken = hisProcurementAccessTokenManager.findAccessToken(config.getAccount());
    if (Objects.nonNull(accessToken) && !HisProcurementAccessTokenHelper.isExpired(accessToken)) {
      request.headers().putValue("Access-Token", accessToken.getAccessToken());
      return;
    }
    HisProcurementAccessTokenParameters parameters = new HisProcurementAccessTokenParameters();
    parameters.appCode = config.getAppCode();
    parameters.authCode = config.getAuthCode();
    HisProcurementAccessTokenResponse response;
    try {
      response = hisProcurementClient.execute(parameters, HisProcurementAccessTokenResponse.class,
              config.getAccessTokenUri());
    } catch (Exception e) {
      throw new InterceptingException("could not get new access token", e);
    }
    HisProcurementAccessToken newAccessToken = HisProcurementAccessToken.builder()
            .setAccessToken(response.accessToken).setAccount(config.getAccount())
            .setExpiresIn(1800).setExpiresTimestamp(System.currentTimeMillis() / 1000 + 1800)
            .setUpdateTime(new Date()).build();
    hisProcurementAccessTokenManager.addNewAccessToken(newAccessToken);
    request.headers().putValue("Access-Token", newAccessToken.getAccessToken());
  }
}
