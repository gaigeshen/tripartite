package work.gaigeshen.triparttite.pay.alipay.parameters;

import work.gaigeshen.triparttite.core.parameter.Parameter;
import work.gaigeshen.triparttite.core.parameter.Parameters;
import work.gaigeshen.triparttite.core.util.JacksonUtils;
import work.gaigeshen.triparttite.pay.alipay.config.AlipayConfig;
import work.gaigeshen.triparttite.pay.alipay.config.AlipayPrivateKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 支付宝业务参数构建请求参数
 *
 * @author gaigeshen
 */
public abstract class AlipayParametersBuilder {

  private final AlipayParameters parameters;

  private final String apiMethod;

  /**
   * 创建支付宝请求参数构建器
   *
   * @param parameters 支付宝业务参数不能为空
   * @param apiMethod 支付宝业务参数所对应的接口名称不能为空
   */
  public AlipayParametersBuilder(AlipayParameters parameters, String apiMethod) {
    if (Objects.isNull(parameters)) {
      throw new IllegalArgumentException("alipay parameters cannot be null");
    }
    if (Objects.isNull(apiMethod)) {
      throw new IllegalArgumentException("api method cannot be null");
    }
    this.parameters = parameters;
    this.apiMethod = apiMethod;
  }

  /**
   * 构建请求参数方法
   *
   * @param config 接受支付宝配置对象不会为空
   * @return 请求参数不为空
   */
  public Parameters build(AlipayConfig config) {
    // 添加固定的参数
    Parameters newParameters = Parameters.parameters(
            Parameter.string("format", "json"), Parameter.string("charset", "utf-8"),
            Parameter.string("version", "1.0"), Parameter.string("sign_type", "RSA2")
    );
    // 应用编号和异步通知地址
    newParameters.put("app_id", config.getAppId());
    newParameters.put("notify_url", config.getNotifyUrl());

    // 业务参数接口名称和对应的业务参数
    newParameters.put("method", apiMethod);
    newParameters.put("biz_content", JacksonUtils.toJson(parameters));

    // 应用私钥用于对请求参数计算签名
    AlipayPrivateKey privateKey = config.getPrivateKey();

    // 支付宝后台配置为证书模式需要携带这两个参数
    newParameters.put("app_cert_sn", privateKey.getCertSerialNumber());
    newParameters.put("alipay_root_cert_sn", config.getRootCertificate().getSerialNumber());

    newParameters.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    // 允许重写已经构建的请求参数
    // 在签名计算之前
    overrideParameters(newParameters, config);

    // 参数的链接方法内部会将所有的参数排序之后再使用连接符进行链接
    // 签名计算规则要求的内容也是如此的规则
    String signResult = privateKey.sign(newParameters.join("&"));
    newParameters.put("sign", signResult);

    return newParameters;
  }

  /**
   * 此方法用于修改已经构建的请求参数，比如可以添加额外的请求参数
   *
   * @param parameters 请求参数不为空
   * @param config 支付宝配置对象不会为空
   */
  protected void overrideParameters(Parameters parameters, AlipayConfig config) {

  }
}
