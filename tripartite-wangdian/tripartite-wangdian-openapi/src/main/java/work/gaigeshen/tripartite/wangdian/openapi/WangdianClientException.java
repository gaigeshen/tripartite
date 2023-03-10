package work.gaigeshen.tripartite.wangdian.openapi;

/**
 * @author gaigeshen
 */
public class WangdianClientException extends RuntimeException {

    public WangdianClientException(String message) {
        super(message);
    }

    public WangdianClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
