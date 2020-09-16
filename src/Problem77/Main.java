package Problem77;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.combine(4,2);
        for(List<Integer> list:ans){
            System.out.println(list);
        }
    }
}

class Solution {
    List<List<Integer>> ans;
    int total;
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        total = n;
        dfs(1,k,new ArrayList<>());
        return ans;
    }
    public void dfs(int cur,int k,List<Integer> arr){
        if (arr.size() + (total - cur + 1) < k) {
            return;
        }
        if(arr.size()==k){
            ans.add(new ArrayList<>(arr));
            return;
        }
        List<Integer> newarr = new ArrayList<>(arr);
        newarr.add(cur);
        dfs(cur+1,k,newarr);
        dfs(cur+1,k,arr);
    }
}