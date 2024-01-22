package com.ttt.tuna.core.server;

import com.ttt.tuna.core.biz.ExecutorBiz;
import com.ttt.tuna.core.biz.impl.ExecutorBizImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description 添加到服务内部的netty服务器
 * DATA 2024-01-22
 *
 * @Author ttt
 */
public class EmbedServer {
    private static final Logger logger = LoggerFactory.getLogger(EmbedServer.class);

    private ExecutorBiz executorBiz;

    private Thread thread;

    public void start(final String address, final int port) {
        executorBiz = new ExecutorBizImpl();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
