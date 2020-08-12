package Problem927;

import java.util.Arrays;

public class Main {
}

class Solution {
    public int[] threeEqualParts(int[] A) {
        int[] ans = new int[]{-1,-1};
        int S = 0;
        for (int value : A) {
            if (value == 1) {
                S++;
            }
        }
        if(S==0){
            return new int[]{0,A.length-1};
        }
        if(S%3!=0){
            return ans;
        }
        int i1 = -1, j1 = -1, i2 = -1, j2 = -1, i3 = -1, j3 = -1,count = 0;
        for(int i=0;i<A.length;i++){
            if(A[i]==1) {
                count++;
                if (count == 1) {
                    i1 = i;
                }
                if (count == S / 3) {
                    j1 = i;
                }
                if(count==S/3+1){
                    i2 = i;
                }
                if (count == 2 * S / 3) {
                    j2 = i;
                }
                if(count==2*S/3+1){
                    i3 = i;
                }
                if (count == S) {
                    j3 = i;
                }
            }
        }
        int[] part1 = Arrays.copyOfRange(A,i1,j1);
        int[] part2 = Arrays.copyOfRange(A,i2,j2);
        int[] part3 = Arrays.copyOfRange(A,i3,j3);
        if(!Arrays.equals(part1,part2)) return ans;
        if(!Arrays.equals(part1,part3)) return ans;
        int x = i2-1-j1;
        int y = i3-1-j2;
        int z = A.length-1-j3;
        if(x<z||y<z) return ans;
        return new int[]{j1+z,j2+z+1};  //后面一个要+1
    }
}
