package CSP2014092;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
    }
}

class Solution{
    final int N = 100+10;
    boolean[][] vis = new boolean[N][N];
    int n;
    int a,b,c,d;
    int ans = 0;
    public Solution(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for(int i=0;i<n;i++){
            a = scanner.nextInt();
            b = scanner.nextInt();
            c = scanner.nextInt();
            d = scanner.nextInt();
            for(int j=a;j<c;j++){
                for(int k=b;k<d;k++){
                    if(!vis[j][k]){
                        vis[j][k] = true;
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
