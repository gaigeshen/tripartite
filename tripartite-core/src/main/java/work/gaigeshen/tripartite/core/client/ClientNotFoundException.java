package work.gaigeshen.tripartite.core.client;

/**
 * 接口客户端未找到的异常
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
