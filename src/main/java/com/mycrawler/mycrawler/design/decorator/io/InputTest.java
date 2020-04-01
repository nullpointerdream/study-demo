package com.mycrawler.mycrawler.design.decorator.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-03-31 09:48
 **/
public class InputTest {
    public static void main(String[] args) throws IOException {
        LowerCaseInputStream lowerCaseInputStream = new LowerCaseInputStream(new FileInputStream("/Users/chenjiale/Downloads/a.txt"));
        int b=0;
        while ((b=lowerCaseInputStream.read())!=-1){
            System.out.print((char)b);
        }
    }
}
