package work.gaigeshen.tripartite.ding.openapi.accesstoken;

import work.gaigeshen.tripartite.ding.openapi.DingClientException;

/**
 *
 * @author gaigeshen
 */
public class DingAccessTokenException extends DingClientException {
  public DingAccessTokenException(String message) {
    super(message);
  }

  public DingAccessTokenException(String message, Throwable cause) {
    super(message, cause);
  }
}
