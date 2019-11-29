package com.zctech88.single.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 09:20
 */
@SpringBootApplication
@Slf4j
public class SingleDatasourceApp implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SingleDatasourceApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();
    }

    private void showConnection() throws SQLException {
        log.info(dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info(dataSource.toString());
        conn.close();
    }

    private void showData() {
        jdbcTemplate.queryForList("SELECT  * FROM FOO").forEach(row -> log.info(row.toString()));
    }
}
