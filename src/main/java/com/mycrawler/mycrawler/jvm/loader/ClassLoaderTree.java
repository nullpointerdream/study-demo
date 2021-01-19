package com.mycrawler.mycrawler.jvm.loader;

/**
 * @program: study-demo
 * @description: 类加载器
 * @author: chenjiale
 * @create: 2020-12-01 09:30
 **/
public class ClassLoaderTree {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderTree.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }
}
