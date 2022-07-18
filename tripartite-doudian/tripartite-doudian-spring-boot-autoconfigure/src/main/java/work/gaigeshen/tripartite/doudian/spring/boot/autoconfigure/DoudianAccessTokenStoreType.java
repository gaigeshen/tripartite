package work.gaigeshen.tripartite.doudian.spring.boot.autoconfigure;

import work.gaigeshen.tripartite.doudian.openapi.accesstoken.DefaultDoudianAccessTokenStore;
import work.gaigeshen.tripartite.doudian.openapi.accesstoken.JdbcDoudianAccessTokenStore;

/**
 * 访问令牌存储器类型
 *
 * @author gaigeshen
 */
public enum DoudianAccessTokenStoreType {

  /**
   * 基于内存的
   *
   * @see DefaultDoudianAccessTokenStore
   */
  MEMORY,

  /**
   * 基于数据源的，请确保已经配置了正确的数据源，否则此选项不生效
   *
   * @see DoudianDataSource
   * @see JdbcDoudianAccessTokenStore
   */
  JDBC
}
