package com.mycrawler.mycrawler.jvm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @program: mycrawler
 * @description: 软引用
 * @author: 陈家乐
 * @create: 2019-01-21 14:12
 **/

public class SoftRef {
    public static class User{
        public User(int age,String name){
            this.age=age;
            this.name=name;
        }
        private int age;
        private String name;

        @Override
        public String toString() {
            return this.age+"->age"+this.name+"name";
        }
    }

    public static void main(String[] args) {
        User user= new User(1,"eww");
        WeakReference<User> userSoftReference=new WeakReference<>(user);
        user=null;
        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("after gc");
        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println(userSoftReference.get());
        ArrayList a =new ArrayList();
        a.remove(null);
    }
}
