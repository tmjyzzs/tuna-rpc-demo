package com.ttt.tuna.core.client.core.scheduler;

import com.ttt.tuna.core.client.core.thread.TunaTriggerPoolHelper;
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

    }

}
