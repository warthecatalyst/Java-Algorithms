package SolveP4.CSP2014035;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int INF = 0x7f7f7f7f;
    public static void main(String[] args) {
        int cur = 0;
        int last = 1;
        int[][][] dp = new int[2][410][410];
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i=0;i<410;i++){
            Arrays.fill(dp[cur][i],INF);
        }
        dp[cur][0][0] = 0;
        for(int k=0;k<n;k++){
            cur = 1-cur;
            last = 1-last;
            int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();
            b = Math.min(b,scanner.nextInt());
            for(int i=0;i<410;i++){
                for(int j=0;j<410;j++){
                    dp[cur][i][j] = Math.min(INF,dp[last][i][j]+b);
                }
            }
            for(int i=0;i<410;i++){
                for(int j=0;j<410;j++){
                    if(i<410-a) dp[cur][i+a][j] = Math.min(dp[cur][i+a][j],dp[last][i][j]);
                    if(j<410-c) dp[cur][i][j+c] = Math.min(dp[cur][i][j+c],dp[last][i][j]);
                    if(j<410-a) dp[cur][i][j+a] = Math.min(dp[cur][i][j+a],dp[last][i][j]);
                }
            }
        }
        int min = INF;
        for(int i=409;i>=0;i--){
            for(int j=409;j>=0;j--){
                min = Math.min(min,dp[cur][i][j]+Math.max(i,j));
            }
        }
        System.out.println(min);
    }
}
