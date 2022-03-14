package work.gaigeshen.triparttite.ding.openapi.config;

import work.gaigeshen.triparttite.ding.openapi.DingClientException;

/**
 *
 * @author gaigeshen
 */
public class DingConfigException extends DingClientException {

  public DingConfigException(String message) {
    super(message);
  }

  public DingConfigException(String message, Throwable cause) {
    super(message, cause);
  }
}
