package Problem501;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){

    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class Solution{
    Map<Integer,Integer> map = new HashMap<>();
    public int[] findMode(TreeNode root) {
        dfs(root);
        int max = Integer.MIN_VALUE;
        List<Integer> list0 = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()>=max){
                max = entry.getValue();
            }
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()==max){
                list0.add(entry.getKey());
            }
        }
        int[] cur = new int[list0.size()];
        for(int i=0;i<cur.length;i++){
            cur[i] = list0.get(i);
        }
        return cur;
    }
    void dfs(TreeNode root){
        if(root==null){
            return;
        }
        int cur = map.getOrDefault(root.val,0);
        map.put(root.val,cur+1);
        dfs(root.left);
        dfs(root.right);
    }
}