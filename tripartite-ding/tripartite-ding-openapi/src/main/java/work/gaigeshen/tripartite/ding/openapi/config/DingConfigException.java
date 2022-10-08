package work.gaigeshen.tripartite.ding.openapi.config;

import work.gaigeshen.tripartite.ding.openapi.DingClientException;

/**
 * @author gaigeshen
 */
public class DingConfigException extends DingClientException {

    public DingConfigException(String message) {
        super(message);
    }

    public DingConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
