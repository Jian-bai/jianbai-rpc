package com.rpc.server;

import com.rpc.codec.Decoder;
import com.rpc.codec.Encoder;
import com.rpc.codec.JsonDecoder;
import com.rpc.codec.JsonEncoder;
import com.rpc.transport.HttpTransportServer;
import com.rpc.transport.TransportServer;
import lombok.Data;

/**
 * rpc server config
 * @author JianBai
 * @date 2022/8/27 13:14
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;

    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private int port =3000;
}
