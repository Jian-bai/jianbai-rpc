package com.rpc.codec;


import com.alibaba.fastjson.JSON;

/**
 * json反序列化
 * @author JianBai
 * @date 2022/8/26 23:55
 */
public class JsonDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
