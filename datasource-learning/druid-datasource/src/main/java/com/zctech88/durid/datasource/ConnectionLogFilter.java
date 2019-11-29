package com.zctech88.durid.datasource;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 10:31
 */
@Slf4j
public class ConnectionLogFilter extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info){
        log.info("Before Connection!");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection){
        log.info("After Connection!");
    }
}
