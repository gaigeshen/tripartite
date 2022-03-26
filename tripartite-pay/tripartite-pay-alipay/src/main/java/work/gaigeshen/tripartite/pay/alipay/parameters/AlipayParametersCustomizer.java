package work.gaigeshen.tripartite.pay.alipay.parameters;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizer;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizingException;
import work.gaigeshen.tripartite.core.util.TimestampUtils;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayConfig;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayPrivateKey;

/**
 * 支付宝请求参数对象自定义器用于添加公共请求参数
 *
 * @author gaigeshen
 */
public class AlipayParametersCustomizer implements ParametersCustomizer {

  @Override
  public void beforeConvert(Object rawParameters, Object config) throws ParametersCustomizingException {
  }

  @Override
  public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {
    AlipayConfig alipayConfig = (AlipayConfig) config;
    AlipayPrivateKey privateKey = alipayConfig.getPrivateKey();

    parameters.put("format", "json");
    parameters.put("charset", "utf-8");
    parameters.put("version", "1.0");
    parameters.put("sign_type", "RSA2");

    parameters.put("app_id", alipayConfig.getAppId());
    parameters.put("notify_url", alipayConfig.getNotifyUrl());
    parameters.put("app_cert_sn", privateKey.getCertSerialNumber());
    parameters.put("alipay_root_cert_sn", alipayConfig.getRootCertificate().getSerialNumber());

    parameters.put("timestamp", TimestampUtils.format("yyyy-MM-dd HH:mm:ss"));

    parameters.put("sign", privateKey.sign(parameters.join("&")));
  }

  @Override
  public boolean supports(Object config) {
    return config instanceof AlipayConfig;
  }
}
