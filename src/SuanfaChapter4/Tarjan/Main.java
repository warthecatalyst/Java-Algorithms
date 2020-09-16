package SuanfaChapter4.Tarjan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static int ans = 0;
    static Scanner scanner = new Scanner(System.in);
    static int[] Dfn;
    static int[] low;
    static int index = 0;
    static List<Integer>[] edges;
    static Stack<Integer> stack = new Stack<>();
    static List<Integer>[] arr;
    static boolean[] vis;
    static int M,N;
    public static void main(String[] args) {
        N = scanner.nextInt(); M  = scanner.nextInt();
        Dfn = new int[N+1]; low = new int[N+1]; vis = new boolean[N+1];
        edges = new ArrayList[N+1];
        for(int i=0;i<edges.length;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            int from = scanner.nextInt(), to = scanner.nextInt();
            edges[from].add(to);
        }
        for(int i=1;i<=N;i++){
            System.out.println(edges[i]);
        }
        arr = new ArrayList[N+1];
        for(int i=0;i<arr.length;i++){
            arr[i] = new ArrayList<>();
        }
        for(int i=1;i<=N;i++){
            if(!vis[i]){
                tarjan(i);
            }
        }
//        for(int i=1;i<=N;i++){
//            arr[Dfn[i]].add(i);
//        }
        for(int i=1;i<=N;i++){
            if(!arr[i].isEmpty()) {
                System.out.println(arr[i]);
            }
        }
    }
    static void tarjan(int u){
        vis[u] = true;
        Dfn[u] = low[u] = ++index;
        stack.add(u);
        //System.out.println(stack);
        for(int v:edges[u]){
            if(!vis[v]){  //未被访问过
                tarjan(v);  //继续向下找
                low[u] = Math.min(low[u],low[v]);
            }
            else if(stack.contains(v)){
                low[u] = Math.min(low[u],Dfn[v]);
            }
        }
        if (Dfn[u]==low[u]){    //若u为强联通分量的根
            System.out.println(u);
            int count = 0;
            int v;
            do{
                v = stack.pop();
                arr[u].add(v);
                count++;
            }while (u!=v);
            ans+=count*(count-1)/2;
        }
    }
}
