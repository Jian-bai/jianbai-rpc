package com.proto;


import lombok.Data;

/**
 * 表示RPC的响应
 * @author JianBai
 * @date 2022/8/27 0:00
 */
@Data
public class RpcResponse {
    /**
     * 服务返回编码： 0-成功 非0失败
     */
    private int code = 0;
    /**
     * error or ok?
     */
    private String message = "ok";
    private Object data;
}
