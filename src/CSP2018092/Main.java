package CSP2018092;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] startW = new int[n];
        int[] endW = new int[n];
        int[] startH = new int[n];
        int[] endH = new int[n];
        for(int i=0;i<n;i++){
            startH[i] = scanner.nextInt();
            endH[i] = scanner.nextInt();
        }
        for(int i=0;i<n;i++){
            startW[i] = scanner.nextInt();
            endW[i] = scanner.nextInt();
        }
        int i = 0,j = 0;
        int ans = 0;    //应该不会超过int的范围
        while(i<n&&j<n){
            if(startH[i]>=endW[j]){  //H的开始时间大于等于W的结束时间，
                j++;
            }else if(endH[i]<=startW[j]){    //W的开始时间大于等于H的结束时间
                i++;
            }else{
                int begin = Math.max(startH[i],startW[j]);
                int end = Math.min(endH[i],endW[j]);
                ans += end-begin;
                if(endW[j]==endH[i]){   //同时结束，都+1
                    i++;
                    j++;
                }else if(endW[j]<endH[i]){
                    j++;
                }else if(endW[j]>endH[i]){
                    i++;
                }
            }
        }
        System.out.println(ans);
    }
}
