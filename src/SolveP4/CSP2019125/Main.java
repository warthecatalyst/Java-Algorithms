package SolveP4.CSP2019125;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static BigInteger[] U= new BigInteger[]{
            new BigInteger("314882150829468584"),
            new BigInteger("427197303358170108"),
            new BigInteger("1022292690726729920"),
            new BigInteger("1698479428772363217"),
            new BigInteger("2006101093849356424")
    };
    static BigInteger Q = new BigInteger("2009731336725594113");
    static long mod = 2019;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), q = scanner.nextInt();
        long[] A = new long[n+1];
        for(int i=1;i<A.length;i++){
            A[i] = i;
        }
        while(q-->0){
            int l = scanner.nextInt(), r = scanner.nextInt();
            long s = 0;
            for(int i=l;i<=r;i++){
                s += A[i]%2019;
            }
            System.out.println(s);
            int t =(int)s%5;
            for(int i=l;i<=r;i++){
                A[i] = new BigInteger(String.valueOf(A[i])).multiply(U[t]).mod(Q).longValue();
            }
        }
    }
}
