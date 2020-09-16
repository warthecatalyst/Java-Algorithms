package Problem60;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.getPermutation(3,3));
    }
}

class Solution {
    public String getPermutation(int n, int k) {
        int[] fac = new int[n];
        fac[0] = 1;
        for(int i=1;i<n;i++){
            fac[i] = fac[i-1]*i;
        }
        k--;
        StringBuilder ans = new StringBuilder();
        int[] valid = new int[n+1];
        Arrays.fill(valid,1);
        for(int i=1;i<=n;i++){
            int order = k/fac[n-i]+1;
            //System.out.println("order = "+order);
            for(int j=1;j<=n;j++){
                order -= valid[j];
                if(order==0){
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k = k%fac[n-i];
            //System.out.println("k = "+k);
        }
        return ans.toString();
    }
}