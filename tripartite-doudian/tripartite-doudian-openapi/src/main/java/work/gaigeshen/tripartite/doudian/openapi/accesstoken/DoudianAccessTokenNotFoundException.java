package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

/**
 * @author gaigeshen
 */
public class DoudianAccessTokenNotFoundException extends DoudianAccessTokenException {
  public DoudianAccessTokenNotFoundException(String message) {
    super(message);
  }
  public DoudianAccessTokenNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
