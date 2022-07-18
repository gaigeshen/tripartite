package work.gaigeshen.tripartite.doudian.spring.boot.autoconfigure;

import org.springframework.boot.jdbc.AbstractDataSourceInitializer;
import org.springframework.boot.jdbc.DataSourceInitializationMode;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/**
 *
 * @author gaigeshen
 */
public class DoudianDataSourceInitializer extends AbstractDataSourceInitializer {

  private final DoudianProperties properties;

  public DoudianDataSourceInitializer(DataSource dataSource, ResourceLoader resourceLoader,
                                      DoudianProperties properties) {
    super(dataSource, resourceLoader);
    this.properties = properties;
  }

  @Override
  protected DataSourceInitializationMode getMode() {
    return properties.getJdbc().getInitializeSchema();
  }

  @Override
  protected String getSchemaLocation() {
    return properties.getJdbc().getSchema();
  }

  @Override
  protected String getDatabaseName() {
    String databaseName = super.getDatabaseName();
    if ("mysql".equals(databaseName)) {
      return "mysql_innodb";
    }
    return databaseName;
  }
}
