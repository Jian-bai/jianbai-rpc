package com.rpc.codec;

/**
 * @author JianBai
 * @date 2022/8/26 23:55
 */
public interface Encoder {

    /**
     * 序列化对象
     * @param obj --
     * @return --
     */
    byte[] encode(Object obj);
}
