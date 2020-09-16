package Problem107;


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){val = x;}
}

public class Main {
    public static void main(String[] args) {
    }
}

class Solution {
    List<List<Integer>> ans;
    List<Pair> ddpair;
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ans = new ArrayList<>();
        ddpair = new ArrayList<>();
        DFS(root,0);
        ddpair.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.depth - o1.depth;
            }
        });
        int last = -1;
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<ddpair.size();i++) {
            //System.out.println(ddpair.get(i));
            if(i==0){
                last = ddpair.get(i).depth;
                list.add(ddpair.get(i).cur.val);
            }
            else{
                int curd = ddpair.get(i).depth;
                if(curd==last){
                    list.add(ddpair.get(i).cur.val);
                }
                else{
                    ans.add(new ArrayList<>(list));
                    list.clear();
                    last = curd;
                    list.add(ddpair.get(i).cur.val);
                }
            }
        }
        if(!list.isEmpty()){
            ans.add(list);
        }
        return ans;
    }
    public void DFS(TreeNode cur,int depth){
        if(cur==null){
            return;
        }
        DFS(cur.left,depth+1);
        DFS(cur.right,depth+1);
        ddpair.add(new Pair(cur,depth));
    }

    static class Pair implements Comparable<Pair>{
        TreeNode cur;
        int depth;
        Pair(TreeNode treeNode,int depth){
            this.cur = treeNode;
            this.depth = depth;
        }

        @Override
        public int compareTo(Pair o) {
            return o.depth-depth;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "cur=" + cur +
                    ", depth=" + depth +
                    '}';
        }
    }
}
