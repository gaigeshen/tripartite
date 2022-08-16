package work.gaigeshen.tripartite.doudian.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.doudian.openapi.DoudianClient;
import work.gaigeshen.tripartite.doudian.openapi.accesstoken.*;

import javax.sql.DataSource;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
@AutoConfigureAfter({ DataSourceAutoConfiguration.class })
@ConditionalOnClass({ DoudianClient.class })
@EnableConfigurationProperties({ DoudianProperties.class })
@Configuration
public class DoudianAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(DoudianAutoConfiguration.class);

  private final DoudianProperties doudianProperties;

  public DoudianAutoConfiguration(DoudianProperties doudianProperties) {
    this.doudianProperties = doudianProperties;
  }

  @Bean
  public DoudianClient doudianClient() {
    return null;
  }

  @Bean(destroyMethod = "shutdown")
  public DoudianAccessTokenManager doudianAccessTokenManager(
          DoudianAccessTokenStore accessTokenStore,
          DoudianAccessTokenRefresher accessTokenRefresher) {
    log.info("creating access token manager with token store {} and token refresher {}",
            accessTokenStore, accessTokenRefresher);
    return new DefaultDoudianAccessTokenManager(accessTokenStore, accessTokenRefresher);
  }

  @Bean
  public DoudianAccessTokenStore doudianAccessTokenStore(
          ObjectProvider<DataSource> dataSource,
          @DoudianDataSource ObjectProvider<DataSource> doudianDataSource) {
    DoudianAccessTokenStoreType accessTokenStoreType = doudianProperties.getAccessTokenStoreType();
    if (DoudianAccessTokenStoreType.JDBC == accessTokenStoreType) {
      DataSource dataSourceIfAvailable = dataSource.getIfAvailable();
      if (Objects.nonNull(dataSourceIfAvailable)) {
        DataSource dataSourceToUse = getDataSource(dataSourceIfAvailable, doudianDataSource);
        log.info("creating jdbc access token store with datasource {}", dataSourceToUse);
        return new JdbcDoudianAccessTokenStore(dataSourceToUse);
      }
    }
    log.info("creating default access token store");
    return new DefaultDoudianAccessTokenStore();
  }

  // @ConditionalOnBean({ DataSource.class })
  // @Bean
  // public DoudianDataSourceInitializer doudianDataSourceInitializer(
  //         DataSource dataSource, @DoudianDataSource ObjectProvider<DataSource> doudianDataSource,
  //         ResourceLoader resourceLoader) {
  //   DataSource dataSourceToUse = getDataSource(dataSource, doudianDataSource);
  //   return new DoudianDataSourceInitializer(dataSourceToUse, resourceLoader, doudianProperties);
  // }

  private DataSource getDataSource(DataSource dataSource, ObjectProvider<DataSource> doudianDataSource) {
    DataSource doudianDataSourceIfAvailable = doudianDataSource.getIfAvailable();
    return Objects.isNull(doudianDataSourceIfAvailable) ? dataSource : doudianDataSourceIfAvailable;
  }
}
