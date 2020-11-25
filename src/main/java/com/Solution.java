package com;


import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 */

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
class Solution {
    int rel=0;
    int count=0;
    public int kthSmallest(TreeNode root, int k) {
        count=k;
        //inOrder(root);

        return rel;

    }

    public int openLock(String[] deadends, String target) {
        int step = 0;
        Set<String> lockSet = new HashSet<>();
        Set<String> addSet = new HashSet<>();

        for (String deadend : deadends) {
            lockSet.add(deadend);
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i =0;i<size;i++){

                String poll = queue.poll();

                if(lockSet.contains(poll)){
                    continue;
                }
                if(target.equals(poll)){
                    return step;
                }

                for(int j=0;j<poll.length();j++){
                    String up = up(poll, j);
                    if(!addSet.contains(up)){
                        queue.offer(up);
                        addSet.add(up);
                    }
                    String down = down(poll, j);
                    if(!addSet.contains(down)){
                        queue.offer(down);
                        addSet.add(down);
                    }
                }


            }
            step++;
        }
        return -1;
    }

    public String up(String srt ,int index){
        char[] chars = srt.toCharArray();
        char ch = chars[index];
        if(ch == '9'){
            chars[index]='0';
        }else {
            chars[index] += 1;
        }
        return new String(chars);
    }

    public String down(String srt ,int index){
        char[] chars = srt.toCharArray();
        char ch = chars[index];
        if(ch == '0'){
            chars[index]='9';
        }else {
            chars[index]-=1;
        }
        return new String(chars);
    }


    LinkedList<Integer> list = new LinkedList();
    List<List<Integer>> rel1 =new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> list = new LinkedList();
        permute(nums,list);
        return rel1;
    }

    public void permute(int[] nums,LinkedList<Integer> list ) {
        if(list.size()==nums.length){
            rel1.add(new LinkedList<>(list));
            return;
        }
        for(int i=0;i<nums.length;i++) {
            if(list.contains(nums[i])){
                continue;
            }
            list.add(nums[i]);
            permute(nums,list);
            list.removeLast();
        }

    }

    List<List<String>> nQueens = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(board,0);
        return nQueens;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

    void backtrack(char[][]board, int row) {
        // 触发结束条件
        if (row == board.length) {
            nQueens.add(construct(board));
            return;
        }
        int n = board.length;
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            board[row][col] = 'Q';
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }

    boolean isValid(char[][] bord, int row, int col) {
        //判断列是否冲突
        for(int i=0;i<bord.length;i++){
            char c = bord[i][col];
            if(c=='Q'){
                return false;
            }
        }
        //左上是否冲突
        for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            char c = bord[i][j];
            if(c=='Q'){
                return false;
            }
        }
        //右上上是否冲突
        for(int i=row-1,j=col+1;i>=0&&j<bord.length;i--,j++){
            char c = bord[i][j];
            if(c=='Q'){
                return false;
            }
        }
        return true;
    }

    int sum =0 ;
    public int sumNumbers(TreeNode root) {
        if(root==null){
            return sum;
        }
        list.add(root.val);
        dfs(root);
        return sum;

    }

    public void dfs(TreeNode root) {
        if(root.left==null&&root.right==null){
            int num=0;
            for (Integer integer : list) {
                num=num*10+integer;
            }
            sum+=num;
            return;
        }
        if(root.left!=null) {
            list.add(root.left.val);
            dfs(root.left);
            list.removeLast();
        }
        if(root.right!=null) {
            list.add(root.right.val);
            dfs(root.right);
            list.removeLast();
        }

    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        int level=0;
        levelOrder(root,level,lists);
        return lists;
    }

    private void levelOrder(TreeNode root, int level, List<List<Integer>> lists) {
        if(root == null){
            return;
        }

        if(lists.size()<=level){
            lists.add(new ArrayList<>());
        }
        List<Integer> list = lists.get(level);
        list.add(root.val);
        levelOrder(root.left,level+1,lists);
        levelOrder(root.right,level+1,lists);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur!=null||!stack.empty()){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            TreeNode pop = stack.pop();
            list.add(pop.val);
            cur = pop.right;
        }
        return list;
    }

    private void midIteator(TreeNode root, List<Integer> list) {
        if(root == null){
            return;
        }
        midIteator(root.left,list);
        list.add(root.val);
        midIteator(root.right,list);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> lists = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();
            int pathValue = 0;
            getPathList(lists,stack,pathValue,sum,root);
            return lists;
    }

    private void getPathList(List<List<Integer>> lists, Stack<Integer> stack, int pathValue, int sum, TreeNode root) {
        if(root == null){
            return;
        }
        pathValue += root.val;
        stack.push(root.val);
        if(root.right==null && root.left==null && pathValue == sum){
            lists.add(new ArrayList<>(stack));
        }
        getPathList(lists,stack,pathValue,sum,root.left);
        getPathList(lists,stack,pathValue,sum,root.right);
        stack.pop();
    }


    public static void containsNearbyDuplicate(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            boolean flag=true;
            for(int j=0;j<n-i-1;j++){
                if(nums[j]>nums[j+1]){
                    int temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                    flag=false;
                }
            }
            if(flag){
                break;
            }
        }
        for (int num : nums) {
            System.out.print(num+" ");
        }


    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pStack = new LinkedList<>();
        LinkedList<TreeNode> qStack = new LinkedList<>();
        TreeNode pRoot = root;
        TreeNode qRoot = root;
        getPath(pRoot,p,pStack,false);
        getPath(qRoot,q,qStack,false);
        TreeNode node = null;
        while (!pStack.isEmpty()&&!qStack.isEmpty()){
            if(pStack.peekFirst()==qStack.peekFirst()){
                node=qStack.removeFirst();
                pStack.removeFirst();
            }else {
                return node;
            }
        }
        return node;
    }

    private static void getPath(TreeNode root, TreeNode node, LinkedList<TreeNode> stack,Boolean finsh) {
        if(root==null || finsh){
            return;
        }
        stack.addLast(root);
        if(root == node ){
            finsh=true;
        }
        getPath(root.left,node,stack,finsh);
        getPath(root.right,node,stack,finsh);
        stack.removeLast();
    }

    private List<String> list3 = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        binaryTreePaths(root,"");
        return list3;
    }

    public void binaryTreePaths(TreeNode root,String str) {
        if(root==null){
            return;
        }
        if(root.left==null&&root.right==null){
            list3.add(str+root.val);
            return;
        }

        str= str+root.val;
        binaryTreePaths(root.left,str+"->");
        binaryTreePaths(root.right,str+"->");


    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int firstPosition = findFirstPosition(nums, target);
        int lastPosition = findLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }


    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // ① 不可以直接返回，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else {
                // 此时 nums[mid] > target，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            }
        }

        // 此时 left 和 right 的位置关系是 [right, left]，注意上面的 ①，此时 left 才是第 1 次元素出现的位置
        // 因此还需要特别做一次判断
        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }




    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 只有这里不一样：不可以直接返回，应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else if (nums[mid] < target) {
                // 应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else {
                // 此时 nums[mid] > target，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            }
        }

        if (right != -1 && nums[right] == target) {
            return right;
        }
        return -1;
    }

    public int maxProfit(int k, int[] prices) {
        int[][][] dp = new int[prices.length][k+1][2];
        int min =Integer.MIN_VALUE;
        dp[0][k][1] = Integer.MIN_VALUE;
        for(int i=0;i<prices.length;i++){
            for(int j=k;j>0;j--){
                if(i==0){
                    dp[i][j][0] = Math.max(0,min+prices[i]);
                    dp[i][j][1] = Math.max(min,-prices[i]);
                }else {
                    dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1] = Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
                }

            }
        }
        return dp[prices.length-1][k][0];
    }







    public static void main(String[] args) {

        // System.out.println(JSONObject.toJSONString(new Solution().maxProfit_k_1(new int[]{2,3,6,5,0,3})));
        System.out.println(JSONObject.toJSONString(new Solution().maxProfit(2,new int[]{2,4,1})));
        TreeNode treeNode = new TreeNode(3);
        treeNode.left=new TreeNode(1);
        treeNode.right=new TreeNode(4);
        treeNode.left.right=new TreeNode(2);
       /*treeNode.left.left=new TreeNode(5);
        treeNode.left.right=new TreeNode(1);*/
       /* treeNode.right.left=new TreeNode(0);
        treeNode.right.right=new TreeNode(8);
        treeNode.left.left.left=null;
        treeNode.left.left.right=null;
        treeNode.right.left.left=new TreeNode(7);
        treeNode.right.left.right=new TreeNode(4);*/
        //lowestCommonAncestor(treeNode,treeNode.left,treeNode.right.left.right);
        //System.out.println(new Solution().binaryTreePaths(treeNode));
        //System.out.println(JSONObject.toJSONString(new Solution().kthSmallest(treeNode,1)));



    }









}

