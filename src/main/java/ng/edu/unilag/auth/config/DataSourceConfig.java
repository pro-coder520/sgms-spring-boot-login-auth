package ng.edu.unilag.auth.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sqlite.SQLiteConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        String dbPath = "university.db";
        checkDatabaseFile(dbPath);

        SQLiteConfig sqLiteConfig = new SQLiteConfig();
        sqLiteConfig.setReadOnly(false);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + dbPath);
        config.setDataSourceProperties(sqLiteConfig.toProperties());

        config.setMaximumPoolSize(10);
        config.setPoolName("SQLiteHikariCP");

        return new HikariDataSource(config);
    }

    private void checkDatabaseFile(String dbPath) {
        Path path = Paths.get(dbPath);
        File dbFile = path.toFile();

        //To check if the file exists
        if (!dbFile.exists()) {
            try {
                Files.createDirectories(path.getParent()); // Creates parent directories if not found
                dbFile.createNewFile(); // To create a new file if not found
            } catch (IOException e) {
                throw new RuntimeException("Unable to create database file: " + dbPath, e);
            }
        }

        if (!dbFile.canRead() || !dbFile.canWrite()) {
            throw new RuntimeException("Database file is neither readable nor writable" + dbPath);
        }
    }
}


