package SolveP4.CSP2015034;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int m,n;
    static List<Integer>[] edges;
    static boolean[] vis;
    static int[] dist;
    public static void main(String[] args) {
        n = scanner.nextInt();
        m = scanner.nextInt();
        vis = new boolean[m+n+1];
        dist = new int[m+n+1];
        edges = new ArrayList[m+n+1];
        for(int i=0;i<edges.length;i++){
            edges[i] = new ArrayList<>();
        }
        int i=2;
        while(i<=n+m){
            int cur = scanner.nextInt();
            edges[i].add(cur);
            edges[cur].add(i);
            i++;
        }
//        for(int j=1;j<=m+n;j++){
//            System.out.println(edges[j]);
//        }
        DFS(1,0);
        int k = 0,max = 0;
//        for(int j=1;j<=m+n;j++){
//            System.out.print(dist[j]+" ");
//        }
        for(int j=1;j<dist.length;j++){
            if(dist[j]>max){
                k = j;
                max = dist[j];
            }
        }
        Arrays.fill(dist,0);
        Arrays.fill(vis,false);
        DFS(k,0);
        int maxdist = Integer.MIN_VALUE;
        for(int j=1;j<=m+n;j++){
            if(dist[j]>maxdist){
                maxdist = dist[j];
            }
        }
        System.out.println(maxdist);
    }

    static void DFS(int cur,int cost){
        vis[cur] = true;
        for(int v:edges[cur]){
            if(vis[v]) continue;
            dist[v] = cost+1;
            DFS(v,cost+1);
        }
    }
}
