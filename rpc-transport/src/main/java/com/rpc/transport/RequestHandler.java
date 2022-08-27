package com.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author JianBai
 * @date 2022/8/27 3:54
 */
public interface RequestHandler {

    /**
     * 处理请求
     * @param beRes -
     * @param toResp -
     */
    void onRequest(InputStream beRes, OutputStream toResp);
}
