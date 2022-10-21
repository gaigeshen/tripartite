package work.gaigeshen.tripartite.core.client;

/**
 * @author gaigeshen
 */
public class ServerHostMissingException extends ServerHostException {

    public ServerHostMissingException(String message) {
        super(message);
    }

    public ServerHostMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
