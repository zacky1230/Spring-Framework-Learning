package com.zctech88.prorammatic.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 12:42
 */
@SpringBootApplication
@Slf4j
public class ProgrammaticTransactionApp implements CommandLineRunner {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProgrammaticTransactionApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        log.info("COUNT BEFORE TRANSACTION: {}", getCount());

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'aaa')");
                log.info("COUNT IN TRANSACTION: {}", getCount());
                transactionStatus.setRollbackOnly();
            }
        });
        log.info("COUNT AFTER TRANSACTION: {}", getCount());
    }

    private long getCount() {
        return (long)jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO").get(0).get("CNT");
    }
}
