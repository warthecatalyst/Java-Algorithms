package CSP2014035.Reference;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int max = n*10+5;
        int[][][] dp = new int[2][max][max];
        int now = 0,last=1;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                dp[now][i][j] = 0x7f7f7f7f;
            }
        }
        dp[now][0][0]=0;
        for (int k = 0; k < n; k++) {
            now=1-now;
            last=1-last;
            int a = s.nextInt();
            int b = s.nextInt();
            int c = s.nextInt();
            b = Math.min(b, s.nextInt());
            for (int i = 0; i < max; i++) {
                for (int j = 0; j < max; j++) {
                    dp[now][i][j] = Math.min(0x7f7f7f7f, dp[last][i][j] + b);
                }
            }
            for (int i = 0; i < max; i++) {
                for (int j = 0; j < max; j++) {
                    if (i < max-a)dp[now][i+a][j] = Math.min(dp[now][i+a][j],dp[last][i][j]);
                    if (j < max-c)dp[now][i][j+c] = Math.min(dp[now][i][j+c],dp[last][i][j]);
                    if (j < max-a)dp[now][i][j+a] = Math.min(dp[now][i][j+a],dp[last][i][j]);
                }
            }
        }
        int min = 0x7f7f7f7f;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                min = Math.min(min, dp[now][i][j] + Math.max(i, j));
            }
        }
        System.out.println(min);
    }
}
