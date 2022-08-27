package com.rpc.client;

import com.proto.Container;
import com.rpc.common.util.ReflectionUtils;
import com.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 轮询
 * @author JianBai
 * @date 2022/8/27 12:40
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {
    /**
     * 已经连接好的client
     */
    private final List<TransportClient> clientList;

    public RandomTransportSelector() {
        clientList =  new ArrayList<>();
    }


    @Override
    public synchronized void init(List<Container> containers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);

        for (Container container : containers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.init(container);
                clientList.add(client);
            }
            log.info("connect server: {}", container);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clientList.size());
        return clientList.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clientList.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : clientList) {
            client.close();
        }
        clientList.clear();
    }
}
