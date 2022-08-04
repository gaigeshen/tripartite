package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.accesstoken.HisProcurementAccessTokenParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.accesstoken.HisProcurementAccessTokenResponse;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
public class DefaultHisProcurementAccessTokenRefresher implements HisProcurementAccessTokenRefresher {

  private final HisProcurementClientSelector hisProcurementClientSelector;

  public DefaultHisProcurementAccessTokenRefresher(HisProcurementClientSelector hisProcurementClientSelector) {
    if (Objects.isNull(hisProcurementClientSelector)) {
      throw new IllegalArgumentException("hisProcurementClientSelector cannot be null");
    }
    this.hisProcurementClientSelector = hisProcurementClientSelector;
  }

  @Override
  public HisProcurementAccessToken refresh(HisProcurementAccessToken oldAccessToken)
          throws HisProcurementAccessTokenRefreshException {
    HisProcurementClient client = hisProcurementClientSelector.select(oldAccessToken);
    if (Objects.isNull(client)) {
      throw new HisProcurementAccessTokenRefreshException("could not find his procurement client: " + oldAccessToken);
    }
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementAccessTokenParameters parameters = new HisProcurementAccessTokenParameters();
    parameters.appCode = config.getAppCode();
    parameters.authCode = config.getAuthCode();

    HisProcurementAccessTokenResponse response;
    try {
      response = client.execute(parameters, HisProcurementAccessTokenResponse.class, config.getAccessTokenUri());
    } catch (Exception e) {
      throw new HisProcurementAccessTokenRefreshException("could not refresh access token", e)
              .setCurrentAccessToken(oldAccessToken).setCanRetry(true);
    }
    return HisProcurementAccessToken.builder()
            .setAccessToken(response.accessToken).setAccount(config.getAccount())
            .setExpiresIn(1800).setExpiresTimestamp(System.currentTimeMillis() / 1000 + 1800)
            .setUpdateTime(new Date()).build();
  }

  /**
   *
   * @author gaigeshen
   */
  @FunctionalInterface
  public interface HisProcurementClientSelector {
    HisProcurementClient select(HisProcurementAccessToken oldAccessToken);
  }
}
