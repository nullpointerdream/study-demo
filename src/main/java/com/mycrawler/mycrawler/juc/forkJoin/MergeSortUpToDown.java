package com.mycrawler.mycrawler.juc.forkJoin;

import com.mycrawler.mycrawler.util.SortUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MergeSortUpToDown {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Comparable[] arr = SortUtil.createRamdom(20000);
        Comparable[] dest =new Comparable[20000];
        System.arraycopy(arr,0,dest,0,arr.length);
        SortUtil.printf(arr);


        long currentTimeMillis = System.currentTimeMillis();
        sort(arr,0,arr.length-1);
        SortUtil.printf(arr);
        System.out.println(System.currentTimeMillis()-currentTimeMillis);

        ForkJoinMerge forkJoinMerge =new ForkJoinMerge(arr);
        forkJoinMerge.sort();
    }

    public static void sort(Comparable[] arr,int start,int end){
        if(start>=end){
            return;
        }
        int mid =(start+end)/2;
        sort(arr,start,mid);
        sort(arr,mid+1,end);
        merage(arr,start,mid,end);
    }

    private static void merage(Comparable[] arr, int start, int mid, int end) {
        Comparable[] newArr =new Comparable[arr.length];
        for(int i=0;i<arr.length;i++){
            newArr[i]=arr[i];
        }
        int k=start;
        int lo=start;
        int hi=mid+1;
        for(int i=start;i<=end;i++){
            if(lo>mid){
                arr[k++]=newArr[hi++];
            }else if(hi>end){
                arr[k++]=newArr[lo++];
            }else  if(SortUtil.less(newArr[lo],newArr[hi])){
                arr[k++]=newArr[lo++];
            }else {
                arr[k++]=newArr[hi++];
            }
        }
    }

    static class ForkJoinMerge{
        private Comparable[] comparables;

        public ForkJoinMerge(Comparable[] comparables){
            this.comparables=comparables;
        }
        public void sort() throws ExecutionException, InterruptedException {
            ForkJoinPool forkJoinPool =new ForkJoinPool();
            long currentTimeMillis = System.currentTimeMillis();
            ForkJoinMergeTask forkJoinMergeTask =new ForkJoinMergeTask(comparables);
            ForkJoinTask<Void> submit = forkJoinPool.submit(forkJoinMergeTask);
            submit.get();
            System.out.println(System.currentTimeMillis()-currentTimeMillis);
            SortUtil.printf(comparables);

        }
    }
    static class ForkJoinMergeTask extends  RecursiveTask<Void>{

        private Comparable[] comparables;
        private int  start;
        private int  end;

        public ForkJoinMergeTask(Comparable[] comparables){
            this(comparables,0,comparables.length-1);

        }

        public ForkJoinMergeTask(Comparable[] comparables,int start,int end){
            this.comparables=comparables;
            this.start=start;
            this.end=end;
        }

        @Override
        protected Void compute() {
            // 如果条件成立，说明这个任务所需要计算的数值分为足够小了
            int mid =start+(end -start)/2;
            if(end-start>1){
                ForkJoinMergeTask left = new ForkJoinMergeTask(comparables, start, mid);
                left.fork();
                ForkJoinMergeTask right = new ForkJoinMergeTask(comparables, mid + 1, end);
                right.fork();
                left.join();
                right.join();
            }
            merageArr(comparables,start,mid,end);
            return null;
        }

        private void merageArr(Comparable[] arr, int start, int mid, int end) {
            Comparable[] newArr =new Comparable[arr.length];
            for(int i=0;i<arr.length;i++){
                newArr[i]=arr[i];
            }
            int k=start;
            int lo=start;
            int hi=mid+1;
            for(int i=start;i<=end;i++){
                if(lo>mid){
                    arr[k++]=newArr[hi++];
                }else if(hi>end){
                    arr[k++]=newArr[lo++];
                }else  if(SortUtil.less(newArr[lo],newArr[hi])){
                    arr[k++]=newArr[lo++];
                }else {
                    arr[k++]=newArr[hi++];
                }
            }
        }
    }

}
