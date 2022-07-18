package work.gaigeshen.tripartite.doudian.openapi;

/**
 * @author gaigeshen
 */
public class DoudianClientException extends RuntimeException {
  public DoudianClientException(String message) {
    super(message);
  }
  public DoudianClientException(String message, Throwable cause) {
    super(message, cause);
  }
}
