package CSP2015121;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        long n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextLong();
        long ans = 0;
        while(n!=0){
            ans += n%10;
            n = n/10;
        }
        System.out.println(ans);
    }
}

