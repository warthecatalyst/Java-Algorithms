package CSP2015092;

import java.util.Scanner;

public class Main {
    static final int[] ordinary = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
    static final int[] runnian = new int[]{0,31,29,31,30,31,30,31,31,30,31,30,31};
    public static void main(String[] args){
        int year,day;
        Scanner scanner = new Scanner(System.in);
        year = scanner.nextInt();
        day = scanner.nextInt();
        boolean flag = (year%400==0)||(year%4==0&&year%100!=0);
        int month = 1;
        if(flag){
            while(day>runnian[month]){
                day-=runnian[month];
                month++;
            }
            System.out.println(month);
            System.out.print(day);
        }else{
            while(day>ordinary[month]){
                day -= ordinary[month];
                month++;
            }
            System.out.println(month);
            System.out.print(day);
        }
    }
}
