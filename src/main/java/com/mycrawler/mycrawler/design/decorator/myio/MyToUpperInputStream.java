package com.mycrawler.mycrawler.design.decorator.myio;

import java.io.*;

/**
 * @program: mycrawler
 * @description: 自定义小写转大写流
 * @author: 陈家乐
 * @create: 2019-03-14 09:51
 **/

public class MyToUpperInputStream extends FilterInputStream{


    public MyToUpperInputStream(InputStream inputStream) throws FileNotFoundException {
        super(inputStream);

        // this.inputStream=inputStream;
    }


    @Override
    public int read() throws IOException {
        int c = 0;

        if ((c = super.read()) != -1) {
                return Character.toUpperCase( c );
        } else {
            return -1;
        }

    }

    @Override
    public int read(byte[] bytes) throws IOException {
        int read = super.read(bytes);
        if(read!=-1){
            for (int i=0;i<bytes.length;i++)
            bytes[i]=(byte) Character.toUpperCase((char) bytes[i]);
        }
        return read;
    }
}
