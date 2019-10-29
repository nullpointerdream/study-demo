package com.mycrawler.mycrawler.doubt;

public class DosEquis {

  //第2个和第3个操作数具有相同的类型，那么它就是条件表达式的类型。
  // 如果一个操作数的类型是T，T表示byte、short、char，而另一个操作数是一个int类型的常量表达式，它的值可以用类型T表示。
  // 否则，将对操作数类型进行二进制数字提升，而条件表达式的类型就是第2个和第3个操作数被提升之后的类型。
  public static void main(String[] args) {  
      char x = 'X';  
      int i = 0;  
      System.out.println(true?x:0);  
      System.out.println(false?x:126);
      System.out.println(false?i:x);
      System.out.println(true?i:x);
  }
}