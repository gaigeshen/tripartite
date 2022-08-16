package work.gaigeshen.tripartite.his.procurement.openapi;

import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessToken;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenHelper;
import work.gaigeshen.tripartite.his.procurement.openapi.accesstoken.HisProcurementAccessTokenManager;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementAccessTokenParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementAccessTokenResponse;

import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class HisProcurementClientAccessTokenInterceptor extends HisProcurementClientRequestResponseInterceptor {

  private final HisProcurementBasicClient hisProcurementClient;

  private final HisProcurementAccessTokenManager hisProcurementAccessTokenManager;

  public HisProcurementClientAccessTokenInterceptor(HisProcurementBasicClient client,
                                                    HisProcurementAccessTokenManager accessTokenManager) {
    super(client.getHisProcurementConfig());
    this.hisProcurementClient = client;
    this.hisProcurementAccessTokenManager = accessTokenManager;
  }

  @Override
  protected void updateRequest(Request request) throws InterceptingException {
    super.updateRequest(request);
    HisProcurementConfig config = hisProcurementClient.getHisProcurementConfig();
    HisProcurementAccessToken accessToken = hisProcurementAccessTokenManager.findAccessToken(config);
    Headers headers = request.headers();
    if (Objects.nonNull(accessToken) && !HisProcurementAccessTokenHelper.isExpired(accessToken)) {
      headers.putValue("Access-Token", accessToken.getAccessToken());
      return;
    }
    HisProcurementAccessTokenParameters parameters = new HisProcurementAccessTokenParameters(
            config.getAppCode(), config.getAuthCode());
    HisProcurementAccessTokenResponse response;
    try {
      response = hisProcurementClient.execute(parameters, HisProcurementAccessTokenResponse.class,
              config.getAccessTokenUri());
    } catch (Exception e) {
      throw new InterceptingException("could not get new access token", e);
    }
    HisProcurementAccessToken newAccessToken = HisProcurementAccessTokenHelper.createAccessToken(
            config, response.getAccessToken());
    hisProcurementAccessTokenManager.addNewAccessToken(config, newAccessToken);
    headers.putValue("Access-Token", newAccessToken.getAccessToken());
  }
}
