package com.ttt.tuna.core.serve.core.scheduler;

import com.ttt.tuna.core.serve.core.thread.TunaRegistryHelper;
import com.ttt.tuna.core.serve.core.thread.TunaTriggerPoolHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description 各种启动配置的调度器
 * DATA 2024-01-29
 *
 * @Author ttt
 */
public class TunaScheduler {
    private static final Logger log = LoggerFactory.getLogger(TunaScheduler.class);

    public void init() {
        // 初始化线程池
        TunaTriggerPoolHelper.toStart();

        // 启动注册中心
        TunaRegistryHelper.getInstance().start();

        log.info("tuna client success");
    }

    public void destroy() throws Exception {

        // client registry stop
        TunaRegistryHelper.getInstance().toStop();

        // client trigger pool stop
        TunaTriggerPoolHelper.toStop();

    }

}
