package com.mycrawler.mycrawler.suanfa.bst;


import java.util.Stack;

/**
 * @program: mycrawler
 * @description: 二叉查找树数据结构
 * @author: 陈家乐
 * @create: 2018-11-29 15:26
 **/
public class BST<Key extends Comparable<Key>,Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int i = x.key.compareTo(key);
        if (i < 0) {
            return get(x.left, key);
        } else if (i > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }

    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int i = key.compareTo(x.key);
        if (i == 0) {
            x.value = value;
        } else if (i < 0) {
            x.left = put(x.left, key, value);
        } else {
            x.right = put(x.right, key, value);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Node min() {
        return min(root);
    }

    private Node min(Node x) {
        if (x.key == null) {
            return x;
        }
        return min(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }


    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int i = x.key.compareTo(key);
        if (i > 0) {
            x.right = delete(x.right, key);
        } else if (i < 0) {
            x.left = delete(x.left, key);
        } else {
            if (x.left == null) {
                return x.right;
            } else if (x.right == null) {
                return x.left;
            }
            Node tmp = x;
            x = min(x.right);
            x.left = tmp.left;
            x.right = deleteMin(tmp.right);
        }
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }

    /**
     * @Description: 前序遍历
     * @Param: [node]
     * @return: void
     * @Author: 陈家乐
     * @Date: 2019/6/27
     */
    public void preOrderMethodOne(Node node) {
        if (node != null) {
            System.out.print(node.value + "--");
            if (node.left != null) {
                medOrderMethodOne(node.left);
            }
            if (node.right != null) {
                medOrderMethodOne(node.right);
            }
        }
    }


    public void preOrderMethodTwo(Node node) {
        Stack<Node> stack=new Stack();
        if (node != null ) {
           stack.push(node);
           while (!stack.empty()){
               Node pop = stack.pop();
               System.out.print("--"+pop.value);
               if(pop.right!=null){
                   stack.push(pop.right);
               }
               if(pop.left!=null){
                   stack.push(pop.left);
               }

           }
        }
    }

    public void postOrderMethodOne(Node node) {
        Stack<Node> stack=new Stack();
        if (node != null) {

            if(node.left!=null){
                stack.push(node.left);
            }
            while (!stack.empty()){

            }
            if (node.left != null) {
                stack.push(node);
            }
            if (node.right != null) {
                postOrderMethodOne(node.right);
            }
            System.out.print(node.value + "--");
        }
    }

    public void postOrderMethodTwo(Node node) {
        if (node != null) {

            if (node.left != null) {
                postOrderMethodOne(node.left);
            }
            if (node.right != null) {
                postOrderMethodOne(node.right);
            }
            System.out.print(node.value + "--");
        }
    }


    /**
    * @Description: 中序遍历
    * @Param: [node]
    * @return: void
    * @Author: 陈家乐
    * @Date: 2019/6/27
    */
    public void medOrderMethodOne(Node node) {
        if (node != null) {
            if (node.left != null) {
                medOrderMethodOne(node.left);
            }
            System.out.print(node.value + "--");
            if (node.right != null) {
                medOrderMethodOne(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST<Integer,String> bst=new BST<>();
        bst.put(1,"1");
        bst.put(2,"2");
        bst.put(3,"3");
        bst.put(4,"4");
        bst.medOrderMethodOne(bst.root);
        System.out.println();
        bst.preOrderMethodOne(bst.root);
        System.out.println();
        bst.postOrderMethodOne(bst.root);
        System.out.println();
        bst.preOrderMethodTwo(bst.root);
        System.out.println();

    }
}



