package com.rpc.server;

import com.proto.RpcRequest;
import com.proto.RpcResponse;
import com.rpc.codec.Decoder;
import com.rpc.codec.Encoder;
import com.rpc.common.util.ReflectionUtils;
import com.rpc.transport.RequestHandler;
import com.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author JianBai
 * @date 2022/8/27 12:40
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config = new RpcServerConfig();
    private final TransportServer net;
    private final Encoder encoder;
    private final Decoder decoder;
    private final ServiceManager serviceManager;
    private final ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config) {
        this.config = config;

        //net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        //codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();

    }

    public RpcServer() {
        //net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        //codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    /**
     * 注册函数，将一个类注册为rpc service，其中的所有public方法会被注册为rpc service
     * 这个函数会调用ServiceManager类的register方法
     *
     * @param interfaceClass 注册类的接口
     * @param bean           注册类的实现类
     * @param <T>            泛型
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    /**
     * 开启网络模块
     */
    public void start() {
        this.net.start();
    }

    /**
     * 停止网络模块
     */
    public void stop() {
        this.net.stop();
    }


    private final RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream inData, OutputStream toResp) {
            RpcResponse resp = new RpcResponse();
            try {
                byte[] inBytes = IOUtils.readFully(inData, inData.available());
                RpcRequest request = decoder.decode(inBytes, RpcRequest.class);
                log.info("get request: {}", request);

                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);

                resp.setData(ret);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: " + e.getClass().getName()
                        + " : " + e.getMessage()
                );
            } finally {
                try {
                    byte[] outBytes = encoder.encode(resp);
                    toResp.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}
