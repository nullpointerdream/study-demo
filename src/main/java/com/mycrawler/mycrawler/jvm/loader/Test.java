package com.mycrawler.mycrawler.jvm.loader;

import java.lang.reflect.Method;

public class Test {

    public void testClassIdentity() {
        String path = "/Users/chenjiale/Downloads";
        FileSystemClassLoader classLoader1 = new FileSystemClassLoader(path);
        FileSystemClassLoader classLoader2 = new FileSystemClassLoader(path);
        String className = "Sample";
        try {
            Class<?> class1 = classLoader1.loadClass(className);
            Class<?> class2 = classLoader2.loadClass(className);
            Object obj1 = class1.newInstance();
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test().testClassIdentity();


    }
}