package work.gaigeshen.triparttite.core.parameter.typed.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author gaigeshen
 */
public class GsonJsonParameterConverter extends AbstractJsonParameterConverter {

  public static final GsonJsonParameterConverter INSTANCE = new GsonJsonParameterConverter();

  @Override
  protected String convertJson(Object rawParameter) throws ParameterConversionException {
    try {
      return GsonHolder.INSTANCE.toJson(rawParameter);
    } catch (Exception e) {
      throw new ParameterConversionException("could not convert to json: " + rawParameter, e);
    }
  }

  /**
   *
   * @author gaigeshen
   */
  private static class GsonHolder {

    public static final Gson INSTANCE = new GsonBuilder().create();

  }
}
