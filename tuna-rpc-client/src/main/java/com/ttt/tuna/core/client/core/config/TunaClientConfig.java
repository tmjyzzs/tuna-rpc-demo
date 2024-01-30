package com.ttt.tuna.core.client.core.config;

import com.ttt.tuna.core.client.core.scheduler.TunaScheduler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Description 服务端的启动配置
 * DATA 2024-01-29
 *
 * @Author ttt
 */
@Component
public class TunaClientConfig implements InitializingBean, DisposableBean {


    private TunaScheduler  tunaScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        tunaScheduler = new TunaScheduler();

    }
    @Override
    public void destroy() throws Exception {

    }


}
