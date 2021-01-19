package com.mycrawler.mycrawler.util;


import java.util.*;

public class Solution {

    private final int START=0;
    private final int NUM=1;
    private final int STR=2;
    private int calculateFlag=0;
    private int caseEnum=START;
    private int num=0;
    private Stack<Integer> numberStack=new Stack();
    private Stack<Character> strStack=new Stack();

    public void calculate(Stack<Integer> numberStack,Stack<Character> strStack){
        if(numberStack.size()<2){
            return;
        }
        Integer one = numberStack.pop();
        Integer two = numberStack.pop();
        Character pop = strStack.pop();
        Integer rel=0;
        if(pop=='-'){
            rel=two-one;
        }else {
            rel=one+two;
        }
        numberStack.push(rel);

    }
    public int calculate(String s) {
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c==' '){
                continue;
            }
            switch (caseEnum){
                case START:
                    if (Character.isDigit(c)){
                        caseEnum=NUM;
                    }else {
                        caseEnum=STR;
                    }
                    i--;
                    break;
                case NUM:
                    if(Character.isDigit(c)) {
                        num = num * 10 + c - '0';
                    }else {
                        numberStack.push(num);
                        if(calculateFlag==1){
                            calculate(numberStack,strStack);
                        }
                        num=0;
                        i--;
                        caseEnum=STR;
                    }
                    break;
                case STR:
                    if(Character.isDigit(c)){
                        caseEnum=NUM;
                        i--;
                    }else if('('==c){
                        calculateFlag=0;
                        caseEnum=NUM;
                    }else if(c==')') {
                        calculate(numberStack,strStack);
                    }else{
                        strStack.push(c);
                        calculateFlag=1;
                    }
                    break;
            }

        }
        if(num!=0){
            numberStack.push(num);
            calculate(numberStack,strStack);
        }
        return numberStack.empty()?0:numberStack.pop();

    }


    public boolean isPalindrome(ListNode head) {
        if(head==null){
            return true;
        }
        ListNode first = head;
        ListNode second = head.next;
        while (second!=null&&second.next!=null){
            first=first.next;
            second=second.next.next;
        }
        ListNode reverseLists = reverseList(first.next);
        while (reverseLists!=null){
            if(head.val!=reverseLists.val){
                return false;
            }
            head=head.next;
            reverseLists=reverseLists.next;
        }
        return true;
        //first 中间
    }

    private ListNode reverseList(ListNode head) {
        ListNode node=head;
        ListNode pre=null;
        while (node!=null){
            ListNode tmp=node.next;
            node.next=pre;
            pre=node;
            node=tmp;
        }
        return pre;
    }



    /**
    * @Description: 最长不重复字符 
    * @Param: [s] 
    * @return: int 
    * @Author: 陈家乐 
    * @Date: 2019/7/12
    */ 
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)),i);//这一步点睛之笔,省去删除重复字符下标之前的字符
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


    /** 
    * @Description: 最长回文字符串
    * @Param: [s] 
    * @return: java.lang.String 
    * @Author: 陈家乐 
    * @Date: 2019/7/12
    */ 
    public String longestPalindrome(String s) {
        if(s.isEmpty()){
            return "";
        }
        int lo=0;
        int length=1;
        boolean[][] dp =new boolean[s.length()][s.length()];//dp[i][j]表示下标i到j是否是回文串
        for(int i=0;i<s.length();i++){
            dp[i][i]=true;
            if(i<s.length()-2&&((s.charAt(i)==s.charAt(i+1))||(s.charAt(i)==s.charAt(i+2)))){
                dp[i][i+1]=true;
                lo=i;
                length=2;
            }
        }
        for(int len=3;len<s.length();len++){
            for(int i=0;i<s.length()-len;i++){
                if(dp[i+1][i+len-1] && s.charAt(i)==s.charAt(i+len)){
                    dp[i][i+len]=true;
                    lo=i;
                    length=len+1;
                }
            }
        }
        return s.substring(lo,lo+length);
    }
    
    /** 
    * @Description: 3树之和 
    * @Param: [nums] 
    * @return: java.util.List<java.util.List<java.lang.Integer>> 
    * @Author: 陈家乐 
    * @Date: 2019/8/6
    */ 
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        int l=0;
        int r=0;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                break;
            }
            if(i==0||nums[i]!=nums[i-1]) {
                l=i+1;
                r=nums.length-1;
                while (l < r) {
                    if (nums[l] + nums[r] < -nums[i]) {
                        l++;
                    } else if (nums[l] + nums[r] > -nums[i]) {
                        r--;
                    } else {
                        list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l]==nums[l+1]){
                            l++;
                        }
                        while (l < r && nums[r]==nums[r-1]){
                            r--;
                        }
                        l++;
                        r--;
                    }
                }
            }
        }
        return list;
    }

    public List<String> letterCombinations(String digits) {
            LinkedList<String> ans = new LinkedList<String>();
            if(digits.isEmpty()) return ans;
            String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            ans.add("");
            for(int i =0; i<digits.length();i++){
                int x = Character.getNumericValue(digits.charAt(i));
                while(ans.peek().length()==i){
                    String t = ans.remove();
                    for(char s : mapping[x].toCharArray())
                        ans.add(t+s);
                }
            }
            return ans;
        }

    /** 
    * @Description: 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
    * @Param: [head, n] 
    * @return: com.mycrawler.mycrawler.util.Solution.ListNode 
    * @Author: 陈家乐 
    * @Date: 2019/8/8
    */ 
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow = head;

        //Move fast in front so that the gap between slow and fast becomes n
        for(int i=1; i<=n+1; i++)   {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }

    public List<String> generateParenthesis(int n) {
        List<String> list =new ArrayList<>();
        generateParenthesis(list,0,0,"",n*2);
        return list;
    }

    private void generateParenthesis(List<String> list, int l, int r, String str, int i2) {
        if(str.length()==i2){
            list.add(str);
            return;
        }
        if(l<i2/2){
            generateParenthesis(list,l+1,r,str+"(",i2);
        }
        if (l>r){
            generateParenthesis(list,l,r+1,str+")",i2);
        }
    }

    /**
    * @Description:
    * @Param: [nums, target]
    * @return: int[]
    * @Author: 陈家乐
    * @Date: 2019/8/20
    */
    public int[] searchRange(int[] nums, int target) {

         int start =firstGreaterEqual(nums, target);
         if(nums[start]!=target){
             return new int[]{-1,-1};
         }
         return new int[]{start,firstGreaterEqual(nums,target+1)-1};
    }

    private int firstGreaterEqual(int[] nums, int target) {
        int left=0;
        int right=nums.length;
        while (left<right){
            int  mid=(left+right)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }

    public static void main(String[] args) throws InterruptedException {
        ListNode listNode =new ListNode(1);
        listNode.next=new ListNode(4);
        listNode.next.next=new ListNode(-1);
        listNode.next.next.next=new ListNode(-1);
        listNode.next.next.next.next=new ListNode(4);
        listNode.next.next.next.next.next=new ListNode(1);
        Solution solution =new Solution();
        solution.isPalindrome(listNode);
        int calculate = solution.calculate("0");
        //int calculate = solution.calculate("(4+5+2)");
        System.out.println(calculate);
        //System.out.println(solution.findMinIdx(new int[]{3,4, 5, 6, 7, 0, 1, 2}));
      //  ExecutorService executorService =Executors.newFixedThreadPool(21);
      //  executorService.submit();
      //  System.out.println(JSONObject.toJSONString(solution.searchRange(new int[]{1},1)));

    }

    public int findMinIdx(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end -  start) / 2;
            if (nums[mid] > nums[end]) start = mid + 1;
            else end = mid;
        }
        return start;
    }



     static class ListNode {
      int val;
      ListNode next;
     ListNode(int x) { val = x; }
  }

}
