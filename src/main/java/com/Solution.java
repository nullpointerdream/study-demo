package com;

/**
 * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
 *
 * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。

 */

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
class Solution {

    public int uniquePaths(int m, int n) {
        int[][] arr=new int[n][m];
         for(int i=0;i<m;i++){
             arr[0][i]=1;
         }
        for(int i=0;i<n;i++){
            arr[i][0]=1;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                arr[i][j]=arr[i-1][j]+arr[i][j-1];
            }
        }
        return arr[n-1][m-1];
    }

    public static void main(String[] args) {
        System.out.println(String.format("adas%s","a"));
    }


}

