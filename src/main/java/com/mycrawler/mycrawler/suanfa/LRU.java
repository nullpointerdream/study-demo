package com.mycrawler.mycrawler.suanfa;

/**
 * @program: mycrawler
 * @description: LRU算法
 * @author: 陈家乐
 * @create: 2019-10-23 14:54
 **/

public class LRU {
    private Node head;

    private int size;

    private int capacity;

    public LRU(int capacity){
        this.capacity=capacity;
    }

    public String get(String key){
        Node node=head;
        String val=null;
        while (node!=null ){
            if(node.key.equals(key)){
              val= node.value;
              break;
            }
            node=node.next;
        }
        if(val!=null){
            delete(key);
            add(key,val);
        }
        return val;
    }

    public void add(String key,String value){
        if(capacity==size) {
            delete(key);
        }
        Node node=new Node(key,value);
        addFirst(node);
    }

    public void delete(String str){
        Node p=head;
        Node q=head.next;
        size--;
        while (q!=null ){
           if(q.key.equals(str)||q.next==null){
               p.next=q.next;
               return;
           }
           p=q;
           q=q.next;
        }


    }

    private void addFirst(Node node){
        node.next=head;
        head=node;
        size++;
    }

    class Node{

        private Node next;

        private String key;

        private String value;

        public Node(String str,String value){
            this.key=str;
            this.value=value;
        }
    }

    public static void main(String[] args) {
        LRU lru =new LRU(5);
        lru.add("1","1");
        lru.add("2","2");
        lru.add("3","3");
        lru.add("4","4");
        lru.add("5","5");
        lru.add("6","6");
        lru.add("7","7");

        String s = lru.get("4");
        lru.add("8","8");
        lru.add("9","9");
    }

}
