package com.rpc.codec;

/**
 * @author JianBai
 * @date 2022/8/26 23:55
 */
public interface Decoder {

    /**
     * 反序列化，将二进制数组转为对象
     * @param bytes 二进制数组
     * @param clazz 待转的类型
     * @param <T> 泛型
     * @return 返回的对象
     */
    <T>T decode(byte[] bytes,Class<T> clazz);
}
