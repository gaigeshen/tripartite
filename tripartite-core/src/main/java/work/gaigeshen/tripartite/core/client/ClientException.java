package work.gaigeshen.tripartite.core.client;

/**
 * 接口客户端异常同时也是顶级异常
 *
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
