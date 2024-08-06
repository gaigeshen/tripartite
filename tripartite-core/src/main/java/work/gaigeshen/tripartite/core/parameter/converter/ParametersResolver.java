package work.gaigeshen.tripartite.core.parameter.converter;

import lombok.Getter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 请求参数解析器
 *
 * @author gaigeshen
 */
public class ParametersResolver {

    private static final Map<Class<?>, Metadata> CLASS_MAPPING_METADATA = new HashMap<>();

    /**
     * 获取请求参数元数据
     *
     * @param parametersClass 请求参数类型不能为空
     * @return 该请求参数的元数据
     * @throws ParametersMetadataException 无法获取请求参数元数据
     */
    public static Metadata getMetadata(Class<?> parametersClass) throws ParametersMetadataException {
        ArgumentValidate.notNull(parametersClass, "parameters class cannot be null");
        return CLASS_MAPPING_METADATA.computeIfAbsent(parametersClass, cls -> {
            Parameters metadata = cls.getAnnotation(Parameters.class);
            if (Objects.isNull(metadata)) {
                return new Metadata(ParametersParametersConverter.INSTANCE, DefaultParametersCustomizer.INSTANCE);
            }
            Class<? extends ParametersConverter> converter = metadata.converter();
            if (ParametersMetadataParametersConverter.class.isAssignableFrom(converter)) {
                throw new ParametersMetadataException("converter is invalid: " + cls);
            }
            ParametersConverter converterInstance;
            if (Objects.equals(converter, JsonParametersConverter.class)) {
                converterInstance = JsonParametersConverter.INSTANCE;
            }
            else if (Objects.equals(converter, ParametersParametersConverter.class)) {
                converterInstance = ParametersParametersConverter.INSTANCE;
            }
            else if (Objects.equals(converter, MultipartParametersParametersConverter.class)) {
                converterInstance = MultipartParametersParametersConverter.INSTANCE;
            }
            else {
                try {
                    converterInstance = converter.getConstructor().newInstance();
                } catch (ReflectiveOperationException e) {
                    throw new ParametersMetadataException("could not resolve parameters metadata: " + cls, e);
                }
            }
            Class<? extends ParametersCustomizer> customizer = metadata.customizer();
            ParametersCustomizer customizerInstance;
            if (Objects.equals(customizer, DefaultParametersCustomizer.class)) {
                customizerInstance = DefaultParametersCustomizer.INSTANCE;
            }
            else {
                try {
                    customizerInstance = customizer.getConstructor().newInstance();
                } catch (ReflectiveOperationException e) {
                    throw new ParametersMetadataException("could not resolve parameters metadata: " + cls, e);
                }
            }
            return new Metadata(converterInstance, customizerInstance);
        });
    }

    /**
     * 标识请求参数上配置的元数据
     *
     * @author gaigeshen
     * @see Parameters
     */
    @Getter
    public static class Metadata {

        private final ParametersConverter converter;

        private final ParametersCustomizer customizer;

        public Metadata(ParametersConverter converter, ParametersCustomizer customizer) {
            ArgumentValidate.notTrue(Objects.isNull(converter) || Objects.isNull(customizer),
                    "converter and customizer cannot be null");
            this.converter = converter;
            this.customizer = customizer;
        }
    }
}
