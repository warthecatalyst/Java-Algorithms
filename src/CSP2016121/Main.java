package CSP2016121;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i=0;i<n;i++){
            nums[i] = scanner.nextInt();
        }
        Arrays.sort(nums);
//        for(int i=0;i<n;i++){
//            System.out.print(nums[i]+" ");
//        }
//        System.out.println();
        if(n%2==0){
            int mid1 = n/2-1;
            int mid2 = n/2;
            int left = 0,right = 0;
            for(int i=0;i<mid1;i++){
                if(nums[i]<nums[mid1]) left++;
            }
            for(int i=mid2;i<n;i++){
                if(nums[i]>nums[mid1]) right++;
            }
            if(left==right){
                System.out.println(nums[mid1]);
                return;
            }
            left = right = 0;
            for(int i=0;i<mid2;i++){
                if(nums[i]<nums[mid2]) left++;
            }
            for(int i=mid2+1;i<n;i++){
                if(nums[i]>nums[mid2]) right++;
            }
            if(left==right){
                System.out.println(nums[mid2]);
                return;
            }
            System.out.println(-1);
        }
        else{
            int mid = n/2;
            int left = 0, right = 0;
            for(int i=0;i<mid;i++){
                if(nums[i]<nums[mid]) left++;
            }
            for(int i=mid+1;i<n;i++){
                if(nums[i]>nums[mid]) right++;
            }
            if(left==right){
                System.out.println(nums[mid]);
                return;
            }
            System.out.println(-1);
        }
    }
}
