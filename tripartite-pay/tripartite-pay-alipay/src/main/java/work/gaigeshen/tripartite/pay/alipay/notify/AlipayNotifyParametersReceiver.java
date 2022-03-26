package work.gaigeshen.tripartite.pay.alipay.notify;

import work.gaigeshen.tripartite.core.notify.AbstractNotifyContentReceiver;
import work.gaigeshen.tripartite.core.notify.NotifyContentIncorrectException;
import work.gaigeshen.tripartite.core.notify.NotifyParameters;
import work.gaigeshen.tripartite.pay.alipay.config.AlipayCertificates;
import work.gaigeshen.tripartite.pay.alipay.AlipayClients;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static work.gaigeshen.tripartite.pay.alipay.notify.AlipayNotifyParameters.*;

/**
 * 支付宝异步通知参数接收器
 *
 * @author gaigeshen
 */
public class AlipayNotifyParametersReceiver extends AbstractNotifyContentReceiver<AlipayNotifyParameters> {

  private final AlipayClients alipayClients;

  public AlipayNotifyParametersReceiver(AlipayClients alipayClients) {
    if (Objects.isNull(alipayClients)) {
      throw new IllegalArgumentException("alipay clients cannot be null");
    }
    this.alipayClients = alipayClients;
  }

  @Override
  protected AlipayNotifyParameters validate(AlipayNotifyParameters parameters) throws NotifyContentIncorrectException {
    String appId = (String) parameters.getValue(PARAMETER_APP_ID);
    if (Objects.isNull(appId)) {
      throw new NotifyContentIncorrectException("appId not found: " + parameters);
    }
    String sign = (String) parameters.getValue(PARAMETER_SIGN);
    if (Objects.isNull(sign)) {
      throw new NotifyContentIncorrectException("sign not found: " + parameters);
    }
    StringBuilder builder = new StringBuilder();
    for (NotifyParameters.Parameter parameter : parameters) {
      String parameterName = parameter.getName();
      if (!PARAMETER_SIGN_TYPE.equalsIgnoreCase(parameterName) && !PARAMETER_SIGN.equalsIgnoreCase(parameterName)) {
        if (builder.length() > 0) {
          builder.append("&");
        }
        builder.append(parameterName).append("=").append(parameter.getValue());
      }
    }
    AlipayCertificates certificates = alipayClients.getConfig(appId).getCertificates();
    String serialNumber = certificates.getValidSerialNumber();
    if (!certificates.verify(serialNumber, sign, builder.toString().getBytes(StandardCharsets.UTF_8))) {
      throw new NotifyContentIncorrectException("sign is invalid: " + parameters);
    }
    return parameters;
  }
}
