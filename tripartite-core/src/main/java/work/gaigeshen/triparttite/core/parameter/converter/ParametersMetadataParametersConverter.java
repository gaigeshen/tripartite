package work.gaigeshen.triparttite.core.parameter.converter;

import work.gaigeshen.triparttite.core.parameter.Parameters;

import java.util.Objects;

/**
 * 此请求参数转换器基于请求参数元数据配置
 *
 * @author gaigeshen
 */
public class ParametersMetadataParametersConverter implements ParametersConverter {

  private final Object config;

  public ParametersMetadataParametersConverter(Object config) {
    this.config = config;
  }

  @Override
  public Parameters convert(Object parameters) throws ParametersConversionException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    ParametersResolver.Metadata metadata = ParametersResolver.getMetadata(parameters.getClass());
    ParametersConverter converter = metadata.getConverter();
    ParametersCustomizer customizer = metadata.getCustomizer();

    Parameters converted = converter.convert(parameters);
    customizer.customize(converted, parameters, config);
    return converted;
  }

}
