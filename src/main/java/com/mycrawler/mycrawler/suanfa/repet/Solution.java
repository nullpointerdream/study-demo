package com.mycrawler.mycrawler.suanfa.repet;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

class ListNode {
      int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

class TreeNode {
   int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) { val = x; }
}
public class Solution {
    int count=0;

    public int compress(char[] chars) {
        int index=0;
        int count=1;
        for(int i=0;i<chars.length-1;i++){
            char aChar = chars[i];
            if(chars[i]==chars[i+1]){
                count++;
            }else if(chars[i]!=chars[i+1]){
                chars[index++] = aChar;
                if (count > 1) {
                    for (char c : (String.valueOf(count)).toCharArray()) {
                        chars[index++] =c;
                    }
                }
                count=1;
            }
        }
        return count;
    }



    public static void main(String[] args) {
        Solution solution =new Solution();
        TreeNode treeNode=new TreeNode(1);
       treeNode.left=new TreeNode(2);
       treeNode.right=new TreeNode(3);
       treeNode.left.left=null;
       treeNode.left.right=new TreeNode(5);

        System.out.println(JSONObject.toJSONString(solution.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'})));
    }
    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}