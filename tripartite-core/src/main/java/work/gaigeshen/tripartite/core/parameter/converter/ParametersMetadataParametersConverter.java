package work.gaigeshen.tripartite.core.parameter.converter;

import work.gaigeshen.tripartite.core.parameter.Parameters;

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

        boolean supports = customizer.supports(config);
        if (supports) {
            customizer.beforeConvert(parameters, config);
        }
        Parameters converted = converter.convert(parameters);
        if (supports) {
            customizer.customize(converted, parameters, config);
        }
        return converted;
    }

}
