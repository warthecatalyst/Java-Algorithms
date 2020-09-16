package CSP2016094;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static ArrayList<Edge>[] Node;
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
        Node = new ArrayList[n+1];
        for(int i=0;i<Node.length;i++){
            Node[i] = new ArrayList<>();
        }
        pre = new int[n+1];     //记录到某个点最短边的源点
        vis = new boolean[n+1]; //是否已经访问过该节点
        dist = new int[n+1];  //用来记录源点到该点的最短距离
        cost = new int[n+1];  //用来记录到某个点的最短边
        for(int i=0;i<m;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            Node[from].add(new Edge(to,weight));
            Node[to].add(new Edge(from,weight));
        }
        solve();
        int result = 0;
        for (int i = 1; i < cost.length; i++) {
            result += cost[i];
        }
        System.out.println(result);
    }

    public static void solve(){
        Arrays.fill(dist, INF);
        Arrays.fill(vis,false);
        Arrays.fill(cost,INF);
        pre[1] = 1;
        dist[1] = cost[1] = 0;
        for(int i=0;i<verNum;i++){
            int tempDist = INF;
            int tepVer = 0;
            for (int j = 1; j <= verNum; j++) {
                if (!vis[j] && dist[j] < tempDist) {
                    tempDist = dist[j];
                    tepVer = j;
                }
            }
            if(tepVer==0){
                break;
            }
            vis[tepVer] = true;
            for (int j = 0; j < Node[tepVer].size(); j++) {
                int to = Node[tepVer].get(j).to;
                int weight = Node[tepVer].get(j).weight;
                if (!vis[to] && dist[tepVer] + weight < dist[to]) {
                    dist[to] = dist[tepVer] + weight;
                    cost[to] = weight;
                } else if (!vis[to] && dist[tepVer] + weight == dist[to] && weight < cost[to]) {
                    cost[to] = weight;
                }
            }
        }

    }

    static class Edge{
        int to,weight;
        Edge(int to,int weight){
            this.to = to;
            this.weight = weight;
        }
    }
}
