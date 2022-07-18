package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

/**
 * @author gaigeshen
 */
public class DoudianInvalidAccessTokenException extends DoudianAccessTokenException {
  public DoudianInvalidAccessTokenException(String message) {
    super(message);
  }
  public DoudianInvalidAccessTokenException(String message, Throwable cause) {
    super(message, cause);
  }
}
