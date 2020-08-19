package CSP2014095;


import java.util.Scanner;

public class Main {
    static int n;
    static int m;
    static Scanner scanner;
    public static void main(String[] args){
        scanner = new Scanner(System.in);
        n = scanner.nextInt();
        //System.out.println("n = "+n);
        m = scanner.nextInt();
        //System.out.println("m = "+m);
        if(m==2){
            int ans;
            if(n%3!=0){
                //System.out.print("yes");
                System.out.println(0);
            }
            else{
                ans = 1<<(n/3);
                System.out.println(ans);
            }
        }
    }
}

