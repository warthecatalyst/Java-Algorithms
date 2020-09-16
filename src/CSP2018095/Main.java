package CSP2018095;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final long Q = 998244353;
    static int m,l,r;
    static long[] k;
    static long[] a;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        l = scanner.nextInt();
        r = scanner.nextInt();
        k = new long[m+1];
        a = new long[3005];
        for(int i=1;i<=m;i++){
            k[i] = scanner.nextInt();
        }
        Arrays.fill(a,0);
        a[0] = 1;
        for(int n=1;n<=m;n++){
            for(int i=1;i<=n;i++){
                a[n] = (a[n]+k[i]*a[(n-i)])%Q;
            }
        }
        for(int n=l;n<=r;n++){
            if(n<=m){
                System.out.println(a[n]);
            }else{
                for(int j=1;j<=m;j++){
                    a[n] = (a[n]+a[n-j]*k[j])%Q;
                }
                System.out.println(a[n]);
            }
        }
    }
}
