package com.mycrawler.mycrawler.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: mycrawler
 * @description: 直接缓冲区复制
 * @author: 陈家乐
 * @create: 2019-03-08 13:55
 **/

public class DirectCopyFile {

    static public void main( String args[] ) throws Exception {


        String infile = null;
        String outfile = null;

        if (args.length<2) {
            infile = "/Users/chenjiale/soar.log";
            outfile = "/Users/chenjiale/soar_ack.log";
        }else {
             infile = args[0];
             outfile = args[1];
        }

        FileInputStream fin = new FileInputStream( infile );
        FileOutputStream fout = new FileOutputStream( outfile );

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );

        while (true) {
            buffer.clear();
            int r = fcin.read( buffer );
            if (r==-1) {
                break;
            }
            buffer.flip();
            fcout.write( buffer );
        }
    }
}
