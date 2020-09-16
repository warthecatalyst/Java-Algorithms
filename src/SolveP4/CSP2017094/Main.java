package SolveP4.CSP2017094;

import javax.naming.AuthenticationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Integer>[] edges;
    static List<Integer>[] reedges;
    static int n,m;
    static boolean[][] canVisit;
    static boolean[] vis;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); m = scanner.nextInt();
        edges = new ArrayList[n+1];
        reedges = new ArrayList[n+1];
        canVisit = new boolean[n+1][n+1];
        vis = new boolean[n+1];
        for(int i=0;i<edges.length;i++){
            edges[i] = new ArrayList<>();
            reedges[i] = new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int from = scanner.nextInt(), to = scanner.nextInt();
            edges[from].add(to);
            reedges[to].add(from);
        }
//        System.out.println(Arrays.toString(edges));
//        System.out.println(Arrays.toString(reedges));
        for(int i=1;i<=n;i++){
            Arrays.fill(vis,false);
            DFS1(i,i);
            Arrays.fill(vis,false);
            DFS2(i,i);
        }
        int ans = 0;
        //System.out.println(Arrays.deepToString(canVisit));
        for(int i=1;i<=n;i++){
            boolean flag = true;
            for(int j=1;j<=n;j++){
                if(!canVisit[i][j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                ans++;
            }
        }
        System.out.println(ans);
    }
    static void DFS1(int root,int cur){
        //System.out.println("In DFS1 "+cur);
        canVisit[root][cur] = true;
        vis[cur] = true;
        for(int v:edges[cur]){
//            System.out.println(v);
            if(vis[v]) continue;
            DFS1(root,v);
        }
    }

    static void DFS2(int root,int cur){
//        System.out.println("In DFS2 "+cur);
        vis[cur] = true;
        canVisit[root][cur] = true;
        for(int v:reedges[cur]){
            if(vis[v]) continue;
            DFS2(root,v);
        }
    }
}
