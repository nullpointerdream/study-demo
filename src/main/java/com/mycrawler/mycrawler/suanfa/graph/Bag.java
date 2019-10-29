package com.mycrawler.mycrawler.suanfa.graph;

import java.util.Iterator;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-09-17 10:53
 **/

public  class Bag<T> implements Iterable<T>{
    Node<T> head;

    public void add(T v){
        Node node= new Node(v);
        node.next=head;
        head=node;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    class Node<T>{
        T val;
        Node next;

        public Node(T v){
            this.val=v;
        }
    }

    private class ListIterator implements Iterator<T> {

        private Node current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public T next() {
            T temp = (T) current.val;
            current = current.next;
            return temp;
        }
    }
}
