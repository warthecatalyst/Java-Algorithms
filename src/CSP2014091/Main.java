package CSP2014091;

import java.util.Scanner;

public class Main {
    static int n;
    static int[] nums = new int[10005];
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for(int i=0;i<n;i++){
            int cur = scanner.nextInt();
            nums[cur] = 1;
        }
        int ans = 0;
        for(int i=1;i<=10000;i++){
            if(nums[i]==1&&nums[i+1]==1){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
