package work.gaigeshen.triparttite.ding.openapi;

/**
 *
 * @author gaigeshen
 */
public class DingClientNotFoundException extends DingClientException {
  public DingClientNotFoundException(String message) {
    super(message);
  }

  public DingClientNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
