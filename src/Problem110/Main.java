package Problem110;

public class Main {
    public static void main(String[] args){

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution{
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        else return isBalanced(root.left)&&isBalanced(root.right)&&Math.abs(calculateDepth(root.left)-calculateDepth(root.right))<=1;
    }
    int calculateDepth(TreeNode cur){
        if(cur==null){
            return 0;
        }
        return Math.max(calculateDepth(cur.left),calculateDepth(cur.right))+1;
    }
}
