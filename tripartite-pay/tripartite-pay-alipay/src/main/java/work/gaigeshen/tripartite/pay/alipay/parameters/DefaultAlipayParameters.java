package work.gaigeshen.tripartite.pay.alipay.parameters;

import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.parameter.typed.converter.DefaultJsonParameterConverter;

import java.util.Objects;

/**
 * 默认的支付宝请求参数
 *
 * @author gaigeshen
 */
@Parameters(
        customizer = AlipayParametersCustomizer.class
)
public class DefaultAlipayParameters implements AlipayParameters {

  @Parameter(
          name = "method"
  )
  private final String apiMethod;

  @Parameter(
          name = "biz_content",
          converter = DefaultJsonParameterConverter.class
  )
  private final AlipayContentParameter contentParameter;

  /**
   * 创建默认的支付宝请求参数
   *
   * @param apiMethod 这是支付宝请求参数中的接口名称部分不能为空
   * @param contentParameter 这是支付宝请求参数中的业务参数部分不能为空
   */
  public DefaultAlipayParameters(String apiMethod, AlipayContentParameter contentParameter) {
    if (Objects.isNull(apiMethod)) {
      throw new IllegalArgumentException("api method cannot be null");
    }
    if (Objects.isNull(contentParameter)) {
      throw new IllegalArgumentException("alipay content parameter cannot be null");
    }
    this.apiMethod = apiMethod;
    this.contentParameter = contentParameter;
  }

  public String getApiMethod() {
    return apiMethod;
  }

  public AlipayContentParameter getContentParameter() {
    return contentParameter;
  }
}
