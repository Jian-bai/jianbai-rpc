package com.rpc;

/**
 * @author JianBai
 * @date 2022/8/27 14:55
 */
public class RpcServiceImpl implements RpcService{
    @Override
    public void printLog() {
        System.err.println("rpc log init done");
    }
}
