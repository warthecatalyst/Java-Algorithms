package CSP2015033;

import java.util.Scanner;

public class Main {
    static int month,index,day,begin,end;
    static final int[] ordinary = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
    static final int[] runnian = new int[]{0,31,29,31,30,31,30,31,31,30,31,30,31};
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        month = scanner.nextInt();
        index = scanner.nextInt();
        day = scanner.nextInt();
        begin = scanner.nextInt();
        end = scanner.nextInt();
        if(day==7){
            day = 0;
        }
        int chushi = 2;
        for(int i=1850;i<begin;i++){
            if(i%400==0||(i%4==0&&i%100!=0)){
                chushi = (chushi+366)%7;
            }
            else{
                chushi = (chushi+365)%7;
            }
        }
        for(int j = 1;j<month;j++){
            if(begin%4==0&&begin%100!=0){
                chushi = (chushi+runnian[j])%7;
            }
            else{
                chushi = (chushi+ordinary[j])%7;
            }
        }
        //System.out.println(chushi);     //begin年份month月第一天是星期几
        for(int k = begin;k<=end;k++){
            int i = 0, cnt = index,curdate = chushi;          //判断
            while(cnt>0){
                if(curdate == day){
                    cnt--;
                }
                i++;
                //System.out.println(curdate);
                curdate = (curdate+1)%7;
            }
            boolean flag =k%400==0||(k%4==0&&k%100!=0);
            boolean flag1 =(k+1)%400==0||((k+1)%4==0&&(k+1)%100!=0);
            if((flag&&i>runnian[month])||(!flag&&i>ordinary[month])){
                System.out.println("none");
                if((flag&&month<=2)||(flag1&&month>2)){
                    chushi = (chushi+366)%7;
                }
                else{
                    chushi = (chushi+365)%7;
                }
                continue;
            }
            System.out.print(k+"/");
            if(month<10){
                System.out.print("0"+month+'/');
            }
            else {
                System.out.print(month+"/");
            }
            System.out.println(i);
            if((flag&&month<=2)||(flag1&&month>2)){
                chushi = (chushi+366)%7;
            }
            else{
                chushi = (chushi+365)%7;
            }
        }
    }
}