package com.mycrawler.mycrawler.doubt;

class Counter{
  private static int count;  
  public static void increment(){count++;}  
  public static int getCount(){return count;}  
}  
class Dog extends Counter{

  public void woof(){increment();}
}  
class Cat extends Counter{
  public void meow(){increment();}
}  
public class Ruckus {  
  public static void main(String[] args){  
      Dog dog = new Dog();
      dog.woof();  
      Cat cat = new Cat();
      cat.meow();  
      System.out.println(dog.getCount()+"woof");  
      System.out.println(cat.getCount()+"meow");  
  }  
}