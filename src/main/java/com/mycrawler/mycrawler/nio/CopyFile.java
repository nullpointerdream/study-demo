package com.mycrawler.mycrawler.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: mycrawler
 * @description: nio读写文件
 * @author: 陈家乐
 * @create: 2019-03-08 13:38
 **/

public class CopyFile {

    public static void copyFile(String inputFile,String outFile) throws IOException {
        FileInputStream fileInputStream =new FileInputStream(inputFile);
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        FileChannel inchannel = fileInputStream.getChannel();
        FileChannel outchannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
        while (inchannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            outchannel.write(byteBuffer);
            byteBuffer.clear();
        }

    }

    public static void main(String[] args) throws IOException {
        copyFile("/Users/chenjiale/soar.log","/Users/chenjiale/soar_bak.log");
    }
}
