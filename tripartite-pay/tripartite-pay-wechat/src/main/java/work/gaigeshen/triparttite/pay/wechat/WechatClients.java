package work.gaigeshen.triparttite.pay.wechat;

import work.gaigeshen.triparttite.pay.wechat.config.WechatConfig;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 多微信支付客户端
 *
 * @author gaigeshen
 */
public interface WechatClients {
  /**
   * 查询微信支付客户端的配置
   *
   * @param appId 应用编号不能为空
   * @param merchantId 商户编号不能为空
   * @return 微信支付客户端的配置不为空
   * @throws WechatClientNotFountException 没有找到指定的微信支付客户端
   */
  default WechatConfig getConfig(String appId, String merchantId) throws WechatClientNotFountException {
    if (Objects.isNull(appId)) {
      throw new IllegalArgumentException("appId cannot be null");
    }
    if (Objects.isNull(merchantId)) {
      throw new IllegalArgumentException("merchantId cannot be null");
    }
    return getConfig(wc -> wc.getAppId().equals(appId) && wc.getMerchantId().equals(merchantId));
  }

  /**
   * 查询微信支付客户端的配置
   *
   * @param predicate 查询条件不能为空
   * @return 微信支付客户端的配置不为空
   * @throws WechatClientNotFountException 没有找到指定的微信支付客户端
   */
  default WechatConfig getConfig(Predicate<WechatConfig> predicate) throws WechatClientNotFountException {
    if (Objects.isNull(predicate)) {
      throw new IllegalArgumentException("predicate cannot be null");
    }
    return getClient(predicate).getWechatConfig();
  }

  /**
   * 查询微信支付客户端
   *
   * @param appId 应用编号不能为空
   * @param merchantId 商户编号不能为空
   * @return 微信支付客户端不为空
   * @throws WechatClientNotFountException 没有找到指定的微信支付客户端
   */
  default WechatClient getClient(String appId, String merchantId) throws WechatClientNotFountException {
    if (Objects.isNull(appId)) {
      throw new IllegalArgumentException("appId cannot be null");
    }
    if (Objects.isNull(merchantId)) {
      throw new IllegalArgumentException("merchantId cannot be null");
    }
    return getClient(wc -> wc.getAppId().equals(appId) && wc.getMerchantId().equals(merchantId));
  }

  /**
   * 查询微信支付客户端
   *
   * @param predicate 查询条件不能为空
   * @return 微信支付客户端不为空
   * @throws WechatClientNotFountException 没有找到指定的微信支付客户端
   */
  WechatClient getClient(Predicate<WechatConfig> predicate) throws WechatClientNotFountException;
}
