package CSP2014121;

import java.util.Scanner;

public class Main {
    static int n;
    static Scanner scanner;
    public static void main(String[] args){
        scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] nums = new int[n+5];
        for(int i=0;i<n;i++){
            int m = scanner.nextInt();
            nums[m]++;
            System.out.print(nums[m]+" ");
        }
    }
}
