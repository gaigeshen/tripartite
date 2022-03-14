package work.gaigeshen.triparttite.wangdian.openapi.parameters;

import org.apache.commons.codec.digest.DigestUtils;
import work.gaigeshen.triparttite.core.parameter.Parameter;
import work.gaigeshen.triparttite.core.parameter.Parameters;
import work.gaigeshen.triparttite.core.parameter.converter.ParametersCustomizer;
import work.gaigeshen.triparttite.core.parameter.converter.ParametersCustomizingException;
import work.gaigeshen.triparttite.core.util.TimestampUtils;
import work.gaigeshen.triparttite.wangdian.openapi.config.WangdianConfig;

/**
 * 旺店通请求参数对象自定义器用于添加公共请求参数
 *
 * @author gaigeshen
 */
public class WangdianParametersCustomizer implements ParametersCustomizer {

  @Override
  public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {
    if (!(config instanceof WangdianConfig)) {
      throw new ParametersCustomizingException("could not customize parameters with config: " + config);
    }
    WangdianConfig configTyped = (WangdianConfig) config;

    parameters.put("sid", configTyped.getSellerId());
    parameters.put("appkey", configTyped.getAppKey());
    parameters.put("timestamp", TimestampUtils.unixTimestamp());

    StringBuilder builder = new StringBuilder();
    for (Parameter<?> newParameter : parameters) {
      String name = newParameter.getName();
      String value = String.valueOf(newParameter.getValue());
      builder.append(";").append(String.format("%02d", name.length())).append('-').append(name);
      builder.append(':').append(String.format("%04d", value.length())).append('-').append(value);
    }
    builder.append(configTyped.getAppSecret());

    parameters.put("sign", DigestUtils.md5Hex(builder.substring(1)));
  }
}
