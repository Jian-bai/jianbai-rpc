package com.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * json序列化
 * @author JianBai
 * @date 2022/8/26 23:55
 */
public class JsonEncoder implements Encoder{

    /**
     * 序列化，将json对象转为二进制数组
     */
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
