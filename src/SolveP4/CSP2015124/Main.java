package SolveP4.CSP2015124;

import java.sql.ClientInfoStatus;
import java.util.*;

public class Main { //求解欧拉回路
    static List<Integer>[] edges;
    static boolean[][] vis;
    static Stack<Integer> stack = new Stack<>();
    static int[] pre;   //并查集，判断图是否连通

    static int Find(int x){
        if(pre[x]==x) return x;
        return pre[x] = Find(pre[x]);
    }

    static void Union(int x,int y){
        int xx = Find(pre[x]);
        int yy = Find(pre[y]);
        if(xx==yy) return;
        if(xx<yy){
            pre[yy] = xx;
        }else{
            pre[xx] = yy;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        vis = new boolean[n+1][n+1];
        pre = new int[n+1];
        for(int i=0;i<pre.length;i++){
            pre[i] = i;
        }
        edges = new ArrayList[n+1];
        for(int i=0;i<edges.length;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            edges[from].add(to);
            edges[to].add(from);
            Union(from,to);
        }
        boolean canSolve = true;
        int count = 0;
        int root = Find(1);
        for(int i=1;i<=n;i++){
            if(Find(i)!=root){
                canSolve = false;
                break;
            }
        }
        if(!canSolve){
            System.out.println(-1);
            return;
        }
        for(int i=1;i<=n;i++){
            if(edges[i].size()%2!=0){
                count++;
            }
        }
        if(count!=0&&count!=2){
            System.out.println(-1);
            return;
        }
        for(int i=1;i<edges.length;i++){
            Collections.sort(edges[i]);
        }
        DFS(1);
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }
    static void DFS(int cur){
        for(int v:edges[cur]){
            if(!vis[cur][v]){
                vis[cur][v] = true;
                vis[v][cur] = true;
                DFS(v);
            }
        }
        stack.push(cur);
    }
}
