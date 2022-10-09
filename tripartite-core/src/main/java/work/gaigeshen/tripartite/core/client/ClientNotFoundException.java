package work.gaigeshen.tripartite.core.client;

/**
 *
 * @author gaigeshen
 */
public class ClientNotFoundException extends ClientException {

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
