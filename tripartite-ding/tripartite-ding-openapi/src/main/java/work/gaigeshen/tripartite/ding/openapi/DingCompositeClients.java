package work.gaigeshen.tripartite.ding.openapi;

import work.gaigeshen.tripartite.ding.openapi.config.DingConfig;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 钉钉组合客户端集合
 *
 * @author gaigeshen
 */
public interface DingCompositeClients {

  default DingConfig getConfig(String appKey) throws DingClientNotFoundException {
    if (Objects.isNull(appKey)) {
      throw new IllegalArgumentException("appKey cannot be null");
    }
    return getConfig(dc -> Objects.equals(dc.getAppKey(), appKey));
  }

  default DingConfig getConfig(Predicate<DingConfig> predicate) throws DingClientNotFoundException {
    if (Objects.isNull(predicate)) {
      throw new IllegalArgumentException("predicate cannot be null");
    }
    return getCompositeClient(predicate).getConfig();
  }

  default DingCompositeClient getCompositeClient(String appKey) throws DingClientNotFoundException {
    if (Objects.isNull(appKey)) {
      throw new IllegalArgumentException("appKey cannot be null");
    }
    return getCompositeClient(dc -> Objects.equals(dc.getAppKey(), appKey));
  }

  DingCompositeClient getCompositeClient(Predicate<DingConfig> predicate) throws DingClientNotFoundException;
}
