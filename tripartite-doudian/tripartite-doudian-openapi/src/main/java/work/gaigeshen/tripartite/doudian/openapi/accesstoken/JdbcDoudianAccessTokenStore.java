package work.gaigeshen.tripartite.doudian.openapi.accesstoken;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * 此访问令牌管理器基于数据库的数据源实现
 *
 * @author gaigeshen
 */
public class JdbcDoudianAccessTokenStore implements DoudianAccessTokenStore {

  private static final String INSERT = "insert into doudian_access_token (access_token, refresh_token, scope, "
          + "shop_id, shop_name, expires_in, expires_timestamp, update_time) values (?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String UPDATE = "update doudian_access_token set access_token = ?, refresh_token = ?, scope = ?, "
          + "shop_name = ?, expires_in = ?, expires_timestamp = ?, update_time = ? where shop_id = ?";

  private static final String DELETE = "delete from doudian_access_token where shop_id = ?";

  private static final String FIND = "select access_token, refresh_token, scope, "
          + "shop_id, shop_name, expires_in, expires_timestamp, update_time from doudian_access_token where shop_id = ?";

  private static final String FIND_ALL = "select access_token, refresh_token, scope, "
          + "shop_id, shop_name, expires_in, expires_timestamp, update_time from doudian_access_token";

  private static final ResultSetHandler<Map<String, Object>> DATABASE_RESULT_HANDLER = new MapHandler();

  private static final ResultSetHandler<List<Map<String, Object>>> DATABASE_RESULTS_HANDLER = new MapListHandler();

  private final DataSource dataSource;

  private final QueryRunner queryRunner;

  /**
   * 创建访问令牌存储器
   *
   * @param dataSource 数据源
   */
  public JdbcDoudianAccessTokenStore(DataSource dataSource) {
    if (Objects.isNull(dataSource)) {
      throw new IllegalArgumentException("dataSource cannot be null");
    }
    this.dataSource = dataSource;
    this.queryRunner = new QueryRunner(dataSource);
  }

  @Override
  public boolean save(DoudianAccessToken accessToken) throws DoudianAccessTokenStoreException {
    if (Objects.isNull(accessToken)) {
      throw new IllegalArgumentException("accessToken cannot be null");
    }
    Connection connection = prepareTransactionalConnection();
    try {
      // 先执行更新操作，如果更新成功则说明该店铺已经存在访问令牌
      int result = queryRunner.update(connection, UPDATE, accessToken.getAccessToken(), accessToken.getRefreshToken(),
              accessToken.getScope(), accessToken.getShopName(),
              accessToken.getExpiresIn(), accessToken.getExpiresTimestamp(), accessToken.getUpdateTime(),
              accessToken.getShopId());
      if (result > 0) {
        connection.commit();
        return false;
      }
      // 为新店铺增加访问令牌
      queryRunner.update(connection, INSERT, accessToken.getAccessToken(), accessToken.getRefreshToken(),
              accessToken.getScope(), accessToken.getShopId(), accessToken.getShopName(),
              accessToken.getExpiresIn(), accessToken.getExpiresTimestamp(), accessToken.getUpdateTime());
      connection.commit();
    } catch (SQLException e) {
      // 操作数据库过程中发生异常，本次所有操作失败，抛出异常
      DbUtils.rollbackAndCloseQuietly(connection);
      throw new DoudianAccessTokenStoreException("Could not save access token, shop id is " + accessToken.getShopId()
              + ", shop name is " + accessToken.getShopName(), e);
    } finally {
      // 确保数据库连接被关闭
      DbUtils.closeQuietly(connection);
    }
    // 所有数据库的操作成功，增加了新店铺的访问令牌
    return true;
  }

  @Override
  public void deleteByShopId(String shopId) throws DoudianAccessTokenStoreException {
    if (Objects.isNull(shopId)) {
      throw new IllegalArgumentException("shopId cannot be null");
    }
    try {
      queryRunner.update(DELETE, shopId);
    } catch (SQLException e) {
      throw new DoudianAccessTokenStoreException("Could not delete access token, shop id is " + shopId, e);
    }
  }

  @Override
  public DoudianAccessToken findByShopId(String shopId) throws DoudianAccessTokenStoreException {
    if (Objects.isNull(shopId)) {
      throw new IllegalArgumentException("shopId cannot be null");
    }
    Map<String, Object> result;
    try {
      result = queryRunner.query(FIND, DATABASE_RESULT_HANDLER, shopId);
    } catch (SQLException e) {
      throw new DoudianAccessTokenStoreException("Could not find access token, shop id is " + shopId, e);
    }
    if (Objects.isNull(result)) {
      return null;
    }
    return parseAccessToken(result);
  }

  @Override
  public List<DoudianAccessToken> findAll() throws DoudianAccessTokenStoreException {
    List<DoudianAccessToken> accessTokens = new ArrayList<>();
    List<Map<String, Object>> results;
    try {
      results = queryRunner.query(FIND_ALL, DATABASE_RESULTS_HANDLER);
    } catch (SQLException e) {
      throw new DoudianAccessTokenStoreException("Could not find all access tokens", e);
    }
    if (results.isEmpty()) {
      return accessTokens;
    }
    for (Map<String, Object> result : results) {
      accessTokens.add(parseAccessToken(result));
    }
    return accessTokens;
  }

  private DoudianAccessToken parseAccessToken(Map<String, Object> databaseResult) throws DoudianAccessTokenStoreException {
    try {
      return DoudianAccessToken.builder()
              .setAccessToken((String) databaseResult.get("access_token"))
              .setRefreshToken((String) databaseResult.get("refresh_token"))
              .setScope((String) databaseResult.get("scope"))
              .setShopId((String) databaseResult.get("shopId"))
              .setShopName((String) databaseResult.get("shop_name"))
              .setExpiresIn((Long) databaseResult.get("expires_in"))
              .setExpiresTimestamp((Long) databaseResult.get("expires_timestamp"))
              .setUpdateTime((Date) databaseResult.get("update_time"))
              .build();
    } catch (Exception e) {
      throw new DoudianAccessTokenStoreException("Could not parse to access token object from " + databaseResult);
    }
  }

  private Connection prepareTransactionalConnection() throws DoudianAccessTokenStoreException {
    Connection connection = null;
    try {
      connection = dataSource.getConnection();
      connection.setAutoCommit(false);
      return connection;
    } catch (SQLException e) {
      DbUtils.closeQuietly(connection);
      throw new DoudianAccessTokenStoreException("Could not get database connection from " + dataSource);
    }
  }
}
