package work.gaigeshen.tripartite.doudian.openapi.config;

/**
 *
 * @author gaigeshen
 */
public class DoudianConfig {

  private final String appKey;

  private final String appSecret;

  public DoudianConfig(String appKey, String appSecret) {
    this.appKey = appKey;
    this.appSecret = appSecret;
  }

  public String getAppKey() {
    return appKey;
  }

  public String getAppSecret() {
    return appSecret;
  }
}
