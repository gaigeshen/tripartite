package work.gaigeshen.tripartite.core.client.accesstoken;

import work.gaigeshen.tripartite.core.client.ClientException;

/**
 * @author gaigeshen
 */
public class AccessTokenException extends ClientException {

    public AccessTokenException(String message) {
        super(message);
    }

    public AccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
