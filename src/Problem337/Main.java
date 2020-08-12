package Problem337;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        Solution solution = new Solution();
        System.out.println(solution.rob(root));
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution{
    Map<TreeNode,Integer> f = new HashMap<>();
    Map<TreeNode,Integer> g = new HashMap<>();
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root,0),g.getOrDefault(root,0));
    }
    void dfs(TreeNode cur){
        if(cur==null){
            return;
        }
        dfs(cur.left);
        dfs(cur.right);
        f.put(cur,cur.val+g.getOrDefault(cur.left,0)+g.getOrDefault(cur.right,0));
        g.put(cur,Math.max(f.getOrDefault(cur.left,0),g.getOrDefault(cur.left,0))
                +Math.max(f.getOrDefault(cur.right,0),g.getOrDefault(cur.right,0)));
    }
}