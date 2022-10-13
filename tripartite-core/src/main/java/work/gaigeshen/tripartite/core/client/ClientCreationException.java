package work.gaigeshen.tripartite.core.client;

/**
 * 客户端创建的时候发生异常
 *
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
