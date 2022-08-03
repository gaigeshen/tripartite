package work.gaigeshen.tripartite.his.procurement.openapi.accesstoken;

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
public class DefaultHisProcurementAccessTokenStore implements HisProcurementAccessTokenStore {

  private final Map<String, HisProcurementAccessToken> internalStore = new ConcurrentHashMap<>();

  @Override
  public boolean save(HisProcurementAccessToken accessToken) throws HisProcurementAccessTokenException {
    if (Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("accessToken cannot be null");
    }
    return Objects.isNull(internalStore.put(accessToken.getAccount(), accessToken));
  }

  @Override
  public void deleteByAccount(String account) throws HisProcurementAccessTokenException {
    if (Objects.isNull(account)) {
      throw new IllegalArgumentException("account cannot be null");
    }
    internalStore.remove(account);
  }

  @Override
  public HisProcurementAccessToken findByAccount(String account) throws HisProcurementAccessTokenException {
    if (Objects.isNull(account)) {
      throw new IllegalArgumentException("account cannot be null");
    }
    return internalStore.get(account);
  }

  @Override
  public List<HisProcurementAccessToken> findAll() throws HisProcurementAccessTokenException {
    return new ArrayList<>(internalStore.values());
  }
}
