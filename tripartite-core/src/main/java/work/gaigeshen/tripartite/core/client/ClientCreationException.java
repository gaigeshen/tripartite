package work.gaigeshen.tripartite.core.client;

/**
 * @author gaigeshen
 */
public class ClientCreationException extends ClientException {

    public ClientCreationException(String message) {
        super(message);
    }

    public ClientCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
