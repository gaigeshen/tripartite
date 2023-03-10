package work.gaigeshen.tripartite.wangdian.openapi.config;

import work.gaigeshen.tripartite.wangdian.openapi.WangdianClientException;

/**
 * @author gaigeshen
 */
public class WangdianConfigException extends WangdianClientException {

    public WangdianConfigException(String message) {
        super(message);
    }

    public WangdianConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
