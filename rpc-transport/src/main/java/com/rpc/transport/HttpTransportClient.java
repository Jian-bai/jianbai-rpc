package com.rpc.transport;


import com.proto.Container;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author JianBai
 * @date 2022/8/27 12:40
 */
public class HttpTransportClient implements TransportClient {

    private String url;

    @Override
    public void init(Container container) {
        this.url = "http://"+container.getHost()+":"+container.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpConn=(HttpURLConnection) new URL(url).openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");

            httpConn.connect();

            org.apache.commons.io.IOUtils.copy(data,httpConn.getOutputStream());

            int resultCode = httpConn.getResponseCode();
            if(resultCode == HttpURLConnection.HTTP_OK){
                return httpConn.getInputStream();
            }else{
                return httpConn.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
