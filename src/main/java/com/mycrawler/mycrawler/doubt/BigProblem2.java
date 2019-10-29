package com.mycrawler.mycrawler.doubt;

import java.math.BigInteger;

public class BigProblem2 {
  public static void main(String[] args){  
      BigInteger a = new BigInteger("5000");
      BigInteger b = new BigInteger("1000");
      BigInteger c = BigInteger.ZERO;
      c.add(a);c.add(b);
      System.out.println(c);


  }  
}  