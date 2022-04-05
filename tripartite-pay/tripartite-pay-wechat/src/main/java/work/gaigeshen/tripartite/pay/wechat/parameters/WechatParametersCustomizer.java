package work.gaigeshen.tripartite.pay.wechat.parameters;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizer;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizingException;
import work.gaigeshen.tripartite.pay.wechat.config.WechatConfig;
import work.gaigeshen.tripartite.pay.wechat.parameters.basic.*;

/**
 * 微信支付请求参数对象自定义器用于添加公共请求参数
 *
 * @author gaigeshen
 */
public class WechatParametersCustomizer implements ParametersCustomizer {

  @Override
  public void beforeConvert(Object rawParameters, Object config) throws ParametersCustomizingException {

  }

  @Override
  public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {
    WechatConfig wechatConfig = (WechatConfig) config;
    // 各种下单方式设置公共参数含回调通知地址
    if (rawParameters instanceof WechatAppOrderParameters
            || rawParameters instanceof WechatH5OrderParameters
            || rawParameters instanceof WechatJsapiOrderParameters
            || rawParameters instanceof WechatNativeOrderParameters) {
      parameters.put("appid", wechatConfig.getAppId());
      parameters.put("mchid", wechatConfig.getMerchantId());
      parameters.put("notify_url", wechatConfig.getNotifyUrl());
    }
    // 申请退款自定义回调通知地址
    if (rawParameters instanceof WechatRefundOrderParameters) {
      parameters.put("notify_url", wechatConfig.getNotifyUrl());
    }
  }

  @Override
  public boolean supports(Object config) {
    return config instanceof WechatConfig;
  }
}
