package com.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @create: 2020-05-19 16:46
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        Main menuService=new Main();
        HashMap h = new HashMap();
        System.out.println(menuService.combinationSum(new int[]{2,3,6,7},7));
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists =new ArrayList<>();
        combinationSum(lists,0,candidates,new ArrayList<Integer>(),target);
        return lists;
    }



    private void combinationSum(List<List<Integer>> lists, int i, int[] candidates, ArrayList<Integer> es, int remain) {
        int candidate = candidates[i];
        if(remain-candidate < 0) {
            return;
        }
        remain-=candidate;
        es.add(candidates[i]);
        if(remain == 0) {
            lists.add(new ArrayList<>(es));
            return;
        }
        combinationSum(lists,i,candidates,es,remain);
        remain+=candidate;
        es.remove(es.size()-1);
        combinationSum(lists,i+1,candidates,es,remain);
    }



}
