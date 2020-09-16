package SolveP4.CSP2015094;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int N,M;
    static int DFN[];
    static int LOW[];
    static boolean[] vis;
    static List<Integer>[] edges;
    static int index = 0;
    static long ans = 0;
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) {
        N = scanner.nextInt(); M = scanner.nextInt();
        DFN = new int[N+1]; LOW = new int[N+1];
        vis = new boolean[N+1];
        edges = new ArrayList[N+1];
        for(int i=0;i<edges.length;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            int from = scanner.nextInt(), to = scanner.nextInt();
            edges[from].add(to);
        }
        for(int i=1;i<=N;i++){
            if(!vis[i]){
                tarjan(i);
            }
        }
        System.out.println(ans);
    }

    static void tarjan(int u){
        vis[u] = true;
        DFN[u] = LOW[u] = ++index;
        stack.add(u);
        for(int v:edges[u]){
            if(!vis[v]){
                tarjan(v);
                LOW[u] = Math.min(LOW[u],LOW[v]);
            }
            else if(stack.contains(v)){
                LOW[u] = Math.min(LOW[u],DFN[v]);
            }
        }
        if(DFN[u]==LOW[u]){
            int count = 0;
            int v = stack.pop();
            count++;
            while(u!=v&&!stack.isEmpty()){
                v = stack.pop();
                count++;
            }
            ans += count*(count-1)/2;
        }
    }
}
