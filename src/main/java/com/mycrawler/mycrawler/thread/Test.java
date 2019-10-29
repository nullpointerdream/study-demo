package com.mycrawler.mycrawler.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

class Person {
    private int age;   // 年龄
    private String identityCardID;  // 身份证号码
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public String getIdentityCardID() {
        return identityCardID;
    }
 
    public void setIdentityCardID(String identityCardID) {
        this.identityCardID = identityCardID;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof  Person)) {
            return false;
        }
        Person personObj = (Person) obj;
        return this.age == personObj.getAge() && this.identityCardID.equals(personObj.getIdentityCardID());
    }

    @Override
    public int hashCode() {
        return age * 37 + identityCardID.hashCode();
    }
}
 
 
public class Test {
 
    public static void main(String[] args) {
        Person jack = new Person();
        jack.setAge(10);
        jack.setIdentityCardID("42118220090315234X");
 
        Set<Person> personSet = new HashSet<Person>();
        personSet.add(jack);
 
        jack.setAge(11);
 
        System.out.println(personSet.contains(jack));

        ReentrantLock reentrantLock =new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
 
    }
}