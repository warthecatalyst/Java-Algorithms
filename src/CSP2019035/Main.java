package CSP2019035;

import java.util.*;

public class Main{
    static final int INF = 0x7f7f7f7f;
    static Scanner scanner = new Scanner(System.in);
    static boolean[] isFa;  //是否为发电站
    static int n,m,k;
    static int[][] dist;
    static List<Edge>[] edges;

    public static void main(String[] args) {
        n = scanner.nextInt(); m = scanner.nextInt(); k = scanner.nextInt();
        edges = new ArrayList[n+1];
        for(int i=0;i<edges.length;i++){
            edges[i] = new ArrayList<>();
        }
        isFa = new boolean[n+1];
        dist = new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dist[i],INF);
            dist[i][i] = 0;
        }
        for(int i=1;i<=n;i++){
            isFa[i] = scanner.nextInt()==1;
        }
        Set<int[]> edgeset = new HashSet<>();
        for(int i=0;i<m;i++){
            int from = scanner.nextInt(), to = scanner.nextInt(), cost = scanner.nextInt();
            //去除自环
            if(from==to){
                continue;
            }
            //重边留下最小的那个
            if(edgeset.contains(new int[]{from,to})||edgeset.contains(new int[]{to,from})){
                for(int j=0;j<edges[from].size();j++){
                    if(edges[from].get(j).to==to&&edges[from].get(j).cost>cost){
                        edges[from].get(j).cost = cost;
                        for(int k=0;k<edges[to].size();k++){
                            if(edges[to].get(k).to==from){
                                edges[to].get(k).cost = cost;
                            }
                        }
                    }
                }
                continue;
            }
            edgeset.add(new int[]{from,to});
            edges[from].add(new Edge(to,cost));
            edges[to].add(new Edge(from,cost));
        }
        //运行多次dijkstra算法
        for(int i=1;i<=n;i++){
            if(!isFa[i]){
                continue;
            }
            boolean[] vis = new boolean[n+1];
            int pickCount = 0;
            while(true){
                int k = 0,min = INF+1;  //可能不连通
                for(int j=1;j<=n;j++){
                    if(!vis[j]&&dist[i][j]<min){
                        k = j;
                        min = dist[i][j];
                    }
                }
                //System.out.println(k);
                pickCount++;
                vis[k] = true;
                if(pickCount==n){
                    break;
                }
                for(Edge edge:edges[k]){
                    if(!vis[edge.to]&&dist[i][k]+edge.cost<dist[i][edge.to]){
                        dist[i][edge.to] = dist[i][k]+edge.cost;
                    }
                }
            }
//            System.out.println("i = "+i);
//            for(int j=1;j<=n;j++){
//                System.out.print(dist[i][j]+" ");
//            }
//            System.out.println();
        }
        //计算离每个节点最近的k个发动机的距离
        for(int i=1;i<=n;i++){
            List<Integer> list = new ArrayList<>();
            int ans = 0;
            for(int j=1;j<=n;j++){
                if(!isFa[j]){
                    continue;
                }
                if(dist[j][i]>=INF){    //表示不连通
                    continue;
                }
                list.add(dist[j][i]);
            }
            Collections.sort(list);
            for(int j=0;j<Math.min(list.size(),k);j++){
                ans+=list.get(j);
            }
            System.out.println(ans);
        }
    }

    static class Edge implements Comparable<Edge>{
        int to,cost;
        Edge(int to,int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost-o.cost;
        }
    }

    static class Dijk implements Comparable<Dijk>{
        int Node, curdist;
        Dijk(int n,int dis){
            Node = n;
            curdist = dis;
        }

        @Override
        public int compareTo(Dijk o) {
            return curdist-o.curdist;
        }
    }
}
