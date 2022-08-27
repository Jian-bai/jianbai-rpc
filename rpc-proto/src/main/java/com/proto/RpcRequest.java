package com.proto;


import lombok.Data;

/**
 * 表示RPC的一个请求
 * @author JianBai
 * @date 2022/8/27 0:00
 */
@Data
public class RpcRequest {

    private ServiceDescriptor service;
    private Object[] parameters;
}
