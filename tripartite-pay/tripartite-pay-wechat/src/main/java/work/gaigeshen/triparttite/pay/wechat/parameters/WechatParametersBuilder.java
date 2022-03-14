package work.gaigeshen.triparttite.pay.wechat.parameters;

import work.gaigeshen.triparttite.core.parameter.Parameters;
import work.gaigeshen.triparttite.core.parameter.converter.ParametersConverter;
import work.gaigeshen.triparttite.pay.wechat.config.WechatConfig;

import java.util.Objects;

/**
 * 微信支付业务参数构建请求参数
 *
 * @author gaigeshen
 */
public abstract class WechatParametersBuilder {

  private final WechatParameters parameters;

  /**
   * 创建微信支付请求参数构建器
   *
   * @param parameters 微信支付业务参数不能为空
   */
  public WechatParametersBuilder(WechatParameters parameters) {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("wechat parameters cannot be null");
    }
    this.parameters = parameters;
  }

  /**
   * 构建请求参数方法
   *
   * @param config 接受微信配置对象不会为空
   * @return 请求参数不为空
   */
  public Parameters build(WechatConfig config) {
    Parameters newParameters = ParametersConverter.convertJson(parameters);
    overrideParameters(newParameters, config);
    return newParameters;
  }

  /**
   * 此方法用于修改已经构建的请求参数，比如可以添加额外的请求参数
   *
   * @param parameters 请求参数不为空
   * @param config 微信配置对象不会为空
   */
  protected void overrideParameters(Parameters parameters, WechatConfig config) {

  }
}
