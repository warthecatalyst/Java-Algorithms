package CSP2019031;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++){
            //System.out.println("ddddddd");
            nums[i] = scanner.nextInt();
        }
        int max = 0,mid1 = 0,min = 0;
        double mid2 = 0.0;
        boolean flag = false;
        if(nums[n-1]>=nums[0]){
            max = nums[n-1];
            min = nums[0];
        }
        else{
            max = nums[0];
            min = nums[n-1];
        }
        if(n%2==0){
            int dd = n>>1;
            int cc = nums[dd-1]+nums[dd];
            if(cc%2!=0){
                mid2 = cc/2.0;
                System.out.printf("%d %.1f %d",max,mid2,min);
            }else{
                mid1 = cc/2;
                System.out.printf("%d %d %d",max,mid1,min);
            }
        }else{
            int dd = n>>1;
            mid1 = nums[dd];
            System.out.printf("%d %d %d",max,mid1,min);
        }
    }
}
