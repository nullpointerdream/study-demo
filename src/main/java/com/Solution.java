package com;


import com.alibaba.fastjson.JSONObject;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.*;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


class Solution {
    int rel = 0;
    int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        //inOrder(root);

        return rel;

    }

    public int rob(int[] nums) {
       return rob(nums,0,nums.length);
    }


    public int rob(int[] nums,int start,int end) {
        if (nums.length == 0) {
            return 0;
        }
        int max = nums[start];
        int dp_1 = nums[start];
        int dp_2=0;

        for (int i = start+1; i < end; i++) {
            max = Math.max(dp_1,dp_2+nums[i]);
            dp_2=dp_1;
            dp_1=max;
        }
        return max;


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
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                String poll = queue.poll();

                if (lockSet.contains(poll)) {
                    continue;
                }
                if (target.equals(poll)) {
                    return step;
                }

                for (int j = 0; j < poll.length(); j++) {
                    String up = up(poll, j);
                    if (!addSet.contains(up)) {
                        queue.offer(up);
                        addSet.add(up);
                    }
                    String down = down(poll, j);
                    if (!addSet.contains(down)) {
                        queue.offer(down);
                        addSet.add(down);
                    }
                }


            }
            step++;
        }
        return -1;
    }

    public String up(String srt, int index) {
        char[] chars = srt.toCharArray();
        char ch = chars[index];
        if (ch == '9') {
            chars[index] = '0';
        } else {
            chars[index] += 1;
        }
        return new String(chars);
    }

    public String down(String srt, int index) {
        char[] chars = srt.toCharArray();
        char ch = chars[index];
        if (ch == '0') {
            chars[index] = '9';
        } else {
            chars[index] -= 1;
        }
        return new String(chars);
    }


    LinkedList<Integer> list = new LinkedList();
    List<List<Integer>> rel1 = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> list = new LinkedList();
        permute(nums, list);
        return rel1;
    }

    public void permute(int[] nums, LinkedList<Integer> list) {
        if (list.size() == nums.length) {
            rel1.add(new LinkedList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            permute(nums, list);
            list.removeLast();
        }

    }

    List<List<String>> nQueens = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(board, 0);
        return nQueens;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

    void backtrack(char[][] board, int row) {
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
        for (int i = 0; i < bord.length; i++) {
            char c = bord[i][col];
            if (c == 'Q') {
                return false;
            }
        }
        //左上是否冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            char c = bord[i][j];
            if (c == 'Q') {
                return false;
            }
        }
        //右上上是否冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < bord.length; i--, j++) {
            char c = bord[i][j];
            if (c == 'Q') {
                return false;
            }
        }
        return true;
    }

    int sum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return sum;
        }
        list.add(root.val);
        dfs(root);
        return sum;

    }

    public void dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            int num = 0;
            for (Integer integer : list) {
                num = num * 10 + integer;
            }
            sum += num;
            return;
        }
        if (root.left != null) {
            list.add(root.left.val);
            dfs(root.left);
            list.removeLast();
        }
        if (root.right != null) {
            list.add(root.right.val);
            dfs(root.right);
            list.removeLast();
        }

    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        int level = 0;
        levelOrder(root, level, lists);
        return lists;
    }

    private void levelOrder(TreeNode root, int level, List<List<Integer>> lists) {
        if (root == null) {
            return;
        }

        if (lists.size() <= level) {
            lists.add(new ArrayList<>());
        }
        List<Integer> list = lists.get(level);
        list.add(root.val);
        levelOrder(root.left, level + 1, lists);
        levelOrder(root.right, level + 1, lists);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode pop = stack.pop();
            list.add(pop.val);
            cur = pop.right;
        }
        return list;
    }

    private void midIteator(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        midIteator(root.left, list);
        list.add(root.val);
        midIteator(root.right, list);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int pathValue = 0;
        getPathList(lists, stack, pathValue, sum, root);
        return lists;
    }

    private void getPathList(List<List<Integer>> lists, Stack<Integer> stack, int pathValue, int sum, TreeNode root) {
        if (root == null) {
            return;
        }
        pathValue += root.val;
        stack.push(root.val);
        if (root.right == null && root.left == null && pathValue == sum) {
            lists.add(new ArrayList<>(stack));
        }
        getPathList(lists, stack, pathValue, sum, root.left);
        getPathList(lists, stack, pathValue, sum, root.right);
        stack.pop();
    }


    public static void containsNearbyDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        for (int num : nums) {
            System.out.print(num + " ");
        }


    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pStack = new LinkedList<>();
        LinkedList<TreeNode> qStack = new LinkedList<>();
        TreeNode pRoot = root;
        TreeNode qRoot = root;
        getPath(pRoot, p, pStack, false);
        getPath(qRoot, q, qStack, false);
        TreeNode node = null;
        while (!pStack.isEmpty() && !qStack.isEmpty()) {
            if (pStack.peekFirst() == qStack.peekFirst()) {
                node = qStack.removeFirst();
                pStack.removeFirst();
            } else {
                return node;
            }
        }
        return node;
    }

    private static void getPath(TreeNode root, TreeNode node, LinkedList<TreeNode> stack, Boolean finsh) {
        if (root == null || finsh) {
            return;
        }
        stack.addLast(root);
        if (root == node) {
            finsh = true;
        }
        getPath(root.left, node, stack, finsh);
        getPath(root.right, node, stack, finsh);
        stack.removeLast();
    }

    private List<String> list3 = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        binaryTreePaths(root, "");
        return list3;
    }

    public void binaryTreePaths(TreeNode root, String str) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list3.add(str + root.val);
            return;
        }

        str = str + root.val;
        binaryTreePaths(root.left, str + "->");
        binaryTreePaths(root.right, str + "->");


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
        int[][][] dp = new int[prices.length][k + 1][2];
        int min = Integer.MIN_VALUE;
        dp[0][k][1] = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            for (int j = k; j > 0; j--) {
                if (i == 0) {
                    dp[i][j][0] = Math.max(0, min + prices[i]);
                    dp[i][j][1] = Math.max(min, -prices[i]);
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }

            }
        }
        return dp[prices.length - 1][k][0];
    }


    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int left = intervals[0][0];
        int right = intervals[0][1];
        int cover = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (left <= interval[0] && right >= interval[1]) {
                cover++;
            } else if (left <= interval[0] && right < interval[1]) {
                right = interval[1];
            } else if (right < interval[0]) {
                left = interval[0];
                right = interval[1];
            }
        }
        return intervals.length - cover;
    }

    public int[][] merge(int[][] intervals) {

        List<int[]> lists = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (right >= interval[0] && right < interval[1]) {
                right = interval[1];
            } else if (right < interval[0]) {
                lists.add(new int[]{left, right});
                left = interval[0];
                right = interval[1];
            }
        }
        lists.add(new int[]{left, right});
        int[][] arr = new int[lists.size()][2];
        for (int i = 0; i < arr.length; i++) {
            int[] ints = lists.get(i);
            for (int j = 0; j < 2; j++) {
                arr[i] = ints;
            }
        }
        return arr;

    }




    public TreeNode deleteNode(TreeNode root, int key) {
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            }else {
                TreeNode min = getMin(root.right);
                root.val=min.val;
                root.right=deleteNode(root.right,min.val);
            }
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }


    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val < root.left.val) {
            return false;
        }
        if (root.right != null && root.val > root.right.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);

    }


    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }


    private TreeNode getMin(TreeNode right) {
        if(right.left==null){
            return right;
        }
        return getMin(right.left);
    }


    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        while (root.right != null) {
            root = root.right;
        }
        right.right = right;
    }


    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0;
        int j = 0;
        List<int[]> list = new ArrayList<>();
        while (j < B.length && i < A.length) {

            int x1 = A[j][0];
            int y1 = A[j][1];

            int x2 = B[j][0];
            int y2 = B[j][1];

            if (y2 >= x1 || x2 <= y1) {
                list.add(new int[]{Math.max(x1, x2), Math.max(y2, y1)});
            }
            if (y1 > y2) {
                j++;
            } else {
                i++;
            }
        }
        int[][] arr = new int[list.size()][2];
        for (int k = 0; k < arr.length; k++) {
            arr[k] = list.get(k);
        }
        return arr;
    }




    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, target, 4, 0);

    }



    public List<List<Integer>> nSum(int[] nums, int target, int n, int start) {
        List<List<Integer>> twoList = new ArrayList<>();
        if (n == 2) {
            int i = start;
            int j = nums.length - 1;
            while (i < j) {
                int a = nums[i];
                int b = nums[j];
                int sum = a + b;
                if (sum > target) {
                    j--;
                } else if (sum < target) {
                    i++;
                } else {
                    List<Integer> integers = new ArrayList<>();
                    integers.add(a);
                    integers.add(b);
                    twoList.add(integers);
                    while (++i < j && nums[i] == a) {

                    }
                    while (--j > i && nums[j] == b) {

                    }
                }
            }
            return twoList;
        } else {
            for (int i = start; i < nums.length; i++) {
                List<List<Integer>> subList = nSum(nums, target - nums[i], n - 1, i + 1);
                 for (List<Integer> one : subList) {
                    one.add(nums[i]);
                    twoList.add(one);
                }
                while (i < nums.length - 1 && nums[i + 1] == nums[i]) {
                    i++;
                }
            }
            return twoList;
        }


    }

    public int coinChange(int[] coins, int amount) {
        //凑n元至少需要次数 1 ,2, 5  11
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];

    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[0][m] = 1;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int n = obstacleGrid.length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            if (i > 0 && dp[i - 1][0] == 0) {
                dp[i][0] = 0;
            } else if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            if (i > 0 && dp[0][i - 1] == 0) {
                dp[0][i] = 0;
            } else if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
            }
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(obstacleGrid[i][j]==1){
                    dp[i][j]=0;
                }else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n-1][m-1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid[0].length;
        int n = grid.length;
        int[][] dp = new int[n][m];
        dp[0][0]=grid[0][0];
        for(int i=1;i<n;i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }

        for(int i=1;i<m;i++){
            dp[0][i]+=dp[0][i-1]+grid[0][i];
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[n-1][m-1];
    }

    int reasult =0 ;
    public int findTargetSumWays(int[] nums, int S) {
        if(nums.length==0){
            return 0;
        }
        findTargetSumWays(nums,0,S);
        return reasult;
    }

    private void findTargetSumWays(int[] nums, int i, int s) {
        if(nums.length==i){
            if(s==0){
                reasult++;
            }
            return;
        }

        s+=nums[i];
        findTargetSumWays(nums,i+1,s);
        s-=nums[i];

        s-=nums[i];
        findTargetSumWays(nums,i+1,s);
        s+=nums[i];
    }


    public int minDistance(String word1, String word2) {
        int s1 = word1.length();
        int s2 = word2.length();
        int[][] dp = new int[s1 + 1][s2 + 1];
        for (int i = 0; i <= s1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= s2; i++) {
            dp[0][i] = i;
        }

        for(int i=1;i<=s1;i++){
            for(int j=1;j<=s2;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else {
                    dp[i][j] = min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1])+1;
                }
            }
        }
        return dp[s1][s2];


    }

    public int min(int a,int b,int c){
        return Math.min(a,Math.min(b,c));
    }

    public int findTargetSumWays2(int[] nums, int S) {
        int sum =0;
        for (int num : nums) {
            sum+=num;
        }

        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }

        int n = (S+sum)/2;


        //只在前 i 个物品中选择，若当前背包的容量为 j，则最多有 x 种方法可以恰好装满背包。
        int[][] dp = new int[nums.length+1][n+1];
        for(int i=0;i<=nums.length;i++){
            dp[i][0]=1;
        }
        for(int i=1;i<=nums.length;i++){
            for(int j=0;j<=n;j++){
                if(nums[i-1]<=j){
                    dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i-1]];
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[nums.length][n];



    }

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int rel = o1[0] - o2[0];
                if (rel == 0) {
                    return o2[1] - o1[1];
                }
                return rel;
            }
        });
        //第n个最长子序列
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int rel = 0;
        for (int i = 0; i < envelopes.length; i++) {
            rel = Math.max(dp[i], rel);
        }

        return rel;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        //s1 0n.. s2 m..
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int dp_1 = nums[0];
        int dp_2 = 0;
        int dp =dp_1;
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp_1, nums[i] + dp_2);
            dp_2 = dp_1;
            dp_1 = dp;
        }
        return dp;
    }


    public int waysToStep(int n) {
        if(n<=2){
            return n;
        }
        if(n==3){
            return 4;
        }
        long dp_1 =1l;
        long dp_2 =2l;
        long dp_3 =4l;
        Long dp =0L;
        for(int i=4;i<=n;i++){
            dp=(dp_1+dp_2+dp_3)%1000000007;;
            dp_1=dp_2;
            dp_2=dp_3;
            dp_3=dp;
        }
        return dp.intValue();
    }




    public static void main(String[] args) throws InterruptedException {

        int[] str = new int[1024*512];
        ReferenceQueue queue = new ReferenceQueue();
        // 创建虚引用，要求必须与一个引用队列关联
        PhantomReference pr = new PhantomReference(str, queue);
        System.out.println(pr.get());
        System.out.println(queue.poll());
        System.gc();
        // Thread.sleep(6000);
        System.out.println(queue.poll());

        ThreadGroup threadGroup = new ThreadGroup("");
        threadGroup.setMaxPriority(1);
        Thread thread = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {

            }
        });

        Thread thread2 = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {

            }
        });
        //threadGroup.
        System.out.println(thread.getPriority());

        System.out.println(thread2.getPriority());
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(1);
        int[] ints = {2,1,1,2};
        System.out.println(JSONObject.toJSONString(new Solution().waysToStep(61)));
        // System.out.println(JSONObject.toJSONString(new Solution().maxProfit_k_1(new int[]{2,3,6,5,0,3})));
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.left.right = new TreeNode(2);
        int[][] arr = new int[][]{{1, 4}, {3, 6}, {2, 8}};
        new Solution().removeCoveredIntervals(arr);
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

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        //前 i 个物品，当前背包的容量为 j 时，能否将背包装满
        boolean[][] dp = new boolean[nums.length][sum+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=true;
        }
        for(int i=1;i<dp.length;i++){
            for (int j = 1; j <= sum; j++) {
                if(nums[i]>j) {
                    dp[i][j] =dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j]|| dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[nums.length-1][sum];

    }







}

