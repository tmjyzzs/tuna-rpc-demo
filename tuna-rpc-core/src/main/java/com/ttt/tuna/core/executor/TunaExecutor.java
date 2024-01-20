package com.ttt.tuna.core.executor;

import com.ttt.tuna.core.util.IpUtil;
import com.ttt.tuna.core.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*Description 初始化客户端的各种基础信息
*DATA 2024-01-20
*@Author ttt
*/public class TunaExecutor {

    private static final Logger log = LoggerFactory.getLogger(TunaExecutor.class);

    private String adminAddresses;

    private String address;
    private String ip;

    private int port;

    public String getAdminAddresses() {
        return adminAddresses;
    }

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    // 初始化netty服务器
    private void initEmbedServer(String address, String ip, int port) throws Exception {
        port = port>0?port: NetUtil.findAvailablePort(9999);
        ip = (ip!=null&&ip.trim().length()>0)?ip: getIp();

        if (address==null || address.trim().length()==0) {
            String ip_port_address = IpUtil.getIpPort(ip, port);
            log.info("本机的ip地址{}",ip_port_address);
        }
    }
}
