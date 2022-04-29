package work.gaigeshen.tripartite.pay.wechat;

import work.gaigeshen.tripartite.pay.wechat.config.WechatConfig;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * 多微信支付客户端的默认实现
 *
 * @author gaigeshen
 */
public class DefaultWechatClients implements WechatClients {

  private final Map<WechatConfig, WechatClient> wechatClients = new ConcurrentHashMap<>();

  public DefaultWechatClients() { }

  /**
   * 创建多微信支付客户端
   *
   * @param wechatClients 微信支付客户端集合不能为空
   */
  public DefaultWechatClients(Collection<WechatClient> wechatClients) {
    if (Objects.isNull(wechatClients)) {
      throw new IllegalArgumentException("wechat clients cannot be null");
    }
    for (WechatClient wechatClient : wechatClients) {
      this.wechatClients.put(wechatClient.getWechatConfig(), wechatClient);
    }
  }

  @Override
  public WechatClient getClient(Predicate<WechatConfig> predicate) throws WechatClientNotFountException {
    if (Objects.isNull(predicate)) {
      throw new IllegalArgumentException("predicate cannot be null");
    }
    WechatClient wechatClient = findWechatClient(predicate);
    if (Objects.isNull(wechatClient)) {
      throw new WechatClientNotFountException("could not find wechat client");
    }
    return wechatClient;
  }

  @Override
  public WechatClient getClient() throws WechatClientNotFountException {
    WechatClient wechatClient = findWechatClient(cfg -> true);
    if (Objects.isNull(wechatClient)) {
      throw new WechatClientNotFountException("could not find wechat client");
    }
    return wechatClient;
  }

  private WechatClient findWechatClient(Predicate<WechatConfig> predicate) {
    if (Objects.isNull(predicate)) {
      throw new IllegalArgumentException("predicate cannot be null");
    }
    for (Map.Entry<WechatConfig, WechatClient> entry : wechatClients.entrySet()) {
      if (predicate.test(entry.getKey())) {
        return entry.getValue();
      }
    }
    return null;
  }
}
