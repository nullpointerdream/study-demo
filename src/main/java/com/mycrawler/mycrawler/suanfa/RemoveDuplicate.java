package com.mycrawler.mycrawler.suanfa;

/**
 * @program: mycrawler
 * @description: 给定 nums = [0,0,1,1,1,2,2,3,3,4], 函数应该返回新的长度 5,
 * 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。 你不需要考虑数组中超出新长度后面的元素。
 * @author: 陈家乐
 * @create: 2019-01-28 10:20
 **/

public class RemoveDuplicate {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int n=nums.length;
        int pre=0;
        int cur=0;
        while (cur<n){
            if(nums[pre]==nums[cur]){
                cur++;
            }else {
                pre++;
                nums[pre]=nums[cur];
                cur++;
            }
        }
        System.out.println(pre+1);
    }
}
