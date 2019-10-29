package com.mycrawler.mycrawler.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: mycrawler
 * @description: 大对象进入老年代
 * @author: 陈家乐
 * @create: 2019-01-22 13:30
 * -Xmx32M -Xms32M -XX:+PrintGCDetails -XX:+UseSerialGC -XX:-UseTLAB -XX:PretenureSizeThreshold=1000
 **/

public class PretenureSizeThreshold {
        public static final Integer _1k=1024;

        public static void main(String[] args) {
            Map<Integer, Byte[]> map = new HashMap<>();
            for (int i = 0; i < 5 * _1k; i++) {
                Byte[] bytes = new Byte[_1k];
                map.put(i, bytes);
            }

    }
}
