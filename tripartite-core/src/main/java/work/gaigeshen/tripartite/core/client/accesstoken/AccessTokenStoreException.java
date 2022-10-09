package work.gaigeshen.tripartite.core.client.accesstoken;

/**
 * @author gaigeshen
 */
public class AccessTokenStoreException extends AccessTokenException {

    public AccessTokenStoreException(String message) {
        super(message);
    }

    public AccessTokenStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
