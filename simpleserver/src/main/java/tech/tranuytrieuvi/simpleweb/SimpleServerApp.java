package tech.tranuytrieuvi.simpleweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.sql.*;
import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class SimpleServerApp {

    private static void setup() {
        log.debug("Creating database if not exist...");
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/",
                "postgres",
                "Root@2023"
        );
             Statement statement = connection.createStatement()
        ) {
            String databaseName = "bookstore";
            // Check if database exists
            ResultSet resultSet = statement.executeQuery("SELECT datname FROM pg_catalog.pg_database WHERE datname = '" + databaseName + "'");
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

    public static void main(String[] args) {
        setup();
        SpringApplication.run(SimpleServerApp.class, args);
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
////        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
//        configuration.setMaxAge(Duration.ofMinutes(5));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
