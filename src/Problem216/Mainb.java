package Problem216;

import java.util.ArrayList;
import java.util.List;

public class Mainb {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.combinationSum3(3,9);
        for(List<Integer> list:ans){
            System.out.println(list);
        }
    }
}

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {  //和为n的k个数
        DFS(k,n,new ArrayList<>(),0);
        return ans;
    }
    public void DFS(int k,int n,List<Integer> list,int lastpickednum){
        if(n==0&&k==0){
            ans.add(new ArrayList<>(list));
            return;
        }
        if(lastpickednum>=9||k<0){
            return;
        }
        for(int i=lastpickednum+1;i<=9;i++) {
            list.add(i);
            DFS(k - 1, n - i, list, i);
            list.remove(list.size() - 1);
        }
    }
}
