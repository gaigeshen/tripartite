package work.gaigeshen.tripartite.core.client;

/**
 * @author gaigeshen
 */
public class ServerHostException extends ClientException {

    public ServerHostException(String message) {
        super(message);
    }

    public ServerHostException(String message, Throwable cause) {
        super(message, cause);
    }
}
