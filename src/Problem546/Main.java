package Problem546;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        int[] nums = {1,3,2,2,2,3,4,3,1};
        System.out.println(solution.removeBoxes(nums));
    }
}

class Solution{
    int[][][] dp = new int[100][100][100];
    public int removeBoxes(int[] boxes) {
        return calculatePoints(boxes,0,boxes.length-1,0);
    }
    int calculatePoints(int[] boxes,int l,int r,int k){
        if(l>r) return 0;
        if(dp[l][r][k]!=0) return dp[l][r][k];
        while (r>l&&boxes[r]==boxes[r-1]){
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoints(boxes, l, r - 1, 0) + (k + 1) * (k + 1);
        for(int i=l;i<r;i++){
            if(boxes[i]==boxes[r]){
                dp[l][r][k] = Math.max(dp[l][r][k],calculatePoints(boxes,l,i,k+1)+calculatePoints(boxes,i+1,r-1,0));
            }
        }
        return dp[l][r][k];
    }
}
