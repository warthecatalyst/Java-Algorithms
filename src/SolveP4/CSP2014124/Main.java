package SolveP4.CSP2014124;

import javax.xml.stream.events.EntityDeclaration;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n,m;
    static Queue<Edge> queue = new PriorityQueue<>();
    static int[] pre;

    static int Find(int x){
        if(pre[x]==x){
            return x;
        }
        else return pre[x]=Find(pre[x]);
    }

    public static void main(String[] args) {
        n = scanner.nextInt();
        m = scanner.nextInt();
        pre = new int[n+1];
        for(int i=1;i<pre.length;i++){
            pre[i] = i;
        }
        for(int i=0;i<m;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int val = scanner.nextInt();
            queue.add(new Edge(from,to,val));
        }
        int pickednum = 0;
        int ans = 0;
        while (!queue.isEmpty()){
            Edge edge = queue.poll();
            int x = edge.from, y = edge.to;
            if(Find(x)==Find(y)) continue;
            int prex = Find(x),prey = Find(y);
            if(prex<prey){
                pre[prey] = prex;
            }else{
                pre[prex] = prey;
            }
            ans+= edge.cost;
            pickednum++;
            if(pickednum==n-1){
                break;
            }
        }
        System.out.println(ans);
    }
    static class Edge implements Comparable<Edge> {
        int from,to,cost;
        Edge(int from,int to,int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost-o.cost;
        }
    }
}
