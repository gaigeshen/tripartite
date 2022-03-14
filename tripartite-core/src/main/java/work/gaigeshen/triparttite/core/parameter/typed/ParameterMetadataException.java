package work.gaigeshen.triparttite.core.parameter.typed;

import work.gaigeshen.triparttite.core.parameter.ParametersException;

/**
 *
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
