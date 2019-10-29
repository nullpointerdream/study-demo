package com.mycrawler.mycrawler.design.decorator.myio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-14 09:56
 **/

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream=new MyToUpperInputStream(new FileInputStream(new File("/Users/chenjiale/soar.log")));
        /*int c=0;
        while ((c=inputStream.read())!=-1){
            System.out.print((char)c);
        }*/

        byte[] bytes=new byte[1024];
        while (inputStream.read(bytes)!=-1){
            System.out.println(new String(bytes));
        }

    }
}
