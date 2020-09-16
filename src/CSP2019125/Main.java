package CSP2019125;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static BigInteger U[] = new BigInteger[]{
            BigInteger.valueOf(314882150829468584L),
            BigInteger.valueOf(427197303358170108L),
            BigInteger.valueOf(1022292690726729920L),
            BigInteger.valueOf(1698479428772363217L),
            BigInteger.valueOf(2006101093849356424L),
    };
    static BigInteger MOD = BigInteger.valueOf(2009731336725594113L);

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n+1];
        for(int i=1;i<=n;i++){
            a[i] = i;
        }
        int q = scanner.nextInt();
        for(int i=0;i<q;i++){
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            long s = 0;
            for(int j=left;j<=right;j++){
                s += a[j]%2019;
            }
            System.out.println(s);
            int t = (int)s%5;
            for(int j = left;j<=right;j++){
                a[j] = BigInteger.valueOf(a[j]).multiply(U[t]).mod(MOD).longValue();
            }
        }
    }
}
