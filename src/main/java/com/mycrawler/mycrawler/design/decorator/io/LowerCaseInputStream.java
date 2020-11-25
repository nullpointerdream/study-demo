package com.mycrawler.mycrawler.design.decorator.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: study-demo
 * @description: 大写转小写
 * @author: chenjiale
 * @create: 2020-03-31 09:41
 **/
public class LowerCaseInputStream extends FilterInputStream {

    public LowerCaseInputStream(InputStream file){
        super(file);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c==-1?c:Character.toLowerCase(c));
    }


}
