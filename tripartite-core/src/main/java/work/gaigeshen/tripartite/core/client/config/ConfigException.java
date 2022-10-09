package work.gaigeshen.tripartite.core.client.config;

import work.gaigeshen.tripartite.core.client.ClientException;

/**
 * @author gaigeshen
 */
public class ConfigException extends ClientException {

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
