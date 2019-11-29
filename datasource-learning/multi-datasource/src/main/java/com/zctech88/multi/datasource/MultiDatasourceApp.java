package com.zctech88.multi.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 09:35
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    JdbcTemplateAutoConfiguration.class})
@Slf4j
public class MultiDatasourceApp {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceApp.class, args);
    }

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDatasourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDateSource(){
        DataSourceProperties dataSourceProperties = fooDatasourceProperties();
        log.info("foo datasource: {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource datasource){
        return new DataSourceTransactionManager(datasource);
    }
}
