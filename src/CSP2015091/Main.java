package CSP2015091;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int last = 0, cur = 0;
        int count = 1;
        for(int i=1;i<=n;i++){
            if(i == 1){
                last = scanner.nextInt();
            }
            else{
                cur = scanner.nextInt();
                //System.out.println("i="+i+", cur="+cur+", last="+last);
                if(cur!=last){
                    count++;
                }
                last = cur;
            }
        }
        System.out.println(count);
    }
}
