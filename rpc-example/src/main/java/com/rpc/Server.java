package com.rpc;

import com.rpc.server.RpcServer;

/**
 * @author JianBai
 * @date 2022/8/27 15:00
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(RpcService.class,new RpcServiceImpl());
        server.start();
    }
}
