package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

import work.gaigeshen.tripartite.doudian.openapi.DoudianClientException;

/**
 * @author gaigeshen
 */
public class DoudianAccessTokenException extends DoudianClientException {
  public DoudianAccessTokenException(String message) {
    super(message);
  }
  public DoudianAccessTokenException(String message, Throwable cause) {
    super(message, cause);
  }
}
