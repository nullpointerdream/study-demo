package com.mycrawler.mycrawler.doubt;

public class Creator {
  public static void main(String[] args){  
      for(int i = 0; i < 100; i++) {  
          Creature creature = new Creature();
      }  
      System.out.println(Creature.getNum());  
  }  
}  
class Creature{  
  private static long num = 0;
  public Creature(){
      num++;  
  }  
  public static long getNum(){
      return num;  
  }  
}