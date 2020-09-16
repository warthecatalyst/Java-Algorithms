package Problem40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combinationSum2(new int[]{2,5,2,1,2},5);
        for(List<Integer> list:lists){
            System.out.println(list);
        }
    }
}

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        DFS(candidates,target,new ArrayList<>(),-1);
        return ans;
    }
    void DFS(int[] candidates,int target,List<Integer> list,int lastpickednum){
        if(target==0){
            if(ans.contains(list)) return;
            ans.add(new ArrayList<>(list));
            return;
        }
        if(target<0||lastpickednum==candidates.length-1){
            return;
        }
        for(int i=lastpickednum+1;i<candidates.length;i++){
            list.add(candidates[i]);
            DFS(candidates,target-candidates[i],list,i);
            list.remove(list.size()-1);
        }
    }
}
