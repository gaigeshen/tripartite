package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问令牌存储器默认的实现，采用简单的哈希存储
 *
 * @author gaigeshen
 */
public class DefaultDoudianAccessTokenStore implements DoudianAccessTokenStore {

  private final Map<String, DoudianAccessToken> internalStore = new ConcurrentHashMap<>();

  @Override
  public boolean save(DoudianAccessToken accessToken) throws DoudianAccessTokenStoreException {
    if (Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("accessToken cannot be null");
    }
    return Objects.isNull(internalStore.put(accessToken.getShopId(), accessToken));
  }

  @Override
  public void deleteByShopId(String shopId) throws DoudianAccessTokenStoreException {
    if (Objects.isNull(shopId)) {
      throw new IllegalArgumentException("shopId cannot be null");
    }
    internalStore.remove(shopId);
  }

  @Override
  public DoudianAccessToken findByShopId(String shopId) throws DoudianAccessTokenStoreException {
    if (Objects.isNull(shopId)) {
      throw new IllegalArgumentException("shopId cannot be null");
    }
    return internalStore.get(shopId);
  }

  @Override
  public List<DoudianAccessToken> findAll() throws DoudianAccessTokenStoreException {
    return new ArrayList<>(internalStore.values());
  }
}
