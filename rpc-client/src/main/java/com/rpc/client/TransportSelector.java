package com.rpc.client;

import com.proto.Container;
import com.rpc.transport.TransportClient;

import java.util.List;

/**
 * server选择器
 * @author JianBai
 * @date 2022/8/27 12:40
 */
public interface TransportSelector {
    /**
     * 初始化selector
     * @param containers 可以连接的server端点信息
     * @param count client 与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Container> containers, int count, Class<? extends TransportClient> clazz);
    /**
     * 选择一个transport与server做交互
     * @return 网络Client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client -
     */
    void release(TransportClient client);

    /**
     * 关闭
     */
    void close();
}
