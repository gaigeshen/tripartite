package work.gaigeshen.triparttite.ding.openapi.accesstoken;

import work.gaigeshen.triparttite.ding.openapi.DingClientException;

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
