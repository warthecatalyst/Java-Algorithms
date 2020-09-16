package CSP2019092;

import java.util.Scanner;

public class Main {
    static int n;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        long[] nums = new long[n];
        boolean[] down = new boolean[n];
        for(int i=0;i<n;i++){
            int cur = scanner.nextInt();
            for(int j=0;j<cur;j++){
                long cc = scanner.nextLong();
                if(cc>0) {
                    if (j == 0) {
                        nums[i] = cc;
                    } else {
                        if (cc < nums[i]) {
                            down[i] = true;
                        }
                        nums[i] = cc;
                    }
                }else{
                    nums[i]+=cc;
                }
            }
        }
//        for(int i=0;i<n;i++){
//            System.out.print(nums[i]+" ");
//        }
        long ans0 = 0,ans1 = 0,ans2 = 0;
        for(int i=0;i<n;i++){
            ans0+=nums[i];
            if(down[i]){
                ans1++;
            }
            int left = i==0?(n-1):(i-1), right = i==(n-1)?0:(i+1);
            if(down[i]&&down[left]&&down[right]){
                ans2++;
            }
        }
        System.out.println(ans0+" "+ans1+" "+ans2);
    }
}
