package com.mycrawler.mycrawler.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    //obj为委托类对象; 
    private Object obj; 
 
    public DynamicProxy(Object obj) {
        this.obj = obj;
    } 
 
    @Override 
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("before"); 
        Object result = method.invoke(obj, args); 
        System.out.println("after"); 
        return result; 
    }
} 
