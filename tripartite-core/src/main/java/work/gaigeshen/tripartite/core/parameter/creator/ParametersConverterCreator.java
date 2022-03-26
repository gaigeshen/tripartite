package work.gaigeshen.tripartite.core.parameter.creator;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.MultipartParametersParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersConverter;
import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersParametersConverter;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 此请求参数创建器依赖另外的请求参数转换器，可以使用此类的实用静态方法快速创建此类的实例，并且允许修改该转换器转换后的请求参数
 *
 * @author gaigeshen
 */
public class ParametersConverterCreator implements ParametersCreator {

  private final ParametersConverter converter;

  private final Object rawParameters;

  /**
   * 创建此请求参数创建器
   *
   * @param converter 请求参数转换器不能为空
   * @param rawParameters 原始的请求参数对象不能为空
   */
  public ParametersConverterCreator(ParametersConverter converter, Object rawParameters) {
    if (Objects.isNull(converter) || Objects.isNull(rawParameters)) {
      throw new IllegalArgumentException("parameters converter and raw parameters object cannot be null");
    }
    this.converter = converter;
    this.rawParameters = rawParameters;
  }

  /**
   * 使用特定的请求参数转换器创建此请求参数创建器
   *
   * @param rawParameters 原始的请求参数对象不能为空
   * @param customizer 请求参数自定义方法用于自定义转换后的请求参数，比如可以向其中添加额外的参数，此参数不能为空
   * @return 请求参数创建器对象实例
   */
  public static ParametersConverterCreator jsonConverter(Object rawParameters, Consumer<Parameters> customizer) {
    if (Objects.isNull(rawParameters) || Objects.isNull(customizer)) {
      throw new IllegalArgumentException("raw parameters object and parameters customizer cannot be null");
    }
    return new ParametersConverterCreator(JsonParametersConverter.INSTANCE, rawParameters) {
      @Override
      protected void overrideParameters(Parameters parameters) {
        customizer.accept(parameters);
      }
    };
  }

  /**
   * 使用特定的请求参数转换器创建此请求参数创建器
   *
   * @param rawParameters 原始的请求参数对象不能为空
   * @param customizer 请求参数自定义方法用于自定义转换后的请求参数，比如可以向其中添加额外的参数，此参数不能为空
   * @return 请求参数创建器对象实例
   */
  public static ParametersConverterCreator parametersConverter(Object rawParameters, Consumer<Parameters> customizer) {
    if (Objects.isNull(rawParameters) || Objects.isNull(customizer)) {
      throw new IllegalArgumentException("raw parameters object and parameters customizer cannot be null");
    }
    return new ParametersConverterCreator(ParametersParametersConverter.INSTANCE, rawParameters) {
      @Override
      protected void overrideParameters(Parameters parameters) {
        customizer.accept(parameters);
      }
    };
  }

  /**
   * 使用特定的请求参数转换器创建此请求参数创建器
   *
   * @param rawParameters 原始的请求参数对象不能为空
   * @param customizer 请求参数自定义方法用于自定义转换后的请求参数，比如可以向其中添加额外的参数，此参数不能为空
   * @return 请求参数创建器对象实例
   */
  public static ParametersConverterCreator multipartParametersConverter(Object rawParameters, Consumer<Parameters> customizer) {
    if (Objects.isNull(rawParameters) || Objects.isNull(customizer)) {
      throw new IllegalArgumentException("raw parameters object and parameters customizer cannot be null");
    }
    return new ParametersConverterCreator(MultipartParametersParametersConverter.INSTANCE, rawParameters) {
      @Override
      protected void overrideParameters(Parameters parameters) {
        customizer.accept(parameters);
      }
    };
  }

  @Override
  public Parameters create() throws ParametersCreationException {

    Parameters parameters = converter.convert(rawParameters);

    overrideParameters(parameters);

    return parameters;
  }

  /**
   * 此方法在转换器转换完毕请求参数之后被调用
   *
   * @param parameters 转换的请求参数不为空
   */
  protected void overrideParameters(Parameters parameters) { }

}
