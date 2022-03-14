package work.gaigeshen.triparttite.core;

/**
 *
 * @author gaigeshen
 */
public class WebException extends RuntimeException {
  public WebException(String message) {
    super(message);
  }
  public WebException(String message, Throwable cause) {
    super(message, cause);
  }
}
