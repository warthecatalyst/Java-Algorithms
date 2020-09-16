package CSP2018094.Reference;

import java.util.Scanner;

public class Main {
    static int n;
    static int[] a = new int[305];
    static int[] b = new int[305];
    static boolean[][][] vis = new boolean[305][305][305];
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for(int i=1;i<=n;i++){
            a[i] = scanner.nextInt();
        }
        for(b[1] = 1;b[1]<=2*a[1];b[1]++){
            if(DFS(2,2*a[1]-b[1])) break;
            if(DFS(2,2*a[1]-b[1]+1)) break;
        }
        for(int i=1;i<=n;i++){
            System.out.print(b[i]+" ");
        }
    }
    static boolean DFS(int cur,int val){
        if(val<=0||vis[cur][val][b[cur-1]]) return false;
        vis[cur][val][b[cur-1]] = true;
        b[cur] = val;
        if(cur==n){
            return (val+b[n-1])/2==a[n];
        }
        for(int i=0;i<3;i++){
            if(DFS(cur+1,a[cur]*3-b[cur]-b[cur-1]+i)){
                return true;
            }
        }
        return false;
    }
}
