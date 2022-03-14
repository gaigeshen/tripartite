package work.gaigeshen.triparttite.pay.alipay.parameters;

/**
 * 默认的支付宝请求参数构建器
 *
 * @author gaigeshen
 */
public class DefaultAlipayParametersBuilder extends AlipayParametersBuilder {
  /**
   * 创建支付宝请求参数构建器
   *
   * @param parameters 支付宝业务参数不能为空
   * @param apiMethod  支付宝业务参数所对应的接口名称不能为空
   */
  public DefaultAlipayParametersBuilder(AlipayParameters parameters, String apiMethod) {
    super(parameters, apiMethod);
  }
}
