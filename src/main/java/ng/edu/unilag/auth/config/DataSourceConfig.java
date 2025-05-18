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
        sqLiteConfig.enforceForeignKeys(true); // Enable foreign key support

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + dbPath + "?journal_mode=WAL");
        config.setDataSourceProperties(sqLiteConfig.toProperties());

        // SQLite works best with a single connection
        config.setMaximumPoolSize(1);
        config.setPoolName("SQLiteHikariCP");

        return new HikariDataSource(config);
    }

    private void checkDatabaseFile(String dbPath) {
        Path path = Paths.get(dbPath);
        File dbFile = path.toFile();

        // Check parent directory
        Path parentDir = path.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                throw new RuntimeException("Unable to create parent directories for database file: " + dbPath, e);
            }
        }

        // Check file permissions if the file exists
        if (dbFile.exists()) {
            if (!dbFile.canRead() || !dbFile.canWrite()) {
                throw new RuntimeException("Database file is neither readable nor writable: " + dbPath);
            }
        }
    }
}