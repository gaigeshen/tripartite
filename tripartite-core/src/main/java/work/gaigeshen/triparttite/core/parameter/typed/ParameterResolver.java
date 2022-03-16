package work.gaigeshen.triparttite.core.parameter.typed;

import work.gaigeshen.triparttite.core.parameter.Parameters;
import work.gaigeshen.triparttite.core.parameter.typed.converter.BooleanFormatterParameterConverter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.DateFormatterParameterConverter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.DefaultParameterConverter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.DefaultJsonParameterConverter;
import work.gaigeshen.triparttite.core.parameter.typed.converter.ParameterConverter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 单个请求参数解析器
 *
 * @author gaigeshen
 */
public class ParameterResolver {

  /**
   * 记录所有访问过的参数相关联的字段及其元数据配置
   */
  private static final Map<Field, Metadata> FIELD_MAPPING_METADATA = new HashMap<>();

  /**
   * 解析单个请求参数并且添加到指定的请求参数内，忽略参数值为空的字段
   *
   * @param parameters 指定的请求参数不能为空
   * @param field 单个请求参数相关联的字段不能为空
   * @param target 该字段所在的对象实例不能为空
   * @throws IllegalAccessException 可能在读取参数值的时候发生异常
   */
  public static void addParameter(Parameters parameters, Field field, Object target) throws IllegalAccessException {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("parameters cannot be null");
    }
    if (Objects.isNull(field)) {
      throw new IllegalArgumentException("field cannot be null");
    }
    if (Objects.isNull(target)) {
      throw new IllegalArgumentException("target object cannot be null");
    }
    Object value = field.get(target);
    if (Objects.isNull(value)) {
      return;
    }
    Metadata metadata = getMetadata(field);
    parameters.put(metadata.getConverter().convert(metadata.getName(), value));
  }

  /**
   * 获取某个字段上配置的元数据配置
   *
   * @param field 字段不能为空
   * @return 该字段配置的元数据配置
   * @throws ParameterMetadataException 无法获取该字段上配置的元数据配置
   */
  public static Metadata getMetadata(Field field) throws ParameterMetadataException {
    if (Objects.isNull(field)) {
      throw new IllegalArgumentException("field cannot be null");
    }
    return FIELD_MAPPING_METADATA.computeIfAbsent(field, f -> {
      f.setAccessible(true);
      Parameter metadata = f.getAnnotation(Parameter.class);
      if (Objects.isNull(metadata)) {
        return new Metadata(f.getName(), DefaultParameterConverter.INSTANCE);
      }
      String name = metadata.name().trim();
      if (Objects.equals(name, "")) {
        name = f.getName();
      }
      ParameterConverter converter;
      if (Objects.equals(metadata.converter(), DefaultParameterConverter.class)) {
        converter = DefaultParameterConverter.INSTANCE;
      }
      else if (Objects.equals(metadata.converter(), BooleanFormatterParameterConverter.class)) {
        converter = BooleanFormatterParameterConverter.INSTANCE;
      }
      else if (Objects.equals(metadata.converter(), DateFormatterParameterConverter.class)) {
        converter = DateFormatterParameterConverter.INSTANCE;
      }
      else if (Objects.equals(metadata.converter(), DefaultJsonParameterConverter.class)) {
        converter = DefaultJsonParameterConverter.INSTANCE;
      }
      else {
        try {
          Constructor<? extends ParameterConverter> constructor = metadata.converter().getConstructor();
          converter = constructor.newInstance();
        } catch (ReflectiveOperationException e) {
          throw new ParameterMetadataException("could not resolve parameter metadata: " + f, e);
        }
      }
      return new Metadata(name, converter);
    });
  }

  /**
   * 单个请求参数配置元数据
   *
   * @author gaigeshen
   * @see Parameter
   */
  public static class Metadata {

    private final String name;

    private final ParameterConverter converter;

    private Metadata(String name, ParameterConverter converter) {
      if (Objects.isNull(name) || Objects.isNull(converter)) {
        throw new IllegalArgumentException("name and converter cannot be null");
      }
      this.name = name;
      this.converter = converter;
    }

    public String getName() {
      return name;
    }

    public ParameterConverter getConverter() {
      return converter;
    }
  }
}
