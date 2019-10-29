package com.mycrawler.mycrawler.doubt;

import java.math.BigDecimal;

public class Change {
    //对于货币计算，要使用int、long、BigDecimal。一定要用BigDecimal（String）构造器，
    // 而千万不要用BigDecimal（double）。后一个构造器将用它的参数的精确值来创建一个实例。
  public static void main(String[] args) {  

      System.out.println(2.0-1.1);
      System.out.printf("%.2f\n",2.0-1.1);
      BigDecimal bigNum1 = new BigDecimal("2.0");
      BigDecimal bigNum2 = new BigDecimal("1.1");
      System.out.println(bigNum1.subtract(bigNum2));
  }  
}