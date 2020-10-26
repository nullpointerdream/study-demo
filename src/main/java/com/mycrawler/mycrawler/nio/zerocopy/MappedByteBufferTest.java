package com.mycrawler.mycrawler.nio.zerocopy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-10-09 15:47
 **/
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        String s = "/czb/server_logs/motorcadeaccount/info.log";
        File file = new File(s);
        long len = file.length();
        byte[] ds = new byte[(int) len];
        MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file,"r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, len);
        for (int offset = 0; offset < len; offset++) {
            byte b = mappedByteBuffer.get();
            ds[offset] = b;
        }

        Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
        while (scan.hasNext()) {
            System.out.print(scan.next() + " ");
        }
    }
}
