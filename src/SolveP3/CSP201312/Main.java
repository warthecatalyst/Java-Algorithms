package SolveP3.CSP201312;

import java.util.Scanner;

public class Main { //最大的矩形
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] height = new int[n];
        for(int i=0;i<n;i++){
            height[i] = scanner.nextInt();
        }
        int ans = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int low = height[i];
            for(int j=i;j<n;j++){
                low = Math.min(low,height[j]);
                int cur = (j-i+1)*low;
                ans = Math.max(cur,ans);
            }
        }
        System.out.println(ans);
    }
}
