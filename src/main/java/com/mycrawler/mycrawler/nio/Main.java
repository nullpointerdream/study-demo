package com.mycrawler.mycrawler.nio;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-24 11:52
 **/

public class Main {
    public static void main(String[] args) {
        System.out.println(SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    }
}
