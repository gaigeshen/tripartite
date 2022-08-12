package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.his.procurement.openapi.HisProcurementClient;
import work.gaigeshen.tripartite.his.procurement.openapi.config.HisProcurementConfig;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementAccessTokenParameters;
import work.gaigeshen.tripartite.his.procurement.openapi.response.HisProcurementAccessTokenResponse;

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
    HisProcurementClient client;
    try {
      client = hisProcurementClientSelector.select(oldAccessToken);
    } catch (Exception e) {
      throw new HisProcurementAccessTokenRefreshException("could not find his procurement client: " + oldAccessToken);
    }
    HisProcurementConfig config = client.getHisProcurementConfig();

    HisProcurementAccessTokenParameters parameters = new HisProcurementAccessTokenParameters(
            config.getAppCode(), config.getAuthCode());

    HisProcurementAccessTokenResponse response;
    try {
      response = client.execute(parameters, HisProcurementAccessTokenResponse.class, config.getAccessTokenUri());
    } catch (Exception e) {
      throw new HisProcurementAccessTokenRefreshException("could not refresh access token", e)
              .setCurrentAccessToken(oldAccessToken).setCanRetry(true);
    }
    return HisProcurementAccessTokenHelper.createAccessToken(config, response.getAccessToken());
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
