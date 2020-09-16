package CSP2018091;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        int[] nums1 = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = scanner.nextInt();
        }
        for(int i=0;i<n;i++){
            if(i==0){
                nums1[i] = (nums[i]+nums[i+1])/2;
            }else if(i==n-1){
                nums1[i] = (nums[i-1]+nums[i])/2;
            }else{
                nums1[i] = (nums[i-1]+nums[i]+nums[i+1])/3;
            }
        }
        for(int i=0;i<n;i++){
            System.out.print(nums1[i]+" ");
        }
    }
}
