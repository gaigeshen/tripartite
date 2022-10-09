package work.gaigeshen.tripartite.core.client;

/**
 * @author gaigeshen
 */
public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
