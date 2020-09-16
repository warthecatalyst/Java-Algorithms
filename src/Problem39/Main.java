package Problem39;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> dd = solution.combinationSum(new int[]{2,3,6,7},13);
        for(List<Integer> list:dd){
            System.out.println(list);
        }
    }
}

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates,new ArrayList<>(),target,0);
        return ans;
    }
    public void dfs(int[] candidates,List<Integer> list,int target,int lastpickednum){
        if(target==0){
            ans.add(new ArrayList<>(list));
            return;
        }
        if(target<0){
            return;
        }
        for(int i=lastpickednum;i<candidates.length;i++){
            list.add(candidates[i]);
            dfs(candidates,list,target-candidates[i],i);
            list.remove(list.size()-1);
        }
    }
}
