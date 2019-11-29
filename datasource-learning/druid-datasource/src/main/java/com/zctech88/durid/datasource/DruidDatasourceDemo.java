package com.zctech88.durid.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 10:30
 */
@Slf4j
@SpringBootApplication
public class DruidDatasourceDemo  implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DruidDatasourceDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(dataSource.toString());
    }
}
