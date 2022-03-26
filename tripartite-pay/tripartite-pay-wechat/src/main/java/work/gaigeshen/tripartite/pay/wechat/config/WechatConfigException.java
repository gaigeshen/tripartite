package work.gaigeshen.tripartite.pay.wechat.config;

import work.gaigeshen.tripartite.pay.wechat.WechatClientException;

/**
 * @author gaigeshen
 */
public class WechatConfigException extends WechatClientException {
  public WechatConfigException(String message) {
    super(message);
  }
  public WechatConfigException(String message, Throwable cause) {
    super(message, cause);
  }
}
