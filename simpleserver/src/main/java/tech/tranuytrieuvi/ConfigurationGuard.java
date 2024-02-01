package tech.tranuytrieuvi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class ConfigurationGuard implements InitializingBean {

  @Value("${spring.datasource.url}")
  private final String datasourceURL;

  @Value("${spring.datasource.username}")
  private final String username;

  @Value("${spring.datasource.password}")
  private final String password;

  private void setup() {
    if (datasourceURL == null || datasourceURL.isEmpty()) {
      log.warn("Skip creating database since there is no JDBC URL");
      return;
    }

    log.debug("Creating database if not exist...");

    int lastSlashIndex = datasourceURL.lastIndexOf('/');
    String databaseName = datasourceURL.substring(lastSlashIndex + 1);
    String url = datasourceURL.substring(0, lastSlashIndex);

    try (Connection connection = DriverManager.getConnection(
        url,
        username,
        password);
        Statement statement = connection.createStatement()) {
      // Check if database exists
      ResultSet resultSet = statement
          .executeQuery("SELECT datname FROM pg_catalog.pg_database WHERE datname = '" + databaseName + "'");
      if (resultSet.next()) {
        log.debug("Database already exists");
        return;
      }

      statement.executeUpdate("CREATE DATABASE " + databaseName);
      log.debug("Database created successfully");
    } catch (SQLException e) {
      log.error("Error", e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    setup();
  }

}
