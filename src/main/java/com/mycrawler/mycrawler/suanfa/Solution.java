package com.mycrawler.mycrawler.suanfa;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

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

    /**
    * @Description: 两数之和
    * @Param: [nums, target]
    * @return: int[]
    * @Author: 陈家乐
    * @Date: 2019/3/25
    */
    public int[] twoSum(int[] nums, int target) {
         Map<Integer,Integer> map=new HashMap<>();
         for(int i=0;i<nums.length;i++){
             int other=target-nums[i];
             if(map.containsKey(other)&&i!=map.get(other)){
                 return new int[]{map.get(other),i};
             }
             map.put(nums[i],i);
         }
         return null;
    }


    /**
     * @Description: 两数之和，有序数组
     * @Param: [nums, target]
     * @return: int[]
     * @Author: 陈家乐
     * @Date: 2019/3/25
     */
    public int[] twoSum2(int[] num, int target) {

        int[] indice = new int[2];
        if (num == null || num.length < 2) return indice;
        int left = 0, right = num.length - 1;
        while (left < right) {
            int v = num[left] + num[right];
            if (v == target) {
                indice[0] = left + 1;
                indice[1] = right + 1;
                break;
            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }
        return indice;
    }
    /**
    * @Description: 整数反转
    * @Param: [x]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/3/25
    */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
    * @Description: 回文数
    * @Param: [x]之反转一半
    * @return: boolean
    * @Author: 陈家乐
    * @Date: 2019/3/25
    */
    public boolean isPalindrome(int x) {
            if(x<0||(x%10==0&&x!=0)){
                return false;
            }
            int result=0;
            while (result<x){
                result=result*10+x%10;
                x=x/10;
            }

            return result/10==x||result==x;

    }

    /**
    * @Description: 罗马数字转整数
    * @Param: [s]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/3/25
    */
    public int romanToInt(String s) {
        int nums[]=new int[s.length()];
        for(int i=0;i<s.length();i++){
            switch (s.charAt(i)){
                case 'M':
                    nums[i]=1000;
                    break;
                case 'D':
                    nums[i]=500;
                    break;
                case 'C':
                    nums[i]=100;
                    break;
                case 'L':
                    nums[i]=50;
                    break;
                case 'X' :
                    nums[i]=10;
                    break;
                case 'V':
                    nums[i]=5;
                    break;
                case 'I':
                    nums[i]=1;
                    break;
            }
        }
        int sum=0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1])
                sum-=nums[i];
            else
                sum+=nums[i];
        }
        return sum+nums[nums.length-1];
    }

    /**
     * 最长不重复
     * @param s
     * Input: "abcabcbb"
     * Output: 3
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


    /**
    * @Description:
    * @Param: [strs]
    * @return: java.lang.String
    * @Author: 陈家乐
    * @Date: 2019/3/25
    */
    public String longestCommonPrefix(String[] strs) {
           if(strs.length==0){
               return "";
           }
           String str=strs[0];
           A:while (str!=null && str.length()>0) {
               for (int i = 1; i < strs.length; i++) {
                   if(!strs[i].startsWith(str)){
                       str=str.substring(0,str.length()-1);
                       continue A;
                   }
               }
               return str;
           }
           return "";

    }
    /** 
    * @Description: 有效的括号 
    * @Param: [s] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    /**
    * @Description: 合并链表
    * @Param: [l1, l2]
    * @return: com.mycrawler.mycrawler.suanfa.Solution.ListNode
    * @Author: 陈家乐
    * @Date: 2019/3/25
    */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
           ListNode listNode=new ListNode(0);
           ListNode listNode2=listNode;
           while (true){
               if(l1!=null&&l2!=null){
                   if(l1.val>l2.val){
                       listNode.next=new ListNode(l2.val);
                       l2=l2.next;
                   }else {
                       listNode.next=new ListNode(l1.val);
                       l1=l1.next;
                   }
               }else if(l1==null &&l2!=null){
                   listNode.next=new ListNode(l2.val);
                   l2=l2.next;
               }else if(l1!=null&&l2==null){
                   listNode.next=new ListNode(l1.val);
                   l1=l1.next;
               }else {
                   break;
               }
               listNode=listNode.next;
           }
           return listNode2.next;
    }
    
    /** 
    * @Description: 删除排序数组中的重复项
    * @Param: [nums] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public int removeDuplicates(int[] nums) {
        int count=1;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1]){
                nums[count++]=nums[i+1];
            }
        }
        return count;
    }
    
    /** 
    * @Description: 移除元素
    * @Param: [nums, val]
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public int removeElement(int[] nums, int val) {
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[count++]=nums[i];
            }
        }
        return count;
    }

    /**
    * @Description: 给定一个 haystack 字符串和一个 needle 字符串，
     *              在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     *              如果不存在，则返回  -1。
    * @Param: [haystack, needle]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/3/25
    */
    public int strStr(String haystack, String needle) {
         if(haystack.length()<needle.length()){
             return -1;
         }
         if(needle.length()==0){
             return 0;
         }

         int count=0;
         A:for(int i=0;i<haystack.length();i++){
             if(haystack.charAt(i)==needle.charAt(count)){
                 int size=needle.length();
                 if(haystack.length()-i<size){
                     return -1;
                 }
                 while (size>1){
                     count++;
                     if(haystack.charAt(count+i)!=needle.charAt(count)){
                         count=0;
                         continue A;
                     }
                     size--;
                 }
                 return i;
             }
         }
         return -1;
    }
    /** 
    * @Description: 搜索插入位置
    * @Param: [nums, target] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public int searchInsert(int[] nums, int target) {
           if(nums.length==1){
               return nums[0]<target?1:0;
           }
            for(int i=0;i<nums.length;i++){
                if(nums[i]==target){
                    return i;
                }else if(nums[i]<target&&i<nums.length-1&&nums[i+1]>target){
                    return i+1;
                }else if(nums[i]>target){
                    return 0;
                }
            }
            return nums.length;
    }



    /**
    * @Description: 报数
    * @Param: [n]
    * @return: java.lang.String
    * @Author: 陈家乐
    * @Date: 2019/3/25
    */
    public String countAndSay(int n) {
            if(n==1){
                return "1";
            }
            StringBuilder stringBuilder=new StringBuilder();
            String str = countAndSay(n - 1);
            for(int i=0;i<str.length();i++){
                char c = str.charAt(i);
                int count=1;
                while (i<str.length()-1&&str.charAt(i+1)==c){
                    count++;
                    i++;
                }
                stringBuilder.append(count).append(c);
            }
        return stringBuilder.toString();
    }

    /** 
    * @Description: 最大子序和
    * @Param: [nums] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public int maxSubArray(int[] nums) {
         int[] dp=new int[nums.length];
         dp[0]=nums[0];
         int max=dp[0];
         for(int i=1;i<nums.length;i++){
             dp[i]=nums[i]+dp[i-1]>0?dp[i-1]:0;
             max=Math.max(dp[i],max);
         }
         return max;
    }
    
    /** 
    * @Description: 最后一个单词的长度
    * @Param: [s] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public int lengthOfLastWord(String s) {
            String[] str=s.trim().split(" ");
            return str.length==1?str[0].length():str[str.length-1].length();
    }

    /** 
    * @Description: 加一
    * @Param: [digits] 
    * @return: int[] 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;

    }
    
    /** 
    * @Description: 二进制求和
    * @Param: [a, b] 
    * @return: java.lang.String 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public String addBinary(String a, String b) {
        int tmp=0;
        StringBuilder sb =new StringBuilder();
        int i=a.length()-1;
        int j=b.length()-1;
        while (i>=0||j>=0) {
            int numa = 0;
            int numb = 0;
            if (i >= 0) {
                numa = a.charAt(i--)-'0';
            }
            if (j >= 0) {
                numb = b.charAt(j--)-'0';
            }
            int sum = numa + numb + tmp;
            sb.append(sum % 2);
            tmp = sum / 2;
        }
         return tmp==1? sb.append(1).reverse().toString():sb.reverse().toString();


    }
    
    /** 
    * @Description: x 的平方根
    * @Param: [x] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/25
    */ 
    public int mySqrt(int x) {
        int l=0,r=x;
        while(l<r){
            int mid=(r+l)/2+1;
            if(mid>x/mid) r=mid-1;
            else l=mid;
        }
        return l;
    }

    /**
    * @Description: 爬楼梯
    * @Param: [n]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/3/26
    */
    public int climbStairs(int n) {
             if(n==1){
                 return 1;
             }
            int[] dp=new int[n+1];
            dp[1]=1;
            dp[2]=2;
            for(int i=3;i<=n;i++){
                dp[i]=dp[i-1]+dp[i-2];
            }
            return dp[n];
    }
    /**
    * @Description: 删除排序链表重复元素
    * @Param: [head]
    * @return: com.mycrawler.mycrawler.suanfa.Solution.ListNode
    * @Author: 陈家乐
    * @Date: 2019/3/26
    */
    public ListNode deleteDuplicates(ListNode head) {
          if(head==null){
              return head;
          }
          ListNode listNode =head;
          while (head.next!=null){
                if(head.val==head.next.val){
                    head.next=head.next.next;
                }else {
                  head=head.next;
                }
            }
            return listNode;
    }
    
    /** 
    * @Description: 合并两个有序数组 
    * @Param: [nums1, m, nums2, n] 
    * @return: void 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1;
        int j=n-1;
        int k = m+n-1;
        while(i >=0 && j>=0)
        {
            if(nums1[i] > nums2[j])
                nums1[k--] = nums1[i--];
            else
                nums1[k--] = nums2[j--];
        }
        while(j>=0)
            nums1[k--] = nums2[j--];

    }
    
    /** 
    * @Description: 相同的树
    * @Param: [p, q] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public boolean isSameTree(TreeNode p, TreeNode q) {
                if(p!=null&&q!=null&&p.val==q.val){
                    return isSameTree(p.left,p.left)&&isSameTree(p.right,p.right);
                }else if(p==null&&q==null){
                    return true;
                }
                return false;
    }
    
    /** 
    * @Description: 对称二叉树
    * @Param: [root] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public boolean isSymmetric(TreeNode root) {
        return root==null || isMirror(root, root);
    }
    private boolean isMirror(TreeNode root1, TreeNode root2) {
        if(root1 == null) return root2 == null;
        if(root2 == null) return root1 == null;
        if(root1.val != root2.val) return false;
        return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    /**
    * @Description:  二叉树的最大深度
    * @Param: [root]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/3/26
    */
    public int maxDepth(TreeNode root) {
            if(root==null){
                return 0;
            }
            return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }




    /**
    * @Description:二叉树的层次遍历 II
    * @Param: [root]
    * @return: List<List<Integer>>
    * @Author: 陈家乐
    * @Date: 2019/3/26
    */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(q.size()>0){
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for(int i=0; i<size; i++){
                TreeNode node = q.poll();
                list.add(node.val);
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
            result.add(0,list);
        }
        return result;
    }

    private void levelOrderBottom(TreeNode root,int level ,List<List<Integer>> lists) {
        if(root==null){
            return;
        }
        List<Integer> list=null;
        if(lists.size()>level){
            list=lists.get(level);
        }else {
            list=new ArrayList<>();
            lists.add(level,list);
        }
        list.add(root.val);
        levelOrderBottom(root.left,level+1,lists);
        levelOrderBottom(root.right,level+1,lists);
    }

    /** 
    * @Description: 将有序数组转换为二叉搜索树
    * @Param: [nums] 
    * @return: com.mycrawler.mycrawler.suanfa.TreeNode 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = buildTree(nums, l, m - 1);
        root.right = buildTree(nums, m + 1, r);
        return root;
    }

    /** 
    * @Description: 判断它是否是高度平衡的二叉树
    * @Param: [root] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public boolean isBalanced(TreeNode root) {
            if(root==null){
                return true;
            }
            return isBalanced(root.left)&&isBalanced(root.right) && Math.abs(depth(root.left)-depth(root.right))<=1;
    }
   
    private int depth(TreeNode root) {
        if(root ==null){
            return 0;
        }
        return 1+Math.max(depth(root.left),  depth(root.right));
    }

    /** 
    * @Description: 二叉树最小深度 
    * @Param: [root] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public int minDepth(TreeNode root) {
        if(root==null) return 0 ;
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if(left==0 || right==0)
            return left+right+1;
        else return Math.min(left,right)+1;
    }
    
    /** 
    * @Description: 判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
    * @Param: [root, sum] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }

    /**
    * @Description: 杨辉三角
    * @Param: [numRows]
    * @return: java.util.List<java.util.List<java.lang.Integer>>
    * @Author: 陈家乐
    * @Date: 2019/3/26
    */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists =new ArrayList<>();
        if(numRows==0){
            return lists;
        }
        lists.add(Arrays.asList(1));
        for(int i=1;i<numRows;i++){
            List<Integer> list= new ArrayList<>();
            for(int j=0;j<=i;j++){
                if(j==0||j==i){
                    list.add(1);
                }else {
                    list.add(lists.get(i-1).get(j-1)+lists.get(i-1).get(j));
                }
            }
            lists.add(list);
        }
        return lists;

    }

    /** 
    * @Description: 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
    * @Param: [rowIndex] 
    * @return: java.util.List<java.lang.Integer> 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public List<Integer> getRow(int numRows) {
        if(numRows==0){
            return Arrays.asList(1);
        }
        List<Integer> list= new ArrayList<>();
        for(int i=0;i<=numRows;i++){
            list.add(0,1);
            for(int j=1;j<i;j++){
                list.set(j,list.get(j)+list.get(j+1));
            }
        }
        return list;
    }
    
    /** 
    * @Description: 只买卖一次买卖股票的最佳时机
    * @Param: [prices] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public int maxProfit(int[] prices) {
          if(prices.length==0){
                return 0;
            }
           int[] dp=new int[prices.length];
           int minPrice=prices[0];
           for(int i=1;i<prices.length;i++){
               if(prices[i]>minPrice){
                   dp[i]=Math.max(dp[i-1],prices[i]-minPrice);
               }else {
                   dp[i]=dp[i-1];
                   minPrice=prices[i];
               }
           }
        return dp[prices.length-1];
    }

    /**
     * @Description: 可以买卖多次买卖股票的最佳时机
     * @Param: [prices]
     * @return: int
     * @Author: 陈家乐
     * @Date: 2019/3/26
     */
    public int maxProfit2(int[] prices) {
         int price=0;
         for(int i=0;i<prices.length-1;i++){
             if(prices[i+1]>prices[i]){
                 price+=prices[i]-prices[i-1];
             }
         }
         return price;

    }
    
    /**
    * @Description: 验证回文串
    * @Param: [s]
    * @return: boolean
    * @Author: 陈家乐
    * @Date: 2019/3/26
    */
    public boolean isPalindrome(String s) {
        if (s == null) return true;
        s = s.toLowerCase();
        int l = s.length();
        StringBuilder str = new StringBuilder(l);
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
                str.append(c);
            }
        }
        return str.toString().equals(str.reverse().toString());
    }

    /** 
    * @Description:
    * @Param: [nums] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/26
    */ 
    public int singleNumber(int[] nums) {
            int a=0;
            for(int i=0;i<nums.length;i++){
               a^=nums[i];
            }
            return a;
    }

    /**
    * @Description: 判读链表中的环
    * @Param: [head]
    * @return: boolean
    * @Author: 陈家乐
    * @Date: 2019/3/27
    */
    public boolean hasCycle(ListNode head) {
        if(head==null) return false;
        ListNode head1=head;
        ListNode head2=head;
        while (head2.next!=null&&head2.next.next!=null){
            head1=head1.next;
            head2=head2.next.next;
            if(head1==head2){
                return true;
            }
            head=head.next;
        }

        return false;

    }
    
    /** 
    * @Description: 找到两个单链表相交的起始节点。
    * @Param: [headA, headB] 
    * @return: com.mycrawler.mycrawler.suanfa.Solution.ListNode 
    * @Author: 陈家乐 
    * @Date: 2019/3/27
    */ 
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
         两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
         **/
        if(headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while(pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
    
    /** 
    * @Description: 求众数
    * @Param: [nums] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/27
    */ 
    public int majorityElement(int[] num) {
        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){
                count++;
                major=num[i];
            }else if(major==num[i]){
                count++;
            }else count--;

        }
        return major;

    }

    public int titleToNumber(String s) {
        int result = 0;
        for(int i = 0 ; i < s.length(); i++) {
            result = result * 26 + (s.charAt(i) - 'A' + 1);
        }
        return result;

    }


    /** 
    * @Description: 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
    * @Param: [nums, k] 
    * @return: void 
    * @Author: 陈家乐 
    * @Date: 2019/3/27
    */ 
    public void rotate(int[] nums, int k) {
        if(nums.length == 0 || k % nums.length == 0) return;
        int size=nums.length;
        k=k%size;
        int i=0;
        int value=nums[i];
        int start=i;
        while (size>0){
            int index = (i + k) % nums.length;
            int tmp=nums[index];
            i=index;
            nums[index]=value;
            if(i == start){//如果新的角标等于已交换并还未覆盖的脏数据的角标相同,那么重新取第二个数//万一第二个数已经换过了?k为多少至少连续k-1不会被替换
                start++;
                i = start;
                value = nums[i];
            }else {
                value=tmp;
            }

            size--;
        }
        for(Integer one:nums){
            System.out.print(one+" ");
        }
    }

    /** 
    * @Description: 反转2进制
    * @Param: [n] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/28
    */ 
    public int reverseBits(int n) {
           int result=0;
            for(int i=0;i<32;i++){
                int tmp =n&1;//获取最后一位
                n>>=1;//右移获取下一位
                result=result<<1|tmp;//先右移，把tmp放在最后一位
            }
            return result;
    }

    public int hammingWeight(int n) {
        int count=0;
        for(int i=0;i<32;i++){
           count+=n&1;
            n>>=1;//右移获取下一位
        }
        return count;
    }

    /** 
    * @Description: 打家劫舍
    * @Param: [nums] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/3/28
    */ 
    public int rob(int[] nums) {
           int n = nums.length;
           if (n <= 1) return n == 0 ? 0 : nums[0];
           int[] dp =new int[nums.length];
           dp[0]=nums[0];
           dp[1]=Math.max(nums[0],nums[1]);
           for(int i=2;i<nums.length;i++){
               dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
           }
           return dp[nums.length-1];
    }

    /**
    * @Description: 是否是快乐数
    * @Param: [n]
    * @return: boolean
    * @Author: 陈家乐
    * @Date: 2019/3/28
    */
    public boolean isHappy(int n) {
        int slow=n, fast=n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while(slow != fast);
        if (slow == 1) return true;
        else return false;
    }

    private int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n>0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

    /**
    * @Description:  链表移除
    * @Param: [head, val]
    * @return: com.mycrawler.mycrawler.suanfa.Solution.ListNode
    * @Author: 陈家乐
    * @Date: 2019/3/28
    */
    public ListNode removeElements(ListNode head, int val) {

        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
    /**
    * @Description: 计数质数
    * @Param: [n]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/3/28
    */
    public int countPrimes(int n) {
        boolean[] m = new boolean[n];
        int count = 0;
        for (int i=2; i<n; i++) {
            if (m[i])
                continue;

            count++;
            for (int j=i; j<n; j=j+i)
                m[j] = true;
        }

        return count;
    }
    
    /** 
    * @Description: 获取
    * @Param: [s, t] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/3/28
    */ 
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            Character character = map.get(s.charAt(i));
            if(character==null){
                if(map.containsValue(t.charAt(i)))   {
                                return false;
                }
                map.put(s.charAt(i),t.charAt(i));
            }else if(character !=t.charAt(i)){
                return false;
            }

        }
        return true;

    }

    /**
    * @Description: 反转链表
    * @Param: [head]
    * @return: com.mycrawler.mycrawler.suanfa.Solution.ListNode
    * @Author: 陈家乐
    * @Date: 2019/4/1
    */
    public ListNode reverseList(ListNode head) {
        ListNode listNode=null;
        while (head!=null){
            ListNode tmp=head.next;
            head.next=listNode;
            listNode=head;
            head=tmp;

        }
        return listNode;

    }
    /** 
    * @Description: 判断是否存在重复元素
    * @Param: [nums] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/4/2
    */ 
    public boolean containsDuplicate(int[] nums) {
            Set<Integer> set =new HashSet<>();
            for(int one :nums){
                if(!set.add(one)){
                    return true;
                }
            }
            return false;
    }
    /** 
    * @Description:  判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，
     * 并且 i 和 j 的差的绝对值最大为 k。
    * @Param: [nums, k]
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/4/2
    */ 
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> map =new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                int index = map.get(nums[i]);
                if(i-index<=k){
                    return true;
                }
            }
            map.put(nums[i],i);
        }
        return false;
    }

    /**
    * @Description: 翻转二叉树
    * @Param: [root]
    * @return: com.mycrawler.mycrawler.suanfa.TreeNode
    * @Author: 陈家乐
    * @Date: 2019/4/2
    */
    public TreeNode invertTree(TreeNode root) {
            if(root==null){
                return null;
            }
            TreeNode tmp=root.left;
            root.left=invertTree(root.right);
            root.right=invertTree(tmp);
            return root;
    }

    /**
    * @Description:判断它是否是 2 的幂次方
    * @Param: [args]
    * @return: void
    * @Author: 陈家乐
    * @Date: 2019/4/2
    */
    public boolean isPowerOfTwo(int n) {
        return ((n & (n-1))==0 && n>0);
    }
    /**
    * @Description: 判断是否是回文链表
    * @Param: [head]
    * @return: boolean
    * @Author: 陈家乐
    * @Date: 2019/4/2
    */
    public boolean isPalindrome(ListNode head) {
            //快慢指针能找到重点
        ListNode fast=head;
        ListNode slow=head;
        while (fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=fast.next;
        }
        slow=reverseList2(slow);
        while (slow!=null){
            if(slow.val!=head.val){
                return false;
            }
            slow=slow.next;
            head=head.next;
        }
        return true;
    }

    private ListNode reverseList2(ListNode root) {
            ListNode head=null;
            ListNode tmp=null;
            while (root!=null){
                tmp =root.next;
                root.next=head;
                head=root;
                root=tmp;
            }
            return head;
    }
    
    /** 
    * @Description: 二叉搜索树查找公共祖先
    * @Param: [root, p, q] 
    * @return: com.mycrawler.mycrawler.suanfa.TreeNode 
    * @Author: 陈家乐 
    * @Date: 2019/4/2
    */ 
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0)
            root = p.val < root.val ? root.left : root.right;
        return root;

    }

    /**
    * @Description: 二叉树所有路径
    * @Param: [root]
    * @return: java.util.List<java.lang.String>
    * @Author: 陈家乐
    * @Date: 2019/4/2
    */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<String>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }
    private void searchBT(TreeNode root, String path, List<String> answer) {
        if (root.left == null && root.right == null) answer.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
    }

    /** 
    * @Description: 各位相加
    * @Param: [num] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/4/2
    */ 
    public int addDigits(int num) {
        if(num<10){
            return num;
        }
        int sum=0;
        while (num>0) {
            sum +=num%10;
            num=num/10;
        }
        return addDigits(sum);
    }
    /** 
    * @Description: 丑数就是只包含质因数 2, 3, 5 的正整数。
    * @Param: [num] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/4/2
    */ 
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        return ((num % 2 == 0) && isUgly(num / 2)) || ((num % 3 == 0) && isUgly(num / 3)) || ((num % 5 == 0) && isUgly(num / 5));

    }
    
    /** 
    * @Description: 缺失数字
    * @Param: [nums] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/4/2
    */ 
    public int missingNumber(int[] nums) {
        int sum=0;
        int sum2=0;
        for(int i=0;i<nums.length+1;i++){
           sum+=i;
        }
        for(int i=0;i<nums.length;i++){
            sum2+=nums[i];
        }
        return sum-sum2;

    }
    
    /** 
    * @Description: 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    * @Param: [nums] 
    * @return: void 
    * @Author: 陈家乐 
    * @Date: 2019/4/2
    */ 
    public void moveZeroes(int[] nums) {
            int index=0;
            for(int i=0;i<nums.length;i++){
                if(nums[i]!=0){
                    nums[index++]=nums[i];
                }
            }
            while (index<nums.length){
                nums[index++]=0;
            }
    }
    
    /** 
    * @Description: 定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循相同的模式。
     * pattern = "abba", str = "dog cat cat dog"
    * @Param: [pattern, str] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/4/3
    */ 
    public boolean wordPattern(String pattern, String str) {
        if(pattern.length()!=str.split(" ").length){
            return false;
        }
        Map<Character,String> map = new HashMap<>();
        String[] s = str.split(" ");
        for(int i=0;i<pattern.length();i++){
            String character = map.get(pattern.charAt(i));
            if(character==null){
                if(map.containsValue(s[i]))   {
                    return false;
                }
                map.put(pattern.charAt(i),s[i]);
            }else if(!character.equals(s[i])){
                return false;
            }

        }
        return true;
    }

    /**
    * @Description: 每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。
    * @Param: [n]
    * @return: boolean
    * @Author: 陈家乐
    * @Date: 2019/4/3
    */
    public boolean canWinNim(int n) {
               return n%4==0;
    }


    /**
    * @Description:
    * @Param: [a, b]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/4/3
    */
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a^b,(a&b)<<1);
    }

    /**
    * @Description: 找到第一个不重复字符串
    * @Param: [s]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/4/3
    */
    public int firstUniqChar(String s) {
            int[] arr= new int[26];
            for(int i=0;i<s.length();i++){
                arr[s.charAt(i)-'a']++;
            }
        for(int i=0;i<s.length();i++){
            if(arr[s.charAt(i)-'a']==1){
                return i;
            }
        }
        return 0;
    }
    
    /** 
    * @Description: 找不同 
    * @Param: [s, t] 
    * @return: char 
    * @Author: 陈家乐 
    * @Date: 2019/4/3
    */ 
    public char findTheDifference(String s, String t) {
        int[] arr= new int[26];
        for(int i=0;i<s.length();i++){
            arr[s.charAt(i)-'a']++;
        }
        for(int i=0;i<t.length();i++){
            if(--arr[t.charAt(i)-'a']<0){
                return t.charAt(i);
            }
        }
        return 0;
    }
    
    /** 
    * @Description: 找到第n个数
    * @Param: [n] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/4/3
    */ 
    public int findNthDigit(int n) {
        if(n<10){
            return n;
        }
        //判断几位数
        int sum=9;
        int count=1;//位数
        while (sum<n){
            int tmp=sum;
            sum+=count*9*Math.pow(10,count+1);
            if(sum>=n){
                break;
            }
        }
        int i = (n - sum) % count;
        int poist=n/count;
        return i==0?poist%10:(poist+1);

    }

    /**
    * @Description: 计算左叶子之和
    * @Param: [root]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/4/4
    */
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeavesHelper(root, false);
    }

    public int sumOfLeftLeavesHelper(TreeNode root, boolean b) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            if (b) return root.val;
            else return 0;
        }
        return sumOfLeftLeavesHelper(root.left, true) + sumOfLeftLeavesHelper(root.right, false);
    }


    private static final char[] hexDigits = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    /** 
    * @Description: 转16进制 
    * @Param: [num] 
    * @return: java.lang.String 
    * @Author: 陈家乐 
    * @Date: 2019/4/4
    */ 
    public String toHex(int num) {
        StringBuilder result = new StringBuilder();
        for( ; num!=0; num>>>=4 ){
            System.out.println(Integer.toBinaryString(num));
            System.out.println(num&15);
            result.append( hexDigits[num&15] );}
        return result.length()==0 ? "0" : result.reverse().toString();
    }

    
    /** 
    * @Description: 大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串
    * @Param: [s] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/4/4
    */ 
    public int longestPalindrome(String s) {
            int[] arr=new int[58];
            for(int i=0;i<s.length();i++){
                arr[s.charAt(i)-'A']++;
            }
            int count=0;
            for(int i=0;i<58;i++){
                if(arr[i]>1){
                    count+=(arr[i]/2)*2;
                }
            }
            return count==s.length()?count:count-1;
    }

    /**
    * @Description: 字符串相加
    * @Param: [num1, num2]
    * @return: java.lang.String
    * @Author: 陈家乐
    * @Date: 2019/4/4
    */
    public String addStrings(String num1, String num2) {
            int i=num1.length()-1;
            int j=num2.length()-1;
            StringBuilder stringBuilder =new StringBuilder();
            int tmp=0;
            while (i>=0||j>=0){

                if(i>=0){
                    tmp+=(num1.charAt(i--)-'0');
                }
                if(j>=0){
                    tmp+=(num1.charAt(j--)-'0');
                }
                stringBuilder.append(tmp%10);
                tmp/=10;
            }

            return tmp==0?stringBuilder.reverse().toString():stringBuilder.append("1").reverse().toString();
    }


    public Node construct(int[][] g) { return build(0, 0, g.length - 1, g.length - 1, g);}

    Node build(int r1, int c1, int r2, int c2, int[][] g) {
        if (r1 > r2 || c1 > c2) return null;
        boolean isLeaf = true;
        int val = g[r1][c1];
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (g[i][j] != val) {
                    isLeaf = false;
                    break;
                }
            }
        }
        if (isLeaf)
            return new Node(val == 1, true, null, null, null, null);
        int rowMid = (r1 + r2) / 2, colMid = (c1 + c2) / 2;
        return new Node(false, false,
                build(r1, c1, rowMid, colMid, g),//top left
                build(r1, colMid + 1, rowMid, c2, g),//top right
                build(rowMid + 1, c1, r2, colMid, g),//bottom left
                build(rowMid + 1, colMid + 1, r2, c2, g));//bottom right
    }


    /**
    * @Description: n叉树层序遍历
    * @Param: [root]
    * @return: java.util.List<java.util.List<java.lang.Integer>>
    * @Author: 陈家乐
    * @Date: 2019/4/4
    */
    public List<List<Integer>> levelOrder(Node2 root) {
        List<List<Integer>> lists =new ArrayList<>();
            Queue<Node2> queue =new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                List<Integer> list= new ArrayList<>();
                while (size>0){
                    Node2 poll = queue.poll();
                    list.add(poll.val);
                    for (Node2 child : root.children) {
                        queue.add(child);
                    }
                }
                lists.add(list);
            }
            return lists;
    }
    
    /** 
    * @Description: 路径和等于sum的个数
    * @Param: [root, sum] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/4/4
    */
    private int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root != null){
            pathadd(root, sum);
            pathSum(root.left, sum);
            pathSum(root.right, sum);
        }
        return count;
    }

    public void pathadd(TreeNode node, int last){
        if(node != null){
            if(last == node.val){
                count++;
            }
            pathadd(node.left, last-node.val);
            pathadd(node.right, last-node.val);
        }
    }

    /** 
    * @Description:  找到字符串中所有字母异位词
    * @Param: [s, p] 
    * @return: java.util.List<java.lang.Integer> 
    * @Author: 陈家乐 
    * @Date: 2019/4/4
    */ 
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list =new ArrayList<>();

        int length = p.length();
        for(int i=0;i<=s.length()-length;i++){
            if(helper(s.substring(i,i+length),p)){
                list.add(i);
            }
        }
        return list;
    }

    /**
    * @Description:
    * @Param: [n]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/4/4
    */
    public int arrangeCoins(int n) {
        if(n==0){
            return 0;
        }
        long sum=0;
        long count=0;
        while (n>sum){
            count++;
            sum=(count+count*count)/2;

        }
        return (int) (count-1);
    }

    public boolean helper(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) return false;
        int[] dict = new int[26];
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            dict[ch-'a']++;
        }
        for (int i = 0; i < b.length(); i++) {
            char ch = b.charAt(i);
            dict[ch-'a']--;
            if (dict[ch-'a'] < 0) return false;
        }
        return true;
    }


    public boolean repeatedSubstringPattern(String s) {
        int l = s.length();
        for(int i=l/2;i>=1;i--) {
            if(l%i==0) {
                int m = l/i;
                String subS = s.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<m;j++) {
                    sb.append(subS);
                }
                if(sb.toString().equals(s)) return true;
            }
        }
        return false;
    }

    
    /** 
    * @Description: 岛屿周长
    * @Param: [grid] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/4/22
    */ 
    public int islandPerimeter(int[][] grid) {
            int count=0;
            int count2=0;
            for(int i=0;i<grid.length;i++){
                for(int j=0;j<grid[i].length;j++){
                    if(grid[i][j]==1){
                        count++;
                        if(i<grid.length-1&&grid[i+1][j]==1){
                            count2++;
                        }
                        if(j<grid[i].length-1&&grid[i][j+1]==1){
                            count2++;
                        }
                    }
                }
            }

            return count*4-count2*2;
    }



        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(houses);
            Arrays.sort(heaters);
            int i = 0, res = 0;
            for (int house : houses) {
                while(i<heaters.length-1&&Math.abs(house-heaters[i])>=Math.abs(house-heaters[i+1])){
                        i++;
                    }
                    res=Math.max(res,Math.abs(house-heaters[i]));
            }
            return res;
    }
    
    /** 
    * @Description: 数字补数
    * @Param: [num] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/4/23
    */ 
    public int findComplement(int num) {
        int n = 0;
        while (n < num) {
            n = (n << 1) | 1;
        }
        return n - num;
    }

    public int[] constructRectangle(int area) {
        int x = (int) Math.sqrt(area);
        for(int L = x; L >= 2; L--){
            if(area % L == 0){
                return new int[]{area/L, L};
            }
        }
        return new int[]{area, 1};
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
                int[] res=new int[nums1.length];
                Map<Integer,Integer> map =new HashMap<>();
                for(int i=0;i<nums2.length;i++){
                    map.put(nums2[i],i);
                }

                A:for(int i=0;i<nums1.length;i++){
                    for(int j=map.get(nums1[i])+1;j<nums2.length;j++){
                        if(nums1[i]<nums2[j]){
                            res[i]=nums2[j];
                            continue A;
                        }
                    }
                    res[i]=-1;

                }
                return res;
    }

    /** 
    * @Description: 二叉树中的众数
    * @Param: [root] 
    * @return: int[] 
    * @Author: 陈家乐 
    * @Date: 2019/4/24
    */
    Integer pre=null;
    int max=0;
    List<Integer> list=new ArrayList<>();
    public int[] findMode(TreeNode root) {
        if(root==null){
            return null;
        }
        countMode(root);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) res[i] = list.get(i);
        return res;

    }

    private void countMode(TreeNode root) {
        if(root==null){
            return;
        }
        countMode(root.left);
        if(pre==null||root.val==pre){
            count++;
        }else {
            count=1;
        }
        pre=root.val;
        if(count>max){
            max=count;
            list.clear();
            list.add(pre);
        }else if(count==max){
            list.add(pre);
        }

        countMode(root.right);

    }

    public String convertToBase7(int num) {
        StringBuilder stringBuilder =new StringBuilder();
        if(num==0){
            return "0";
        }
        int sum=Math.abs(num);
            while (sum>0){
                stringBuilder.append(sum%7);
                sum/=7;
            }
            if(num<0){
                stringBuilder.append("-");
            }
            return stringBuilder.reverse().toString();
    }

    public int fib(int N) {
        if(N<=1){
            return N;
        }

        int[] arr=new int[N+1];
        arr[0]=0;
        arr[1]=1;
        for(int i=2;i<=N;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr[N];
    }
    
    /** 
    * @Description: 二叉搜索树最小值
    * @Param: [word] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/4/24
    */
    int min=Integer.MAX_VALUE;
    Integer preval=null;
    public int getMinimumDifference(TreeNode root) {
        getMinimum(root);
        return min;
    }

    private void getMinimum(TreeNode node) {
        if(node==null){
            return;
        }
        getMinimum(node.left);
        if(preval!=null)
            min=Math.min(min,node.val-preval);
        preval=node.val;
        getMinimum(node.right);
    }

    /**
    * @Description: 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。
     *                  这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，
     *                  且两数之差的绝对值是 k.
    * @Param: [nums, k]
    * @return: int
    * @Author: 陈家乐
    * @Date: 2019/4/25
    */
    public int findPairs(int[] nums, int k) {
        if(k<0){
            return 0;
        }
        Map<Integer,Integer> map= new HashMap<>();
        Set<Integer> set =new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(map.get(-k+nums[i])!=null){
                set.add(Math.max(nums[i],-k+nums[i]));
            }
            if(map.get(k+nums[i])!=null){
                set.add(Math.max(nums[i],k+nums[i]));
            }
            map.put(nums[i],i);

        }
        return set.size();
    }

    /**
    * @Description: 把二叉搜索树转换为累加树
    * @Param: [root]
    * @return: com.mycrawler.mycrawler.suanfa.TreeNode
    * @Author: 陈家乐
    * @Date: 2019/4/29
    */
    private int sum=0;
    public TreeNode convertBST(TreeNode root) {
        add(root);
        return root;
    }
    public void add(TreeNode root){  //右中左递归
        if(root!=null){
            add(root.right);
            sum+=root.val;
            root.val=sum;
            add(root.left);
        }
    }
    
    /** 
    * @Description: 出勤记录
    * @Param: [s] 
    * @return: boolean 
    * @Author: 陈家乐 
    * @Date: 2019/4/29
    */ 
    public boolean checkRecord(String s) {
        char[] chars = s.toCharArray();
        int countp=0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='A'){
                countp++;
            }else if(i<chars.length-2&&chars[i]=='L'&&chars[i]==chars[i+1]&&chars[i]==chars[i+2]){
                return false;
            }
        }
        return countp<=1;

    }
    int sum1=0;
    public int findTilt(TreeNode root) {
        if(root==null||(root.left==null&&root.right==null)){
            return 0;
        }
        if(root.left!=null&&root.right!=null){
            sum1=Math.abs(root.left.val-root.right.val);
        }else {
            sum1=(root.left==null?root.right.val:root.left.val);
        }

        sum1+=findTilt(root.left);
        sum1+=findTilt(root.right);
        return sum1;


    }

    /**
    * @Description: 判断二叉树的子树
    * @Param: [s, t]
    * @return: boolean
    * @Author: 陈家乐
    * @Date: 2019/5/5
    */
    public boolean isSubtree(TreeNode s, TreeNode t) {
            if(s==null){
                return false;
            }
            if(isSametree(s,t)){
                return true;
            }
            return isSubtree(s.left,t)||isSubtree(s.right,t);
    }

    private boolean isSametree(TreeNode s, TreeNode t) {
        if(s==null&&t==null){
            return true;
        }
        if(s==null||t==null){
            return false;
        }
        if(s.val!=t.val){
            return false;
        }
        return isSametree(s.left,t.left)&&isSametree(s.right,t.right);
    }

    /**
     * @Description:  查找丢失的数
     * @Param: [nums]
     * @return: java.util.List<java.lang.Integer>
     * @Author: 陈家乐
     * @Date: 2019/4/4
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0){
                nums[index] = -nums[index];
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i] > 0){
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * @Description: 分发饼干
     * @Param: [g, s]
     * @return: int
     * @Author: 陈家乐
     * @Date: 2019/4/8
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0;
        int j=0;
        int count=0;
        while (i<g.length&&j<s.length){
            if(g[i]<=s[j]){
                count++;
                i++;
                j++;
            }else{
                j++;
            }
        }
        return count;
    }





    /**
     * @Description: 判断是否是循环字符串构成的
     * @Param: [s]
     * @return: boolean
     * @Author: 陈家乐
     * @Date: 2019/4/8
     */

    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        while(i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap(arr, i, j);
            i += 2 * k;
        }
        return String.valueOf(arr);
    }
    private void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }

    /**
     * @Description: 二叉树直径
     * @Param: [root]
     * @return: int
     * @Author: 陈家乐
     * @Date: 2019/4/29
     */
    int max1=0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=MaxLength(root.left);
        int right=MaxLength(root.right);
        max1=Math.max(left+right,max1);
        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        return max1;
    }



    private int MaxLength(TreeNode left) {
        if(left==null){
            return 0;
        }
        return Math.max(1+MaxLength(left.left),1+MaxLength(left.right));
    }

    /** 
    * @Description: 最短无序 
    * @Param: [nums] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/5/5
    */
    public int findUnsortedSubarray(int[] A) {
        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
        for (int i=1;i<n;i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[n-1-i]);
            if (A[i] < max) end = i;
            if (A[n-1-i] > min) beg = n-1-i;
        }
        return end - beg + 1;
    }

    public boolean isPalindrome2(int x) {
        if(x<0|| (x!=0 &&x%10==0)){
            return false;
        }
        int sum=0;
        while (x>sum) {
            sum =sum*10+x%10;
            x=x/10;
        }
        return sum==x||sum/10==x;
    }


    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode listNode =new ListNode(0);
        ListNode head =listNode;
        while (l1!=null || l2!=null){
            if(l1!=null && l2!=null){
                if(l1.val<l2.val){
                    listNode.next=new ListNode(l1.val);
                    l1=l1.next;
                }else {
                    listNode.next=new ListNode(l2.val);
                    l2=l2.next;
                }
            } else if (l1 != null) {
                listNode.next=new ListNode(l1.val);
                l1=l1.next;
            }else {
                listNode.next=new ListNode(l2.val);
                l2=l2.next;
            }
            listNode=listNode.next;
        }

        return head.next;
    }

    /**
    * @Description: 合并二叉树
    * @Param: [t1, t2]
    * @return: com.mycrawler.mycrawler.suanfa.TreeNode
    * @Author: 陈家乐
    * @Date: 2019/6/24
    */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if(t1!=null && t2!=null){
                t1.val+=t2.val;
            }
            if(t1==null){
                return t2;
            }
            if(t2==null){
                return t1;
            }
            t1.left=mergeTrees(t1.left,t2.left);
            t1.right=mergeTrees(t1.right,t2.right);
            return t1;
    }

    public int findUnsortedSubarray2(int[] nums) {
        //此解法参考英文官方LeetCode上的讨论
        //从左到右扫描（或从右到左）找局部极大值（或局部极小值），若位置放置不正确，找到其应该存在的地方
        int n = nums.length;
        //赋初始开始和结束值
        int start = -1;
        int end = -2;
        //结束值赋为-2是考虑在数组本身就是有序时,return也可以给出正确值
        int min = nums[n - 1];
        int max = nums[0];
        for(int i = 0, pos = 0; i < n; i++) {
            pos = n - 1 - i;
            //找出局部极大、极小值
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[pos]);
            //如果当前值小于局部极大值，用end记录该位置，找到该max的合适位置，
            if(nums[i] < max)
                end = i;
            //如果当前值大于局部极小值，用star记录该位置，找到该star的合适位置
            if(nums[pos] > min)
                start = pos;
        }
        //返回开始和结束的索引差
        return end - start + 1;
    }


    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        ListNode listNode=new ListNode(1);
        new ArrayList<>().add(0);
        //Integer.reverse(n)
        listNode.next=new ListNode(2);
        listNode.next.next=new ListNode(3);
        listNode.next.next.next=new ListNode(4);
        listNode.next.next.next.next=new ListNode(5);
        TreeNode treeNode=new TreeNode(1);
        treeNode.left=new TreeNode(2);
        treeNode.right=new TreeNode(3);
        treeNode.left.left=new TreeNode(4);
        treeNode.right.left=new TreeNode(5);
        //solution.moveZeroes(new int[]{0,0,0,3,12}
       System.out.println(JSONObject.toJSONString(solution.findUnsortedSubarray(new  int[]{16, 6, 4, 8, 10, 9, 15})));

     /*   File file = new File("/Users/chenjiale/Desktop/aaa");
        File file2 = new File("/Users/chenjiale/Desktop/customer_Name.txt");
        BufferedReader bufferedReader =new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter =new BufferedWriter(new FileWriter(file2));

        String str=null;
        while ((str=bufferedReader.readLine())!=null){
            JSONObject jsonObject =JSONObject.parseObject(str);
            JSONObject customer = jsonObject.getJSONObject("customer");
            bufferedWriter.write(customer.get("customerName").toString()+"                  "+customer.get("customerProvinceNumber").toString()+"               "+customer.get("customerNumber").toString());
            //
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();*/

    }



    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };

    class Node2 {
        public int val;
        public List<Node2> children;

        public Node2() {}

        public Node2(int _val,List<Node2> _children) {
            val = _val;
            children = _children;
        }
    };
}
        
