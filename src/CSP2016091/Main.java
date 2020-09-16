package CSP2016091;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int ans = Integer.MIN_VALUE, last = 0;
        for(int i=0;i<n;i++){
            int cur = sc.nextInt();
            if(i>=1){
                ans = Math.max(ans,Math.abs(cur-last));
                last = cur;
            }
            else{
                last = cur;
            }
        }
        System.out.println(ans);
    }
}
