package com.mycrawler.mycrawler.suanfa.sort;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[]=new int[]{1,2,4,5,6,7,9,10};
        System.out.println(search(arr,0,arr.length-1,10));

    }

    public static int search(int arr[], int low, int high, int target) {
        while(low <= high)
        {
            int mid = (low + high)/2;
            if (arr[mid] > target)
                high = mid - 1;
            else if (arr[mid] < target)
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }

}
