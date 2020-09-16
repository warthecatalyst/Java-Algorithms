package CSP2019091;

import java.awt.desktop.SystemSleepEvent;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N,M;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt(); M = scanner.nextInt();
        long[] nums = new long[N+1];
        int k = 0;
        long max = Integer.MIN_VALUE;
        for(int i=1;i<=N;i++){
            long ans = 0;
            for(int j=0;j<=M;j++){
                if(j==0){
                    nums[i] = scanner.nextLong();
                }
                else{
                    int cur = scanner.nextInt();
                    ans+=(-cur);
                    nums[i]+=cur;
                }
            }
            if(ans>max){
                k = i;
                max = ans;
            }
        }
        long cc = 0;
        for(int i=1;i<=N;i++){
            cc+=nums[i];
        }
        System.out.println(cc+" "+k+" "+max);
    }
}
