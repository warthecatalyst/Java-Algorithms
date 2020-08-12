package CSP2014035;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();

    }
}

class Solution{
    int n;
    int[] a,b,c,d;
    public Solution(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        a = new int[n+1];
        b = new int[n+1];
        c = new int[n+1];
        d = new int[n+1];
        for(int i=1;i<=n;i++){
            a[i] = scanner.nextInt();
            b[i] = scanner.nextInt();
            c[i] = scanner.nextInt();
            d[i] = scanner.nextInt();
        }
    }
    public void entry(){
        int[][][][] dp = new int[n+1][][][];
    }
}
