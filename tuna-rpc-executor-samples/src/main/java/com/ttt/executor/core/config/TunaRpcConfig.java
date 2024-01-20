package com.ttt.executor.core.config;

import com.ttt.tuna.core.executor.impl.TunaSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description config
 * DATA 2024-01-20
 *
 * @Author ttt
 */
@Configuration
public class TunaRpcConfig {

    private static final Logger log = LoggerFactory.getLogger(TunaRpcConfig.class);

    @Value("${tuna.rpc.executor.address}")
    private String address;

    @Value("${tuna.rpc.executor.ip}")
    private String ip;

    @Value("${tuna.rpc.executor.port}")
    private int port;

    @Bean
    public TunaSpringExecutor tunaSpringExecutor(){
        TunaSpringExecutor tunaSpringExecutor = new TunaSpringExecutor();
        tunaSpringExecutor.setAddress(address);
        tunaSpringExecutor.setIp(ip);
        tunaSpringExecutor.setPort(port);
        return tunaSpringExecutor;
    }
}
