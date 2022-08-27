package com.rpc.client;

import com.proto.RpcRequest;
import com.proto.RpcResponse;
import com.proto.ServiceDescriptor;
import com.rpc.codec.Decoder;
import com.rpc.codec.Encoder;
import com.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 远程调用
 *
 * @author JianBai
 * @date 2022/8/27 12:40
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private final Encoder encoder;
    private final Class<?> clazz;
    private final Decoder decoder;
    private final TransportSelector selector;

    RemoteInvoker(Class<?> clazz,
                  Encoder encoder,
                  Decoder decoder,
                  TransportSelector selector) {
        this.encoder = encoder;
        this.decoder = decoder;
        this.clazz = clazz;
        this.selector = selector;
    }


    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setService(ServiceDescriptor.init(clazz, method));
        request.setParameters(args);
        RpcResponse resp = invokeRemote(request);
        if (resp == null || resp.getCode() != 0) {
            throw new IllegalStateException("fail to invoje remote: " + resp.getMessage());
        }
        return resp.getData();
    }

    /**
     * 远程执行，被invoke调用
     *
     * @param request client请求
     * @return 返回 Response类
     */
    private RpcResponse invokeRemote(RpcRequest request) {
        TransportClient client = null;
        RpcResponse resp = null;
        try {
            client = selector.select();

            byte[] reqData = encoder.encode(request);
            InputStream inData = client.write(new ByteArrayInputStream(reqData));

            byte[] inBytes = IOUtils.readFully(inData, inData.available());

            resp = decoder.decode(inBytes, RpcResponse.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            resp = new RpcResponse();
            resp.setCode(1);
            resp.setMessage("RpcClient got error: "
                    + e.getClass()
                    + " : " + e.getMessage()
            );
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return resp;
    }
}
