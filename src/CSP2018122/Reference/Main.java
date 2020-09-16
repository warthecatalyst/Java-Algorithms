package CSP2018122.Reference;

import java.util.Scanner;

public class Main {
    static int r,y,g;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        r = scanner.nextInt(); y = scanner.nextInt(); g = scanner.nextInt();
        int x = r+y+g;
        //System.out.println(x);
        int n = scanner.nextInt();
        long ans = 0;
        for(int i=0;i<n;i++){
            int type = scanner.nextInt();
            int time = scanner.nextInt();
            long tmp = 0;
            if(type==0){
                ans+=time;
            }else {
                tmp = ans%x;    //经过n轮之后在数轴上移动的距离
                if(type==1){    //红
                    tmp += r-time;
                }else if(type==2){  //黄
                    tmp+= x-time;
                }else if(type==3){  //绿
                    tmp += r+g-time;
                }
                tmp %= x;
                //System.out.println(tmp);
                if(tmp<r){
                    ans += r-tmp;
                }else if(tmp<r+g){
                    ans += 0;
                }else{
                    //System.out.println(x-tmp+r);
                    ans += x-tmp+r;
                }
            }
            //System.out.println(ans);
        }
        System.out.println(ans);
    }
}
