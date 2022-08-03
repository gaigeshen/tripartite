package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultHisProcurementAccessTokenRefresher implements HisProcurementAccessTokenRefresher {

  private final HisProcurementClient hisProcurementClient;

  public DefaultHisProcurementAccessTokenRefresher(HisProcurementClient hisProcurementClient) {
    if (Objects.isNull(hisProcurementClient)) {
      throw new IllegalArgumentException("hisProcurementClient cannot be null");
    }
    this.hisProcurementClient = hisProcurementClient;
  }

  @Override
  public HisProcurementAccessToken refresh(HisProcurementAccessToken oldAccessToken) throws HisProcurementAccessTokenRefreshException {
    HisProcurementConfig config = hisProcurementClient.getHisProcurementConfig();

    HisProcurementAccessTokenParameters parameters = new HisProcurementAccessTokenParameters();
    parameters.appCode = config.getAppCode();
    parameters.authCode = config.getAuthCode();

    HisProcurementAccessTokenResponse response;
    try {
      response = hisProcurementClient.execute(parameters, HisProcurementAccessTokenResponse.class, config.getAccessTokenUri());
    } catch (Exception e) {
      throw new HisProcurementAccessTokenRefreshException("could not refresh access token", e)
              .setCurrentAccessToken(oldAccessToken)
              .setCanRetry(true);
    }

    HisProcurementAccessToken.Builder builder = HisProcurementAccessToken.builder();
    return builder.setAccessToken(response.accessToken)
            .setAccount(config.getAccount())
            .setExpiresIn(1800).setExpiresTimestamp(System.currentTimeMillis() / 1000 + 1800)
            .setUpdateTime(new Date()).build();
  }


  @Parameters
  public static class HisProcurementAccessTokenParameters implements HisProcurementParameters {

    @Parameter
    public String appCode;

    @Parameter
    public String authCode;
  }

  public static class HisProcurementAccessTokenResponse extends AbstractHisProcurementResponse {

    public String accessToken;

  }

}
