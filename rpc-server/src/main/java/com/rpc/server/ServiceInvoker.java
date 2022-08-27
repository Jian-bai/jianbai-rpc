package com.rpc.server;

import com.proto.RpcRequest;
import com.rpc.common.util.ReflectionUtils;

/**
 * 管理rpc暴露的服务
 * @author JianBai
 * @date 2022/8/27 12:54
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, RpcRequest request){
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters()
        );
    }
}
