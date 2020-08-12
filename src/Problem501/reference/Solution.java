package Problem501.reference;


public class Solution {
    TreeNode pre = null;
    int[] ret;
    int cur = 0;
    int max = 0;
    int retcount = 0;
    public int[] findMode(TreeNode root) {
        inorder(root);
        pre = null;
        ret = new int[retcount];
        cur = 0;
        retcount = 0;
        inorder(root);
        return ret;
    }

    void inorder(TreeNode root){
        if(root==null){
            return;
        }
        inorder(root.left);
        if(pre!=null&&root.val==pre.val){
            cur++;
        }
        else{
            cur = 1;
        }
        if(cur>max){
            max = cur;
            retcount = 1;
        }
        else if(cur==max){
            if(ret!=null){
                ret[retcount] = root.val;
            }
            retcount++;
        }
        pre = root;
        inorder(root.right);
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}