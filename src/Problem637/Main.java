package Problem637;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    List<List<Double>> ans = new ArrayList<>();
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        DFS(root,0);
        for(List<Double> list:ans){
            int count = list.size();
            double sum = 0.0;
            for(double d:list){
                sum+=d;
            }
            sum/=count;
            result.add(sum);
        }
        return result;
    }
    public void DFS(TreeNode cur,int level){
        if(cur==null){
            return;
        }
        int size = ans.size()-1;
        if(size<level){
            List<Double> list = new ArrayList<>();
            list.add((double) cur.val);
            ans.add(list);
        }else{
            List<Double> list = ans.get(level);
            list.add((double) cur.val);
            ans.set(level,list);
        }
        DFS(cur.left,level+1);
        DFS(cur.right,level+1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }
