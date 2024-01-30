package com.ttt.tuna.core.serve.core.thread;


import com.ttt.tuna.core.serve.core.model.TunaRegistry;
import com.ttt.tuna.core.serve.core.storage.RegistryStorage;
import com.ttt.tuna.core.model.RegistryParam;
import com.ttt.tuna.core.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Description Tuna 的 注册中心
 * DATA 2024-01-30
 *
 * @Author ttt
 */
public class TunaRegistryHelper {

    private static final Logger log = LoggerFactory.getLogger(TunaRegistryHelper.class);

    private static TunaRegistryHelper instance = new TunaRegistryHelper();

    public static TunaRegistryHelper getInstance(){
        return instance;
    }

    private ThreadPoolExecutor registryOrRemoveThreadPool = null;
    private Thread registryMonitorThread;

    private volatile boolean toStop = false;

    public void start(){

        log.info("========创建注册线程信息==========");
        // for registry or remove
        registryOrRemoveThreadPool = new ThreadPoolExecutor(
                2,
                10,
                30L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "tuna, admin JobRegistryMonitorHelper-registryOrRemoveThreadPool-" + r.hashCode());
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        r.run();
                        log.warn(">>>>>>>>>>> tuna, registry or remove too fast, match threadpool rejected handler(run now).");
                    }
                });

        // for monitor
        registryMonitorThread = new Thread(new Runnable() {
            @Override
            public void run() {

                log.info(">>>>>>>>>>> tuna, job registry monitor thread stop");
            }
        });
        registryMonitorThread.setDaemon(true);
        registryMonitorThread.setName("tuna, admin JobRegistryMonitorHelper-registryMonitorThread");
        registryMonitorThread.start();
    }

    public void toStop(){
        toStop = true;

        // stop registryOrRemoveThreadPool
        registryOrRemoveThreadPool.shutdownNow();

        // stop monitor (interrupt and wait)
        registryMonitorThread.interrupt();
        try {
            registryMonitorThread.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    // ---------------------- helper ----------------------

    public ReturnT<String> registry(RegistryParam registryParam) {

        log.info("远程注册信息>>>{}",registryParam);
        // valid
        if (!StringUtils.hasText(registryParam.getRegistryGroup())
                || !StringUtils.hasText(registryParam.getRegistryKey())
                || !StringUtils.hasText(registryParam.getRegistryValue())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "Illegal Argument.");
        }

        // async execute
        registryOrRemoveThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                // 将注册的数据存放到数据库中
                // 修改将数据存放内存中
                TunaRegistry tunaRegistry = new TunaRegistry();
                tunaRegistry.setRegistryKey(registryParam.getRegistryKey());
                tunaRegistry.setRegistryValue(registryParam.getRegistryValue());
                tunaRegistry.setUpdateTime(new Date());
                RegistryStorage.addXxlRegistry(tunaRegistry);
            }
        });

        return ReturnT.SUCCESS;
    }
}
