package com.mycrawler.mycrawler.jvm;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @program: mycrawler
 * @description: 直接内存和堆内存比较
 * @author: 陈家乐
 * @create: 2019-01-21 13:30
 **/

public class AccessDirectBuffer {

        public void directAccess(){
            long starttime=System.currentTimeMillis();
            ByteBuffer byteBuffer=ByteBuffer.allocateDirect(500);
            for(int i=0;i<100000;i++){
                for(int j=0;j<99;j++){
                    byteBuffer.putInt(i);
                }
                byteBuffer.flip();
                for(int j=0;j<99;j++){
                    byteBuffer.getInt();
                }
                byteBuffer.clear();

            }
            long end=System.currentTimeMillis();
            System.out.println("directAccess"+(end-starttime));

        }

    public void bufferAccess(){
        long starttime=System.currentTimeMillis();
        ByteBuffer byteBuffer=ByteBuffer.allocate(500);
        for(int i=0;i<100000;i++){
            for(int j=0;j<99;j++){
                byteBuffer.putInt(i);
            }
            byteBuffer.flip();
            for(int j=0;j<99;j++){
                byteBuffer.getInt();
            }
            byteBuffer.clear();

        }
        long end=System.currentTimeMillis();
        System.out.println("bufferAccess"+(end-starttime));

    }


    public void directAllocate(){
        long starttime=System.currentTimeMillis();

        for(int i=0;i<200000;i++){
            ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1000);

        }
        long end=System.currentTimeMillis();
        System.out.println("directAllocate"+(end-starttime));

    }

    public void bufferAllocate(){
        long starttime=System.currentTimeMillis();

        for(int i=0;i<200000;i++){
            ByteBuffer byteBuffer=ByteBuffer.allocate(1000);

        }
        long end=System.currentTimeMillis();
        System.out.println("bufferAllocate"+(end-starttime));

    }


    public static void main(String[] args) {
        AccessDirectBuffer accessDirectBuffer=new AccessDirectBuffer();
        accessDirectBuffer.directAccess();
        accessDirectBuffer.bufferAccess();
        accessDirectBuffer.bufferAllocate();
        accessDirectBuffer.directAllocate();
    }
}
