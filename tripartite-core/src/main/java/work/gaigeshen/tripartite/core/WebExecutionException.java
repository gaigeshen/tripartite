package work.gaigeshen.tripartite.core;

/**
 * @author gaigeshen
 */
public class WebExecutionException extends WebException {
    public WebExecutionException(String message) {
        super(message);
    }

    public WebExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
