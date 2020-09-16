package SolveP4.CSP2016094;

import java.util.*;

public class Main {
    static final int INF = 0x7f7f7f7f;
    static List<Edge>[] edges;
    static int[] dist;
    static boolean[] vis;
    static int[] cost;
    static int n,m;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); m = scanner.nextInt();
        edges = new ArrayList[n+1];
        dist = new int[n+1];
        vis = new boolean[n+1];
        cost = new int[n+1];
        for(int i=0;i<edges.length;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            edges[from].add(new Edge(to,cost));
            edges[to].add(new Edge(from,cost));
        }
        Arrays.fill(dist,INF);
        Arrays.fill(cost,INF);
        dist[1] = cost[1] = 0;
        Queue<Dist> queue = new PriorityQueue<>();
        queue.add(new Dist(1,0));
        //int picknum = 0;
        while (!queue.isEmpty()){
            Dist cur = queue.poll();
            int ver = cur.vertex;
//            int curcost = cur.value;
//            picknum++;
//            if(picknum==n){
//                break;
//            }
            vis[ver] = true;
            for(Edge edge:edges[ver]){
                if(vis[edge.to]) continue;
                int to = edge.to,weight = edge.weight;
                if(dist[ver]+weight<dist[to]){
                    dist[to] = dist[ver]+weight;
                    cost[to] = weight;
                    queue.add(new Dist(to,dist[to]));
                }else if(dist[ver]+weight==dist[to]&&weight<cost[to]){
                    cost[to] = weight;
                }
            }
        }
        int result = 0;
        for(int i=1;i<cost.length;i++){
            result+= cost[i];
        }
        System.out.println(result);
    }
    static class Edge{
        int to,weight;
        Edge(int to,int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class Dist implements Comparable<Dist>{
        int vertex;
        int value;
        Dist(int vertex,int value) {
            this.vertex = vertex;
            this.value = value;
        }
        @Override
        public int compareTo(Dist o) {
            if(value==o.value){
                return vertex-o.vertex;
            }
            return value-o.value;
        }
    }
}
