package CSP2013124;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int Q = 1000000007;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[][] dp = new long[n+1][6];     //注意这边是long就可
        Arrays.fill(dp[1],0);
        dp[1][0] = 1;
        for(int i=2;i<=n;i++){
            dp[i][0] = 1;
            dp[i][1] = (dp[i-1][0]+dp[i-1][1]*2)%Q;
            dp[i][2] = (dp[i-1][0]+dp[i-1][2])%Q;
            dp[i][3] = (dp[i-1][1]+dp[i-1][3]*2)%Q;
            dp[i][4] = (dp[i-1][1]+dp[i-1][2]+dp[i-1][4]*2)%Q;
            dp[i][5] = (dp[i-1][3]+dp[i-1][4]+dp[i-1][5]*2)%Q;
        }
//        for(int i=1;i<=n;i++){
//            for(int j=0;j<6;j++){
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println(dp[n][5]);
    }
}
