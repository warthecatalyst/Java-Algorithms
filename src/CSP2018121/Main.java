package CSP2018121;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int r,y,g;
        Scanner scanner = new Scanner(System.in);
        r = scanner.nextInt(); y = scanner.nextInt(); g = scanner.nextInt();
        int n = scanner.nextInt();
        int time = 0;
        for(int i=0;i<n;i++){
            int type = scanner.nextInt();
            int ff = scanner.nextInt();
            if(type==0){
                time += ff;
            }else if(type==1){
                time += ff;
            }else if(type==2){
                time += ff+r;
            }else{
                continue;
            }
        }
        System.out.println(time);
    }
}
