package work.gaigeshen.tripartite.ding.openapi;

/**
 * @author gaigeshen
 */
public class DingClientException extends RuntimeException {

    public DingClientException(String message) {
        super(message);
    }

    public DingClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
