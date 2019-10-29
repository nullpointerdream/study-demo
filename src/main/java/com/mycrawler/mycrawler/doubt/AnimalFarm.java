package com.mycrawler.mycrawler.doubt;

public class AnimalFarm {
  public static void main(String[] args){  
      String pig = "length: 10";  
      String dog = "length: " + pig.length();  
      System.out.println("Animalare equals:"+ pig == dog);

      System.out.println("Animal areequals:"+ (pig == dog));

      System.out.println("Animal areequals:"+ pig.equals(dog));
  }  
}  