package com.mycrawler.mycrawler.bitmap;

import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-09-02 13:33
 **/

public class TestBitMap {

    //-Xms64m -Xmx64m -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError

    @Test
    public void hashMapTest(){
        long star = System.currentTimeMillis();

        Set<Integer> hashset = new HashSet<>(10000000) ;

        for (int i = 0; i < 10000000; i++) {
            hashset.add(i) ;
        }
        Assert.assertTrue(hashset.contains(1000));
        Assert.assertTrue(hashset.contains(20000));
        Assert.assertTrue(hashset.contains(300000));

        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }


    @Test
    public void hashBitMap(){
        long star = System.currentTimeMillis();

       BitMap bitMap =new BitMap(10000000);
        for (int i = 0; i < 10000000; i++) {
            bitMap.add(i) ;
        }
        Assert.assertTrue(bitMap.contain(1000));
        Assert.assertTrue(bitMap.contain(20000));
        Assert.assertTrue(bitMap.contain(10000000));

        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }



}
