package SolveP3.CSP201409;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        boolean flag = scanner.nextInt()==0;    //大小写不敏感时为真
        if(flag){
            s = s.toLowerCase();
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String cur = scanner.nextLine().trim();
            String cur1 = cur;
            if(flag){
                cur1 = cur1.toLowerCase();
            }
            if(cur1.contains(s)){
                System.out.println(cur);
            }
        }
    }
}
