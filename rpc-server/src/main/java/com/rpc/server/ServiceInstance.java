package com.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;


/**
 * @author JianBai
 * @date 2022/8/27 12:40
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
