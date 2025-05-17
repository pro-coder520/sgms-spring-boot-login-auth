package ng.edu.unilag.auth.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sqlite.SQLiteConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        SQLiteConfig sqLiteConfig = new SQLiteConfig();
        sqLiteConfig.setReadOnly(true);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:path_to_your_db_file.db"); // Update with your DB path
        config.setDataSourceProperties(sqLiteConfig.toProperties());

        config.setMaximumPoolSize(10);
        config.setPoolName("SQLiteHikariCP");

        return new HikariDataSource(config);
    }
}


