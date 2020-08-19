package CSP2014124;

import java.util.*;

public class Main {
    static PriorityQueue<Edge> queue = new PriorityQueue<>();  //from,to,cost
    static int[] pre;
    static int find(int x){
        if(pre[x]==x) return x;
        else return pre[x] = find(pre[x]);
    }
    static void link(int x,int y){
        int prex = find(x);
        int prey = find(y);
        if(prex>prey) pre[prex] = prey;
        else pre[prey] = prex;
    }
    public static void main(String[] args){ //最小生成树问题，使用Kruskal算法
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        pre = new int[n+1];
        for(int i=0;i<n+1;i++){
            pre[i] = i;
        }
        for(int i=0;i<m;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            queue.add(new Edge(from,to,cost));
        }
        int ans = 0;
        Edge temp = new Edge();
        while(!queue.isEmpty()){
            temp = queue.poll();
            int from = temp.from;
            int to = temp.to;
            if(find(from)==find(to)) continue;
            link(from,to);
            ans += temp.cost;
        }
        System.out.println(ans);
    }
}

class Edge implements Comparable<Edge>{
    int from,to,cost;
    public Edge(){
    }
    public Edge(int from,int to,int value){
        this.from = from;
        this.to = to;
        this.cost = value;
    }
    @Override
    public int compareTo(Edge o) {
        if(cost==o.cost) return from-o.from;
        return cost-o.cost;
    }
}
