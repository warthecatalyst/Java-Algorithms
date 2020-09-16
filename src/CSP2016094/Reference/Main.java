package CSP2016094.Reference;

import java.util.*;
public class Main {
    static ArrayList<Edge>[] nodes;
    static final int INF = 0x7f7f7f7f;
    static int[] pre;
    static int[] dist;
    static boolean[] vis;
    static int verNum;
    static int[] cost;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        verNum = n;
        nodes = new ArrayList[n+1];
        dist = new int[n + 1];
        pre = new int[n + 1];
        vis = new boolean[n + 1];
        cost = new int[n + 1];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            nodes[from].add(new Edge(to, weight));
            nodes[to].add(new Edge(from, weight));
        }
        solve();
        int result = 0;
        for(int i=1;i<cost.length;i++){
            result += cost[i];
        }
        System.out.println(result);
    }

    static void solve(){
        Arrays.fill(dist,INF);
        Arrays.fill(cost,INF);
        Arrays.fill(vis,false);
        pre[1] = 1;
        dist[1] = 0;
        cost[1] = 0;
        PriorityQueue<Dist> queue = new PriorityQueue<>();
        queue.add(new Dist(1, 0));
        while (!queue.isEmpty()) {
            int tmpDist = queue.peek().value;
            int tmpVer = queue.poll().vertex;
            vis[tmpVer] = true;
            for (int j = 0; j < nodes[tmpVer].size(); j++) {
                int to = nodes[tmpVer].get(j).to;
                int weight = nodes[tmpVer].get(j).weight;
                if (!vis[to] && dist[tmpVer] + weight < dist[to]) {
                    dist[to] = dist[tmpVer] + weight;
                    cost[to] = weight;
                    queue.add(new Dist(to, dist[to]));
                } else if (!vis[to] && dist[tmpVer] + weight == dist[to] && weight < cost[to]) {
                    cost[to] = weight;
                    pre[to] = j;
                }
            }
        }
    }
    static class Edge{
        int to,weight;
        public Edge(int to,int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class Dist implements Comparable<Dist>{
        int vertex,value;
        public Dist(int vertex,int value){
            this.vertex = vertex;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Dist)) return false;
            Dist dist = (Dist) o;
            return vertex == dist.vertex &&
                    value == dist.value;
        }

        @Override
        public int hashCode() {
            return vertex;
        }

        @Override
        public int compareTo(Dist o) {
            return value - o.value;
        }
    }
}
