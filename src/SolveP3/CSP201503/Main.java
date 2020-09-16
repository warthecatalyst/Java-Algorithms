package SolveP3.CSP201503;

import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Main { //节日
    static int[] DD = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
    static int a,b,c,y1,y2;


    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt(); b = scanner.nextInt(); c = scanner.nextInt(); y1 = scanner.nextInt(); y2 = scanner.nextInt();
        if(c==7){
            c = 0;
        }
//        cal.set(Calendar.YEAR,2020);
//        cal.set(Calendar.MONTH,8);
//        cal.set(Calendar.DATE,5);
//        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        for(int year=y1;year<=y2;year++){
            if(isLeapYear(year)){
                DD[2] = 29;
            }else{
                DD[2] = 28;
            }
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,a-1);  //a月
            cal.set(Calendar.DAY_OF_MONTH,1);   //1号
            int curday = cal.get(Calendar.DAY_OF_WEEK)-1;   //0-6代表星期天到星期六
            int date = 1;
            int numcount = 0;
            while(true){
                if(curday==c){
                    numcount++;
                    if(numcount==b){
                        break;
                    }
                }
                curday = (curday+1)%7;
                date++;
            }
//            System.out.println(date);
            if(date>DD[a]){
                System.out.println("none");
            }else{
                System.out.print(year+"/");
                if(a<10){
                    System.out.print("0"+a+"/");
                }else{
                    System.out.print(a+"/");
                }
                if(date<10){
                    System.out.println("0"+date);
                }
                else{
                    System.out.println(date);
                }
            }
        }

    }
    static boolean isLeapYear(int year){
        return year%400==0||(year%4==0&&year%100!=0);
    }
}
