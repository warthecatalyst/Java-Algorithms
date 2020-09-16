package jianzhiOffer29;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = solution.spiralOrder(new int[][]{{1,2,3,4}});
        System.out.println(Arrays.toString(ans));
    }
}

class Solution {
    int[][] direction = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    int n,m;
    public int[] spiralOrder(int[][] matrix) {
        n = matrix.length;
        if(n==0){
            return new int[]{};
        }
        m = matrix[0].length;
        if(m==0){
            return new int[]{};
        }
        int[] print = new int[n*m];
        int pickCount = 0;
        int i=0,j=0;
        boolean[][] vis = new boolean[n][m];
        int k = 0;
        while(true){
            //System.out.println("i = "+i+" j = "+j);
            print[pickCount] = matrix[i][j];
            vis[i][j] = true;
            pickCount++;
            if (pickCount==n*m) break;
            while (!in(i+direction[k][0],j+direction[k][1])||vis[i+direction[k][0]][j+direction[k][1]]){
                k = (k+1)%4;
            }
            i = i+direction[k][0];
            j = j+direction[k][1];
        }
        return print;
    }
    boolean in(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
}
