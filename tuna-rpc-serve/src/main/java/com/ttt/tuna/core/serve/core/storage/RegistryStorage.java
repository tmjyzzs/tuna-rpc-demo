package com.ttt.tuna.core.serve.core.storage;


import com.ttt.tuna.core.serve.core.model.TunaRegistry;
import java.util.HashSet;
import java.util.Set;

/**
 * Description 存放客户端的注册信息
 * DATA 2024-01-12
 *
 * @Author ttt
 */
public class RegistryStorage {

    // 不可以存放重复元素
    private static Set<TunaRegistry> RegistrySet = new HashSet<TunaRegistry>();


    public static void addXxlRegistry(TunaRegistry  tunaRegistry){
        RegistrySet.add(tunaRegistry);
    }

    public static TunaRegistry getXxlRegistry(){
        // TODO 先简单获取，后期需要判断是否为空，还可以添加轮询机制
          return RegistrySet.iterator().next();
    }
}
