package CSP2020062;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int n,a,b;
        int[][] value1;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        a = scanner.nextInt();
        b = scanner.nextInt();
        value1 = new int[a][2];
        for(int i=0;i<a;i++){//index和value
            value1[i][0] = scanner.nextInt();
            value1[i][1] = scanner.nextInt();
        }
        int i=0;
        long ans = 0;
        for(int j=0;j<b;j++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if(i>=a){
                break;
            }
            if(value1[i][0]==x){
                ans += (long) y*value1[i][1];
                i++;
            }
            else if(value1[i][0]<x){
                while (i<a&&value1[i][0]<x) {
                    i++;
                }
                if(i<a){
                    if(value1[i][0]==x){
                        ans += (long) y*value1[i][1];
                        i++;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
