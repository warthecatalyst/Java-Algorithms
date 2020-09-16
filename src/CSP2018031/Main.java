package CSP2018031;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int ans = 0, length = 1;
        Scanner scanner = new Scanner(System.in);
        while(true){
            int cur = scanner.nextInt();
            if(cur==0){
                break;
            }
            if(cur==1){
                ans += 1;
                length = 1;
            }
            else if(cur==2){
                ans += length*2;
                length++;
            }
        }
        System.out.print(ans);
    }
}
