package work.gaigeshen.tripartite.core.response;

import work.gaigeshen.tripartite.core.WebException;

/**
 * 有关响应结果的异常
 *
 * @author gaigeshen
 */
public class ResponseException extends WebException {
    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
