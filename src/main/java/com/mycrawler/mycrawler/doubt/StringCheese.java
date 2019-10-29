package com.mycrawler.mycrawler.doubt;

import java.io.UnsupportedEncodingException;

public class StringCheese {

    /**
     * 这里的罪魁祸首就是String(byte[])构造器。
     * 有关它的规范描述道：“在通过解码使用平台缺省字符集的指定byte数组来构造一个新的String时，
     * 该新String的长度是字符集的一个函数，因此，它可能不等于byte数组的长度。
     * 当给定的所有字节在缺省字符集中并非全部有效时，这个构造器的行为是不确定的”[Java-API]。
     * @param args
     * @throws UnsupportedEncodingException
     */
   public static void main(String[] args) throws UnsupportedEncodingException {
       byte bytes[] = new byte[256];
       for(int i = 0; i < 256; i++)  
             bytes[i] = (byte)i;  
       String str = new String(bytes);  
       for(int i = 0, n = str.length(); i < n; i++)  
             System.out.print((int)str.charAt(i)+ " ");
       System.out.println();
       String str2 = new String(bytes,"ISO-8859-1");
       for(int i = 0, n = str2.length(); i < n; i++)
           System.out.print((int)str2.charAt(i)+ " ");
   }  
} 