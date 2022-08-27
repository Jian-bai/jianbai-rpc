package com.rpc;

import com.rpc.client.RpcClient;

/**
 * @author JianBai
 * @date 2022/8/27 14:57
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        RpcService  service = (RpcService) client.getProxy(RpcService.class);
        service.printLog();
    }
}
