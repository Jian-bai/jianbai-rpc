package com.rpc.client;

import com.rpc.codec.Decoder;
import com.rpc.codec.Encoder;
import com.rpc.common.util.ReflectionUtils;

import java.lang.reflect.Proxy;


/**
 * @author JianBai
 * @date 2022/8/27 12:40
 */

public class RpcClient {

    private RpcClientConfig config = new RpcClientConfig();
    private final Encoder encoder;
    private final Decoder decoder;
    private final TransportSelector selector;

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());
        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    public RpcClient() {
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());
        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    /**
     * 获取动态代理类
     */
    public Object getProxy(Class<?> clazz) {
        return Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector)
        );
    }
}
