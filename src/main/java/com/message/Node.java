package com.message;


import java.util.*;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {



    public Node copyRandomList(Node head) {
        List<Node> list = new ArrayList<>();
        Map<Node, Integer> nodeMap = new HashMap<>();
        Node node = head;
        int i = 0;
        while (node != null) {
            list.add(new Node(node.val));
            nodeMap.put(node, i++);
            node = node.next;
        }
        i = 0;
        while (head != null) {
            Node newNode = list.get(i);
            if (i < list.size() - 1) {
                newNode.next = list.get(i + 1);
            }
            i++;
            newNode.random = list.get(nodeMap.get(head));
            head = head.next;
        }
        return list.get(0);


    }




    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cr = s.charAt(i);
            switch (cr) {
                case ')': {
                    char c = stack.pop();
                    if (c != '(') {
                        return false;
                    }
                    break;
                }
                case ']': {
                    char c = stack.pop();
                    if (c != '[') {
                        return false;
                    }
                    break;
                }
                case '}': {
                    char c = stack.pop();
                    if (c != '{') {
                        return false;
                    }
                    break;
                }
                default:
                    stack.push(cr);
                    break;
            }


        }
        return stack.empty();
    }








    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 2) {
            return 2;
        }
        final int START = 0;
        final int HIGH = 1;
        final int LOW = 2;
        int FLAG = START;
        int rel = 1;
        for (int i = 1; i < nums.length; i++) {
            switch (FLAG) {
                case START:
                    if (nums[i] > nums[i - 1]) {
                        FLAG = HIGH;
                        rel++;
                    } else if (nums[i] < nums[i - 1]) {
                        FLAG = LOW;
                        rel++;
                    }
                    break;
                case HIGH:
                    if (nums[i] < nums[i - 1]) {
                        FLAG = LOW;
                        rel++;
                    }
                    break;
                case LOW:
                    if (nums[i] > nums[i - 1]) {
                        FLAG = LOW;
                        rel++;
                    }
                    break;
            }
        }
        return rel;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }

    public static String removeKdigits(String num, int k) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            int c = num.charAt(i) - '0';
            while (!stack.isEmpty() && k > 0) {
                if (c < stack.getLast()) {
                    stack.removeLast();
                    k--;
                } else {
                    break;
                }
            }
            stack.addLast(c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        Boolean flag = true;
        while (!stack.isEmpty()) {
            if (flag) {
                if (stack.getFirst() == 0) {
                    stack.removeFirst();
                    continue;
                } else {
                    flag = false;
                }
            }
            stringBuilder.append(stack.removeFirst());
        }
        if (stringBuilder.toString().isEmpty()) {
            return "0";
        }
        return stringBuilder.substring(0, stringBuilder.length() - k);

    }

    public static int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int count = 1;
        int x = points[0][0];
        int y = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];
            x = point[0];
            if (point[0] > y) {
                count++;
                y = point[1];
            } else if (point[1] <= y) {
                y = point[1];
            }

        }
        return count;
    }


    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] arr = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            arr[i] = gas[i] - cost[i];
        }
        int sum = 0;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] < 0) {
                sum = 0;
                j = i + 1;
            } else {
                sum += arr[i];
            }
        }
        if (sum < 0) {
            return -1;
        }
        for (int i = 0; i < j; i++) {
            sum += arr[i];
        }
        return sum >= 0 ? j : -1;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists =new ArrayList<>();
        lists.add(new ArrayList<>());
        subsets(lists,0,nums,new ArrayList<>());
        return lists;
    }




    private static void subsets(List<List<Integer>> lists, int index, int[] nums, ArrayList<Integer> es) {

        if(index>=nums.length) {
            return;
        }
        es.add(nums[index]);
        lists.add(new ArrayList<>(es));
        subsets(lists,index+1,nums,es);
        es.remove(es.size() - 1);
        while (index < nums.length-1 && nums[index+1] == nums[index]) {index++;}
        subsets(lists,index+1,nums,es);

    }

    public static List<String> generateParenthesis(int n) {
        List<String> l = new ArrayList<>();
        generateParenthesis("", 0, 0, n, l);
        return l;
    }

    private static void generateParenthesis(String s, int left, int right, int n, List<String> list) {
        if (left == n && right == n) {
            list.add(s);
            return;
        }

        if (left > right) {
            generateParenthesis(s + ")", left, right + 1, n, list);
        }
        if (left < n) {
            generateParenthesis(s + "(", left+1, right , n, list);
        }
    }


    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> lists = new ArrayList<>();
            int sum=0;
            combinationSum2(candidates,target,lists,0,sum,new ArrayList<>());
            return lists;
    }

    private static void combinationSum2(int[] candidates, int target, List<List<Integer>> lists, int index,int sum,ArrayList<Integer> list) {
        if (index == candidates.length) {
            return;
        }

        int candidate = candidates[index];
        sum += candidate;
        if (sum  > target) {
            return;
        }
        list.add(candidate);

        if (sum == target) {
            lists.add(new ArrayList<>(list));
        }
        combinationSum2(candidates,target,lists,index+1,sum,list);

        sum-=candidate;
        list.remove(list.size() - 1);
        while (index<candidates.length-1&&candidates[index+1]==candidates[index]){
            index++;
        }
        combinationSum2(candidates,target,lists,index+1,sum,list);


    }
}