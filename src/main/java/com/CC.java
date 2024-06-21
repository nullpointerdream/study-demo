package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author chenjl40
 * @Date 2023/10/18 11:52
 */
public class CC {

    static String all= "";
    public static void main(String[] args) throws IOException {
       List<String> s = new ArrayList<>();
       s.add("5MS1H14180");
       s.add("5MS1H141801");
       s.add("5MS1H141802");
       try {
           s.stream().parallel().forEach(bean->{
               try {
                   Thread.sleep(10000L);
                   System.out.println("forEach"+System.currentTimeMillis());
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           });
       }catch (Exception e){

       }finally {
           System.out.println("finally"+System.currentTimeMillis());
       }





    }

}
