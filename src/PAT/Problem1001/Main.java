package PAT.Problem1001;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a,b;
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt(); b = scanner.nextInt();
        boolean flag = (a+b)<0;
        String s = String.valueOf(Math.abs(a+b));
        //System.out.println(s);
        StringBuilder sb = new StringBuilder();
        while(s.length()>3){
            StringBuilder dd = new StringBuilder(s.substring(s.length()-3));
            dd.reverse();
            sb.append(dd).append(",");
            //System.out.println(sb);
            s = s.substring(0,s.length()-3);
        }
        StringBuilder dd = new StringBuilder(s);
        dd.reverse();
        sb.append(dd);
        if(flag){
            sb.append("-");
        }
        sb.reverse();
        System.out.println(sb);
    }
}
