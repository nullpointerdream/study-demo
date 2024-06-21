package com;


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

class Solution {
    int rel = 0;
    int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
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

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0;
        int j = 0;
        List<int[]> list = new ArrayList<>();
        int[] tmp = A[0];
        while (j < B.length) {
            int[] intsB = B[j];
            if (tmp[1] < intsB[0]) {
                i++;
            } else if (tmp[0] <= intsB[0] && tmp[1] <= intsB[1]) {
                list.add(new int[]{intsB[0], tmp[1]});
                i++;
            } else if (tmp[0] > intsB[0] && tmp[1] <= intsB[1]) {
                list.add(new int[]{tmp[0], tmp[1]});
                i++;
            } else if (tmp[0] <= intsB[1] && tmp[1] >= intsB[1]) {
                list.add(new int[]{tmp[0], intsB[1]});
                i++;
            } else if (tmp[0] > intsB[1]) {
                j++;
            }

            if (i == A.length) {
                break;
            } else {
                tmp = A[i];
            }
        }
        int[][] arr = new int[list.size()][2];
        return null;
    }


    public int getMaximumGenerated(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 0; i <= n / 2; i++) {
            if (2 * i <= n) {
                dp[2 * i] = Math.max(dp[i], dp[2 * i]);
            }
            if (2 * i + 1 <= n) {
                dp[2 * i + 1] = Math.max(dp[i] + dp[i + 1], dp[2 * i + 1]);
            }

        }
        int max = 0;
        for (int i = 0; i <= n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //定义图
        int[][] graph = new int[n][n];
        //存到达i最低花费
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        for(int[] flight : flights){
            //构造图
            graph[flight[0]][flight[1]] = flight[2];
        }
        Queue<int[]> queue = new LinkedList<>();
        //起点入队列（中转次数，起点，花费）
        queue.offer(new int[]{k, src, 0});
        while(!queue.isEmpty()){
            //取出队首
            int[] info = queue.poll();
            //如果中转次数>1 && 并且该起点有邻接的下一站
            if(info[0] >= 0 && graph[info[1]].length != 0){
                //取出所有邻接站
                int[] tmpDst = graph[info[1]];
                for(int i = 0; i < tmpDst.length; i++){
                    //==0说明没赋值，跳过
                    if(tmpDst[i] == 0) continue;
                    int nextPrice = info[2] + tmpDst[i];
                    if(nextPrice >= result[i]){
                        continue;
                    }
                    result[i] = nextPrice;
                    //入队
                    queue.offer(new int[]{info[0] - 1, i, nextPrice});
                }
            }
        }
        return result[dst] == Integer.MAX_VALUE ? -1 : result[dst];
    }


    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = i == j ? 0 : INF;
            }
        }

        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }
        boolean[] visit = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        for (int i = 0; i < n; i++) {
            int x = -1;
            for (int j = 0; j < n; j++) {
                if (!visit[j] && (x == -1 || dist[j] < dist[x])) {
                    x = j;
                }
            }
            visit[x] = true;
            // 用点 t 的「最小距离」更新其他点
            for (int z = 0; z < n; z++) {
                dist[z] = Math.min(dist[z], dist[x] + g[x][z]);
            }
        }
        // 找到距离最远的点
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] visit = new boolean[nums.length];
        dfs(nums,new ArrayList<Integer>(),visit);
        return lists;
    }

    private void dfs(int[] nums, ArrayList<Integer> tmp, boolean[] visit) {
        if(tmp.size()==nums.length){
            lists.add(new ArrayList<>(tmp));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(visit[i]){
                continue;
            }
            tmp.add(nums[i]);
            visit[i] =true;
            dfs(nums,tmp,visit);
            tmp.remove(tmp.size()-1);
            visit[i] =false;
            while (i+1<nums.length && nums[i]==nums[i+1]){
                i++;
            }
        }
    }


    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int count =0;
        int left = 0;
        int right =people.length-1;
        while (left<right){
            if(people[left]+people[right]<=limit){
                count++;
                left++;
                right--;
            }else {
                count++;
                right--;
            }
        }

        return left==right?count+1:count;
    }


    public static void main(String[] args) {

        // System.out.println(JSONObject.toJSONString(new Solution().maxProfit_k_1(new int[]{2,3,6,5,0,3})));
        //System.out.println(JSONObject.toJSONString(new Solution().maxProfit(2, new int[]{2, 4, 1})));
        int[][] ints = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        new Solution().setZeroes(ints);
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


    public void setZeroes(int[][] matrix) {
        int m = matrix[0].length;
        int n = matrix.length;
        Set<Integer> x = new HashSet<>();
        Set<Integer> y = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    x.add(i);
                    y.add(j);
                }
            }
        }
        for (Integer i : x) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = 0;
            }
        }
        for (Integer j : y) {
            for (int i = 0; i < n; i++) {
                matrix[i][j] = 0;
            }
        }

    }

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if ("".equals(sentence1) || "".equals(sentence2)) {
            return true;
        }
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");
        int l = 0;
        int r = s2.length-1;
        for (int i = 0; i < s1.length; i++) {
            if (s1[i].equals(s2[l])) {
                l++;
                if (i == s1.length - 1 || l == s2.length) {
                    return true;
                }
            } else {
                break;
            }
        }
        for (int i = s1.length - 1; i >= 0; i--) {
            if (s1[i].equals(s2[r])) {
                r--;
                if (r == l - 1 || i == 0) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;

    }


    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int x : nums1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Integer cur = map.ceilingKey(nums2[i] + 1);
            if (cur == null) {
                cur = map.ceilingKey(-1);
            }
            ans[i] = cur;
            map.put(cur, map.get(cur) - 1);
            if (map.get(cur) == 0) {
                map.remove(cur);
            }
        }
        return ans;
    }


}

