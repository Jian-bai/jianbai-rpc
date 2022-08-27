package com.rpc.server;

import com.proto.RpcRequest;
import com.proto.ServiceDescriptor;
import com.rpc.common.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 管理rpc暴露的服务
 *
 * @author JianBai
 * @date 2022/8/27 13:10
 */
@Slf4j
public class ServiceManager {
    private final Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.init(interfaceClass, method);
            services.put(sdp, sis);
            log.info("register service: {} {}", sdp.getClazz(), sdp.getMethod());
        }
    }


    public ServiceInstance lookup(RpcRequest request) {
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
