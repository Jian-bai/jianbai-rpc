package com.rpc.transport;

/**
 * @author JianBai
 * @date 2022/8/27 3:57
 */
public interface TransportServer {
    /**
     * 初始化，设置servlet
     * @param port 端口
     * @param handler 处理请求的方法
     */
    void init(int port,RequestHandler handler);

    /**
     * 启动服务
     */
    void start();

    /**
     * 关闭服务
     */
    void stop();
}
