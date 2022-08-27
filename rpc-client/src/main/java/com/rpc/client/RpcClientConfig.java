package com.rpc.client;

import com.proto.Container;
import com.rpc.codec.Decoder;
import com.rpc.codec.Encoder;
import com.rpc.codec.JsonDecoder;
import com.rpc.codec.JsonEncoder;
import com.rpc.transport.HttpTransportClient;
import com.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * rpc客户端配置
 * @author JianBai
 * @date 2022/8/27 12:40
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class <? extends Encoder> encoderClass = JsonEncoder.class;
    private Class <? extends Decoder> decoderClass = JsonDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    private int connectCount =1;

    private List<Container> servers = Arrays.asList(new Container("127.0.0.1",3000));

}
