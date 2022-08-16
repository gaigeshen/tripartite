package work.gaigeshen.tripartite.doudian.spring.boot.autoconfigure;

/**
 *
 * @author gaigeshen
 */
// public class DoudianDataSourceInitializer extends AbstractDataSourceInitializer {
//
//   private final DoudianProperties properties;
//
//   public DoudianDataSourceInitializer(DataSource dataSource, ResourceLoader resourceLoader,
//                                       DoudianProperties properties) {
//     super(dataSource, resourceLoader);
//     this.properties = properties;
//   }
//
//   @Override
//   protected DataSourceInitializationMode getMode() {
//     return properties.getJdbc().getInitializeSchema();
//   }
//
//   @Override
//   protected String getSchemaLocation() {
//     return properties.getJdbc().getSchema();
//   }
//
//   @Override
//   protected String getDatabaseName() {
//     String databaseName = super.getDatabaseName();
//     if ("mysql".equals(databaseName)) {
//       return "mysql_innodb";
//     }
//     return databaseName;
//   }
// }
