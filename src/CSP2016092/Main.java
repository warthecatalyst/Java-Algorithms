package CSP2016092;

import java.util.Scanner;

public class Main {
    static boolean[] isbought = new boolean[110];
    static int[] rows = new int[21];
    public static void main(String[] args){
        for(int i=0;i<=20;i++){
            rows[i] = 5;
        }
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i=0;i<n;i++){
            boolean flag = false;
            int cur = scanner.nextInt();
            for(int j=0;j<20;j++){
                if (rows[j]>=cur){  //能够安排在相邻的座位上
                    flag = true;
                    rows[j]-=cur;
                    for(int k=5*j+1,l=0;k<=5*j+5&&l<cur;k++){
                        if(!isbought[k]){
                            l++;
                            isbought[k] = true;
                            System.out.print(k+" ");
                        }
                    }
                    System.out.println();
                    break;
                }
            }
            if(!flag) {//不能安排在相邻的座位上
                for (int k = 1, l = 0; k <= 100 && l < cur; k++) {
                    //System.out.println("k= "+k+" ,l="+l);
                    if (!isbought[k]) {
                        l++;
                        isbought[k] = true;
                        int mm = k / 5;
                        rows[mm]--; //不要忘记这一列的座位数也要减少
                        System.out.print(k + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
