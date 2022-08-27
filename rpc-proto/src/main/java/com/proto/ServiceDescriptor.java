package com.proto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;


/**
 * RPC 服务描述 >>>> 做remote 调用
 *
 * @author JianBai
 * @date 2022/8/27 0:00
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {

    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    /**
     * 抽取目标对象方法信息 生成ServiceDescriptor
     *
     * @param clazz  -
     * @param method --
     * @return --
     */
    public static ServiceDescriptor init(Class<?> clazz, Method method) {

        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class<?>[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);

        return sdp;
    }


}
