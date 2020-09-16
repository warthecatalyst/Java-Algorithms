package CSP2016122;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        double S = 0.0;
        if(T<=3500){
            System.out.println(T);
            return;
        }else if(T<=4955){
            S = (T-105)/0.97;
        }else if(T<=7655){
            S = (T-455)/0.9;
        }else if(T<=11255){
            S = (T-1255)/0.8;
        }else if(T<=30755){
            S = (T-1880)/0.75;
        }else if(T<=44755){
            S = (T-3805)/0.7;
        }else if(T<=61005){
            S = (T-6730)/0.65;
        }else{
            S = (T-15080)/0.55;
        }
        int SS = ((int) S+1)/100*100;
        System.out.println(SS);
    }
}
