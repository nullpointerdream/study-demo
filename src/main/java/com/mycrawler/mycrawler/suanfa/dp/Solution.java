package com.mycrawler.mycrawler.suanfa.dp;

/**
 * @program: mycrawler
 * @description: 动态规划
 * @author: 陈家乐
 * @create: 2019-09-12 10:38
 **/

public class Solution {

    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N+1];//数字为N 是否获胜
        for(int i = 1; i <= N; i++){
            for(int x = 1; x < i; x++){//存在满足 0 < x < N
                if(i%x == 0 && !dp[i-x]){// 且 N % x == 0 ，用 N - x 替换黑板上的数字 N 。
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }


    public boolean isSubsequence(String s, String t) {
        if(s.isEmpty()){
            return true;
        }
        //dp[i][j]为s的从头开始到i的子字符串是否为t从头开始到j的子字符串的子序列
        boolean[][] dp = new boolean[s.length()+1][t.length()+1];
        //初始化
        for(int j=0;j<=t.length();j++){
            dp[0][j]=true;
        }

        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=t.length();j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else {
                    dp[i][j]=dp[i][j-1];
                }

            }
        }
        return dp[s.length()][t.length()];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }

        for(int i=0;i<n;i++){
            dp[0][i]=1;
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid[0].length;
        int n=obstacleGrid.length;
        int[][] dp = new int[n][m];
        dp[0][0]=obstacleGrid[0][0]==1?0:1;
        for(int i=1;i<m;i++){
            if(obstacleGrid[0][i]==1){
                dp[0][i]=0;
            }else {
                dp[0][i]=dp[0][i-1];
            }
        }
        for(int i=1;i<n;i++){
            if(obstacleGrid[i][0]==1){
                dp[i][0]=0;
            }else {
                dp[i][0]=dp[i-1][0];
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
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                else if(i == 0)  grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if(j == 0)  grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= s.length(); i++) {
            //如果该位不为'0'，说明该位单独成字母合法
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            //如果后两位能组成"1x"（x为任意数字）或者"2x"（x小于7），说明最后两位组成字母合法
            if ((s.charAt(i - 2) == '1') || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];



    }

    public int minCostClimbingStairs(int[] cost) {

        //状态 dp[i]表示前i层最低花费
        int[] dp= new int[cost.length];

        //初始值
        dp[0]=cost[0];
        dp[1]=cost[1];
        for(int i=2;i<cost.length;i++){
            dp[i]=cost[i]+Math.min(dp[i-1],dp[i-2]);
        }
        return Math.min(dp[cost.length-1],dp[cost.length-2]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("1001"));
    }
}
