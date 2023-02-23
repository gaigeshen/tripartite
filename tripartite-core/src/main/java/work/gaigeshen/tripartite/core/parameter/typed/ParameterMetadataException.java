package work.gaigeshen.tripartite.core.parameter.typed;

import work.gaigeshen.tripartite.core.parameter.ParametersException;

/**
 * @author gaigeshen
 */
public class ParameterMetadataException extends ParametersException {
    public ParameterMetadataException(String message) {
        super(message);
    }

    public ParameterMetadataException(String message, Throwable cause) {
        super(message, cause);
    }
}
