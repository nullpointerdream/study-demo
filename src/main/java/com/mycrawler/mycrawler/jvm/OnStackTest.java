package com.mycrawler.mycrawler.jvm;

/**
 * @program: mycrawler
 * @description: 栈上分配对象 栈上分配的基本思想，是将线程私有的对象，打散分配到栈上，
 * 分配在栈上的函数调用结束后对象会自行销毁，
 * 不需要垃圾回收接入，从而提升性能。对于大量的零散小对象，
 * 栈上分配提供了一种很好的对象分配优化策略，但由于和堆空间相比，
 * 栈空间较小，因此大对象无法也不适合在栈上分配
 * 栈上分配依赖逃逸分析和标量替换的实现，
 * 同时必须在server模式下才能启用。参数-XX:+DoEscapeAnalysis启用逃逸分析
 * -XX:+EliminateAllocations开启标量替换(默认打开).
 * -server -XX:+PrintGC -Xms10M -Xmx10M -XX:+DoEscapeAnalysis -XX:-UseTLAB -XX:+EliminateAllocations
 * @author: 陈家乐
 * @create: 2019-01-21 10:42
 **/

public class OnStackTest {

    public static class User{
        private int age;
        private String name;
    }

    public static void alloc(){
        User u =new User();
        u.age=12;
        u.name="123";
    }

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
