package work.gaigeshen.tripartite.doudian.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 抖店客户端配置
 *
 * @author gaigeshen
 */
@ConfigurationProperties("doudian")
public class DoudianProperties {

  /**
   * 应用编号
   */
  private String appKey;

  /**
   * 应用密钥
   */
  private String appSecret;

  /**
   * 数据源配置
   */
  private Jdbc jdbc = new Jdbc();

  /**
   * 访问令牌存储器类型
   */
  private DoudianAccessTokenStoreType accessTokenStoreType = DoudianAccessTokenStoreType.MEMORY;

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

  public Jdbc getJdbc() {
    return jdbc;
  }

  public void setJdbc(Jdbc jdbc) {
    this.jdbc = jdbc;
  }

  public DoudianAccessTokenStoreType getAccessTokenStoreType() {
    return accessTokenStoreType;
  }

  public void setAccessTokenStoreType(DoudianAccessTokenStoreType accessTokenStoreType) {
    this.accessTokenStoreType = accessTokenStoreType;
  }

  /**
   * 数据源配置
   *
   * @author gaigeshen
   */
  public static class Jdbc {

    private static final String DEFAULT_SCHEMA_LOCATION = "classpath:jdbc/tables_@@platform@@.sql";

    /**
     * 将用于数据源初始化执行脚本文件
     */
    private String schema = DEFAULT_SCHEMA_LOCATION;
    /**
     * 数据源初始化模式，默认只在嵌入式的数据源进行初始化
     */
    // private DataSourceInitializationMode initializeSchema = DataSourceInitializationMode.EMBEDDED;

    public String getSchema() {
      return schema;
    }

    public void setSchema(String schema) {
      this.schema = schema;
    }

    // public DataSourceInitializationMode getInitializeSchema() {
    //   return initializeSchema;
    // }
    //
    // public void setInitializeSchema(DataSourceInitializationMode initializeSchema) {
    //   this.initializeSchema = initializeSchema;
    // }
  }
}
