package GraphAlgorithms.Dijkstra;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int INF = 0x7f7f7f7f;
    static List<Edge>[] list;
    static boolean[] vis;
    static int[] dist;
    static int n,m;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        list = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            list[i] = new ArrayList<>();
        }
        vis = new boolean[n+1];
        Arrays.fill(vis,false);
        dist = new int[n+1];
        Arrays.fill(dist,INF);
        dist[1] = 0;
        for(int i=0;i<m;i++){       //读入边
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            list[from].add(new Edge(to,weight));
            list[to].add(new Edge(from,weight));
        }
        for(int i=1;i<=n;i++){
            int tempDist = INF;
            int k = 0;
            for(int j=1;j<=n;j++){
                if(!vis[j]&&dist[j]<tempDist){
                    tempDist = dist[j];
                    k = j;
                }
            }
            vis[k] = true;
            for(int j=0;j<list[k].size();j++){
                int l = list[k].get(j).to;
                if(!vis[l]){
                    dist[l] = Math.min(dist[l],dist[k]+list[k].get(j).weight);
                }
            }
        }
        for(int i=1;i<=n;i++){
            System.out.println("到达"+i+"的最短距离为:"+dist[i]);
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
