package com.proto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示一个 目标容器
 * @author JianBai
 * @date 2022/8/27 0:00
 */
@Data
@AllArgsConstructor
public class Container {

    private String host;
    private int port;
}
