package CSP2016041;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        int cnt = 0;
        for(int i=0;i<n;i++){
            nums[i] = scanner.nextInt();
            if(i>=2){
                if(nums[i-1]>nums[i-2]&&nums[i-1]>nums[i]){
                    //System.out.println("zhedian1 "+(i-1));
                    cnt++;
                }
                if(nums[i-1]<nums[i-2]&&nums[i-1]<nums[i]){
                    //System.out.println("zhedian2 "+(i-1));
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
