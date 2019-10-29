package com.mycrawler.mycrawler.doubt;

public class HelloGoodbye {
  public static void main(String[] args){  
      try{  
          System.out.println("Hello,World!");  
          System.exit(0);  
      }finally{  
          System.out.println("Goodbye,world!");  
      }  
    }  
}