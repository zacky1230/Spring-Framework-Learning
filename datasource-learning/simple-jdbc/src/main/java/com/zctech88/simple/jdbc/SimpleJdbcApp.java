package com.zctech88.simple.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 10:58
 */
@SpringBootApplication
public class SimpleJdbcApp implements CommandLineRunner {

    @Autowired
    private FooDao fooDao;

    public static void main(String[] args) {
        SpringApplication.run(SimpleJdbcApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fooDao.insertData();
//        batchFooDao.batchInsert();
        fooDao.listData();
    }

    @Bean
    @Autowired
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("FOO").usingGeneratedKeyColumns("ID");
    }
}
