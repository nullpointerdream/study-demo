package com.mycrawler.mycrawler.juc.guava;

import java.util.concurrent.TimeUnit;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 16:55
 **/

public class SimpleLimiter {

    // 当前令牌桶中的令牌数量
    long storedPermits = 0;
    // 令牌桶的容量
    long maxPermits = 3;

    // 下一令牌产生时间
    long next = System.nanoTime();

    private int size;

    public SimpleLimiter(){
        this(1);
    }
    // 发放令牌间隔：纳秒
    long interval = 1000_000_000;

    public SimpleLimiter(int size) {
        this.size=size;
    }

    public synchronized long resver(long now){
        if(now>next){
            // 新产生的令牌数
            long newPermits=(now-next)/interval; // 新令牌增加到令牌桶
            storedPermits=Math.min(maxPermits, storedPermits + newPermits);
            // 将下一个令牌发放时间重置为当前时间
            next=now;
        }

        // 能够获取令牌的时间
        long at=next;
        // 令牌桶中能提供的令牌
        long fb= Math.min(1, storedPermits);
        // 令牌净需求：首先减掉令牌桶中的令牌
        long nr = 1 - fb;
        // 重新计算下一令牌产生时间
        next = next + nr*interval;
        // 重新计算令牌桶中的令牌
        this.storedPermits -= fb;
        // 返回线程需要等待的时间
        return at;
    }

    public void acquire() {
        long now = System.nanoTime();
        long resver = resver(now);
        long waitTime = Math.max(resver - now, 0); // 按照条件等待 if(waitTime > 0) {

        try {
            TimeUnit.NANOSECONDS.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
