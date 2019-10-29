package com.mycrawler.mycrawler.suanfa.redblackbst;


/**
 * @program: mycrawler
 * @description: 二叉查找树数据结构
 * @author: 陈家乐
 * @create: 2018-11-29 15:26
 **/
public class RedBlackBST<Key extends Comparable<Key>,Value> {

    private Node root;
    private static final boolean RED =true;
    private static final boolean BLACK =false;

    private class Node{
       private Key key;
       private Value value;
       private Node left,right;
       private int N;
       private boolean color;

       public Node(Key key,Value value,int N,boolean color){
           this.key=key;
           this.value=value;
           this.N=N;
           this.color=color;
       }
    }

    private  boolean isRed(Node x){
        if(x==null){
            return false;
        }
        return x.color==RED;
    }


    public int size(){
        return size(root);
    }

    private int size(Node x) {
        if(x==null){
            return 0;
        }
        return x.N;
    }

    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x, Key key) {
        if(x==null){
            return null;
        }
        int i = x.key.compareTo(key);
        if(i<0){
            return get(x.left,key);
        }else if(i>0){
            return get(x.right,key);
        }else {
            return x.value;
        }

    }

    public Node rotateLeft(Node h){
        Node x  =h.right;
        h.right =x.left;
        x.left=h;
        x.color=h.color;
        x.left.color=RED;
        x.N=h.N;
        h.N=size(h.left)+size(x.right)+1;
        return  x;
    }

    public Node rotateRight(Node h){
        Node x  =h.left;
        h.left=x.right;
        x.right=h;
        x.color=h.color;
        x.right.color=RED;
        x.N=h.N;
        h.N=size(h.left)+size(x.right)+1;
        return  x;
    }


    public void flipColor(Node h){
        h.left.color=BLACK;
        h.right.color=BLACK;
        h.color=RED;
    }

    public void put(Key key,Value value){
        root= put(root,key,value);
        root.color=BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if(x==null){
            return new Node(key,value,1,RED);
        }
        int i = x.key.compareTo(key);
        if(i==0){
            x.value=value;
        }else if(i<0){
            x.left=put(x.left,key,value);
        }else{
            x.right=put(x.right,key,value);
        }

        if(isRed(x.right) && !isRed(x.left)){
            rotateLeft(x);
        }
        if(isRed(x.right) && isRed(x.left.left)){
            rotateRight(x.left);
        }
        if(isRed(x.right) && isRed(x.left)){
            flipColor(x);
        }
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }

    //  这是用于删除节点的flipColor方法，该方法用于节点的合并，将父节点中的部分给与子节点

    public void moveflipColors(Node h ){
        h.color = BLACK;
        h.left.color = RED;
        h.right.color = RED;
    }

    ////这个函数是用来处理2节点的
    private Node moveRedLeft(Node h){
        /**
         * 当前节点的左右子节点都是2-节点，左右节点需要从父节点中借一个节点
         * 如果该节点的右节点的左节点是红色节点，说明兄弟节点不是2-节点，可以从兄弟节点中借一个
         * 对于当前节点，从其兄弟节点（父节点的右节点）中借一个节点
         */
        moveflipColors(h);     // 从父节点中借一个
        if(isRed(h.right.left)){    // 判断兄弟节点，如果是红节点，也从兄弟节点中借一个
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            moveflipColors(h);  //  在从兄弟节点借了一个以后，我们就需要还一个节点给父节点了，因为一开始从父节点那里借了一个
        }
        return h;
    }

    // 如果根节点的左右子节点是2-节点，我们可以将根设为红节点，
    // 这样才能进行后面的moveRedLeft操作，因为左子要从根节点借一个
    public void deleteMin(){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMin(root);
        root.color = BLACK;  // 借完以后，我们将根节点的颜色复原
    }

    private Node deleteMin(Node x){
        if(x.left == null) return null;
        //保证node或node.left为红节点，注意是从父节点入手
        if(!isRed(x.left) && !isRed(x.left.left))    // 判断x的左节点是不是2-节点
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);  //删除后修复红色右节点（链接）
    }

    private Node balance(Node h){
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.right) && !isRed(h.left)) h=rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h=rotateRight(h);
        if (isRed(h.left) && isRed(h.right))    flipColor(h);
        h.N = size(h.left)+size(h.right)+1;
        return h;
    }



}
