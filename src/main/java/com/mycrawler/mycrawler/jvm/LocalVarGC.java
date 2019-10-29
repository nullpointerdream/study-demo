package com.mycrawler.mycrawler.jvm;

/**
 * @program: mycrawler
 * @description: 局部变量gc回收  -XX:+PrintGC -Xms10M -Xmx10M
 * @author: 陈家乐
 * @create: 2019-01-21 09:57
 **/

public class LocalVarGC {

    public void localvargc1(){
        byte[] a= new byte[6*1024*1024];
       System.gc();
    }
    public void localvargc2(){
        byte[] a= new byte[6*1024*1024];
        a=null;
        System.gc();
    }
    public void localvargc3(){
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
    }
    public void localvargc4(){
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int c=0;
        System.gc();
    }
    public void localvargc5(){
        localvargc1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC localVarGC=new LocalVarGC();
        localVarGC.localvargc5();
    }

}
