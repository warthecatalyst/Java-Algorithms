package CSP2020064;

import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class Main {
    static final int Q = 998244353;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String S = scanner.next();
        if(S.length()==1){
            long[][] dp = new long[n+1][4];
            dp[0][0] = 1;
            for(int i=1;i<=n;i++){
                dp[i][0] = (dp[i-1][2])%Q;
                dp[i][1] = (dp[i-1][0])%Q;
                dp[i][2] = (dp[i-1][1]+dp[i-1][3])%Q;
                dp[i][3] = (dp[i-1][2]+dp[i-1][3])%Q;
            }
//            for(int i=0;i<=n;i++){
//                System.out.println(dp[i][0]+" "+dp[i][1]+" "+dp[i][2]+" "+dp[i][3]);
//            }
            int s1 = Integer.parseInt(S);
            if(s1==1){
                System.out.println(dp[n][0]);
            }else if(s1==2){
                System.out.println(dp[n][1]);
            }else if(s1==4){
                System.out.println(dp[n][2]);
            }else{
                System.out.println(dp[n][3]);
            }
        }else if(S.length()==2){
            String[] strings = new String[n+1];
            strings[0] = "1";
            strings[1] = "2";
            strings[2] = "4";
            strings[3] = "16";
            strings[4] = "264";
            strings[5] = "46416";
            strings[6] = "166416264";
            for(int i=7;i<=n;i++){
                StringBuilder sb = new StringBuilder();
                sb.append(strings[i-3]).append(strings[i-1].substring(strings[i-4].length())).append(strings[i-2]);
                strings[i] = sb.toString();
                //System.out.println(i+" "+sb);
            }
            System.out.println(contains(strings[n],S));
        }else{
            System.out.println("我不会！");
        }
    }
    static long contains(String str,String key){
        long count = 0;
        int index = 0;
        while ((index = str.indexOf(key, index)) != -1) {
            index = index+key.length();
            count++;
        }
        return count%Q;
    }
}

