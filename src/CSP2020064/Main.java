package CSP2020064;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

    }
}

class Solution{
    final int MOD = 998224353;
    int n;
    String S;
    public Solution(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        S = scanner.next();
        System.out.print("n = "+n+"\t");
        System.out.print(S);
        int cur = Integer.parseInt(S);
        int[][] dp = new int[n+1][4];
        dp[0][0] = 1;
        for(int i=1;i<=n;i++){
            dp[i][0] = dp[i-1][2]%MOD;
            dp[i][1] = dp[i-1][0]%MOD;
            dp[i][2] = (dp[i-1][1]+dp[i-1][2])%MOD;
            dp[i][3] = (dp[i-1][2]+dp[i-1][3])%MOD;
        }
        if(S.length()==1){
            if(cur==1){
                System.out.println(dp[n][0]);
                return;
            }
            if(cur==2){
                System.out.println(dp[n][1]);
                return;
            }
            if(cur==4){
                System.out.print(dp[n][2]);
                return;
            }
            if(cur==6){
                System.out.print(dp[n][3]);
            }
        }
        else if(S.length()==2){
            switch (cur/10){
                case 1:switch (cur%10){
                    case 1:
                        System.out.println(0);
                }
                case 2:
            }
        }
    }
}
