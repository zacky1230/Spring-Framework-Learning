package com.zctech88.declarative.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 12:57
 */
@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@Slf4j
public class DeclarativeTransactionApp implements CommandLineRunner {

    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DeclarativeTransactionApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        log.info("AAA {}",
            jdbcTemplate
                .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAA'", Long.class));
        try {
            fooService.insertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                jdbcTemplate
                    .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }

        /**
         * 调用方法没有事务
         */
        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                jdbcTemplate
                    .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }
    }
}
