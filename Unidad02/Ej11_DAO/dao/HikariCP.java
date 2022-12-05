package Ej11_DAO.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.nio.file.Path;

public class HikariCP {
    private HikariDataSource dataSource;
    private final String RUTABASE = Path.of("Unidad02/Ej11_DAO/db/formula1.db").toString();

    public HikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + RUTABASE);
        config.addDataSourceProperty("minimumIdle", "5");
        config.addDataSourceProperty("maximumPoolSize", "10");

        dataSource = new HikariDataSource(config);
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
