package CSP2014035;

import jdk.jshell.SourceCodeAnalysis;

import java.util.Map;
import java.util.Scanner;

public class Main {
    static int n;
    static int max;
    static int[][][] dp;
    static final int INF = 0x7f7f7f7f;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        max = n*10+5;
        dp = new int[n][max][max];
        int now = 0,last = 1;
        for(int i=0;i<max;i++){
            for(int j=0;j<max;j++){
                dp[now][i][j] = INF;
            }
        }
        dp[now][0][0] = 0;
        for(int k=0;k<n;k++){
            now = 1-now;
            last = 1-last;
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            b = Math.min(b,scanner.nextInt());
            for(int i=0;i<max;i++){
                for(int j=0;j<max;j++){
                    dp[now][i][j] = dp[last][i][j]+b;   //当前占用2个
                }
            }
            for(int i=0;i<max;i++){
                for(int j=0;j<max;j++){
                    if(i<max-a) dp[now][i+a][j] = Math.min(dp[now][i+a][j],dp[last][i][j]);
                    if(j<max-a) dp[now][i][j+a] = Math.min(dp[now][i][j+a],dp[last][i][j]);
                    if(j<max-c) dp[now][i][j+c] = Math.min(dp[now][i][j+c],dp[last][i][j]);
                }
            }
        }
        int min = INF;
        for(int i=0;i<max;i++){
            for(int j=0;j<max;j++){
                min = Math.min(dp[now][i][j]+Math.max(i,j),min);
            }
        }
        System.out.println(min);
    }
}
