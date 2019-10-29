package com.mycrawler.mycrawler.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: mycrawler
 * @description: 老年对象进入老年代
 * @author: 陈家乐
 * @create: 2019-01-22 11:39
 * -Xmx1024M -Xms1024M -XX:+PrintGCDetails -XX:+PrintHeapAtGC
 **/

public class MaxTenuringThreshold {
    public static final Integer _1k=1024;
    public static final Integer _1M=1024*1024;

    public static void main(String[] args) {
        Map<Integer, Byte[]> map = new HashMap<>();
        for (int i = 0; i < 5 * _1k; i++) {
            Byte[] bytes = new Byte[_1k];
            map.put(i, bytes);
        }
        for (int k = 0; k < 17; k++) {
            for (int i = 0; i < 270; i++) {
                Byte[] bytes = new Byte[_1M];
            }
        }
    }
}
