package work.gaigeshen.tripartite.core.parameter.creator;

import work.gaigeshen.tripartite.core.parameter.ParametersException;

/**
 * 创建请求参数的时候发生异常
 *
 * @author gaigeshen
 */
public class ParametersCreationException extends ParametersException {
    public ParametersCreationException(String message) {
        super(message);
    }

    public ParametersCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
