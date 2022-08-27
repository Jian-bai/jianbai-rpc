package com.rpc.transport;


import com.proto.Container;

import java.io.InputStream;

/**
 * @author JianBai
 * @date 2022/8/27 3:40
 */
public interface TransportClient {

    /**
     * 初始化连接
     * @param container -
     */
    void init(Container container);


    /**
     * 传输数据
     * @param data -
     * @return -
     */
    InputStream write(InputStream data);

    /**
     * 关闭连接
     */
    void close();
}
