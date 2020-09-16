package SolveP4.CSP2016124;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int INF = 0x7f7f7f7f;
    public static void main(String[] args) {    //压缩编码
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],INF);
        }
        int[] sum = new int[n+1];
        for(int i=1;i<=n;i++){
            int a = scanner.nextInt();
            dp[i][i] = 0;
            sum[i] = sum[i-1]+a;
        }
        for(int k=1;k<n;k++){
            for(int i=1;i<=n-k;i++){
                for(int j=i;j<i+k;j++){
                    dp[i][i+k] = Math.min(dp[i][i+k],sum[i+k]-sum[i-1]+dp[i][j]+dp[j+1][i+k]);
                }
            }
        }
        System.out.println(dp[1][n]);
    }
}
