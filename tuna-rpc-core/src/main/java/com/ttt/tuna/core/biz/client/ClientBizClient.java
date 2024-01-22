package com.ttt.tuna.core.biz.client;

import com.ttt.tuna.core.biz.ClientBiz;

/**
 * Description TODO
 * DATA 2024-01-20
 *
 * @Author ttt
 */
public class ClientBizClient implements ClientBiz {

    private String addressUrl ;

    public ClientBizClient() {
    }

    public ClientBizClient(String addressUrl) {
        this.addressUrl = addressUrl;
    }
}
