package com.mycrawler.mycrawler.util;

import java.util.Stack;

public class MyQueue {

    private Stack<Integer> s;
    private Stack<Integer> o;

    /** Initialize your data structure here. */
    public MyQueue() {
        s=new Stack();
        o=new Stack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(o.empty()){
            while (!s.empty()){
                o.push(s.pop());
            }
        }
        return o.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(o.empty()){
            while (!s.empty()){
                o.push(s.pop());
            }
        }
        return o.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s.empty()&&o.empty();
    }

    public static void main(String[] args) {
        MyQueue myQueue= new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }
}

