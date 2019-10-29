package com.mycrawler.mycrawler.doubt;

public class Increment {

    /**
     * 当++操作符被置于一个变量值之后时，其作用就是一个后缀增量操作符,
     * 表达式j++的值等于j在执行增量操作之前的初始值。因此，前面提到的赋值语句首先保存j的值，
     * 然后将j设置为其值加1，最后将j复位到它的初始值。换句话说，这个赋值操作等价于下面的语句序列：
     * @param args
     */
  public static void main(String[] args) {  
      int j = 0;  
      for(int i = 0; i < 100; i++) {  
          j= j++;  
      }  
      System.out.println(j);  
      }  
}  