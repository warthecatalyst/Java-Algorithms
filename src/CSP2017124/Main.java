package CSP2017124;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main { //50分，本来以为可以70
    static final int INF = 0x7f7f7f7f;
    static int m,n;
    static boolean[] vis;
    static int[] dist;  //dijkstra算法
    static List<Edge>[] list;
    static List<List<Integer>> routes = new ArrayList<>();
    public static void main(String[] args) {    //70分解法
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        dist = new int[n + 1];
        vis = new boolean[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            boolean flag = scanner.nextInt() == 1;  //1表示小路
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            list[from].add(new Edge(to, cost, flag));
            list[to].add(new Edge(from, cost, flag));
        }
        if (n <= 8 && m <= 10) {
            //小算例就DFS暴力搜索找到路径
            List<Integer> route = new ArrayList<>();
            route.add(1);
            DFS(1,route);
            int cost = INF;
            for(List<Integer> route1:routes){
//                System.out.println(route1);  //找到所有的路径
//                System.out.println(calCost(route1));
                cost = Math.min(cost,calCost(route1));
            }
            System.out.println(cost);
        }
        else{   //dijkstra算法，另外40%的算例直接解决
            for(int i=1;i<list.length;i++){
                for(Edge edge:list[i]){
                    if(edge.flag){
                        edge.cost = edge.cost*edge.cost;    //之后flag就无用了
                    }
                }
            }
            Arrays.fill(dist,INF);
            Arrays.fill(vis,false);
            dist[1] = 0;
            int cnt = 0;
            while(cnt<n) {
                int k = 0 ,cost = INF;
                for (int i = 1; i <= n; i++) {  //第一次循环找到当前最小
                    if (!vis[i] && dist[i] < cost) {
                        k = i;
                        cost = dist[i];
                    }
                }
                //选出了k
                vis[k] = true;
                cnt++;
                if (k == n) {
                    System.out.println(dist[n]);
                    return;
                }
                //更新其他相邻边的权重
                for (Edge edge : list[k]) {
                    int to = edge.to;
                    if (!vis[to] && dist[k] + edge.cost < dist[to]) {
                        dist[to] = dist[k] + edge.cost;
                    }
                }
            }
            System.out.println(dist[n]);
        }
    }
    public static void DFS(int cur,List<Integer> curroute){//分别代表当前的位置,以及当前遍历过的节点
        if(cur==n){
            routes.add(new ArrayList<>(curroute));
            return;
        }

        List<Edge> neighbours = list[cur];
        for (Edge next : neighbours) {
            boolean flag = true;
            for (Integer integer : curroute) {  //保证之前没有通过当前的节点
                if (next.to == integer) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                curroute.add(next.to);
                DFS(next.to,curroute);
                curroute.remove(curroute.size()-1);
            }
        }
    }
    public static int calCost(List<Integer> route){
        int cost = 0,length = 0;    //分别代表当前的消耗和当前经过的小路的长度
        for(int i=0;i<route.size()-1;i++){
            int from = route.get(i);
            int to = route.get(i+1);
            Edge cur = null;
            for(Edge edge:list[from]){
                if(edge.to == to){
                    cur = edge;
                    break;
                }
            }
            assert cur != null;
            if(cur.flag){   //如果是小路
                length+=cur.cost;
            }else{  //如果是大路
                cost += length*length+cur.cost;
                length = 0; //清空小路的长度
            }
        }
        if(length!=0){
            cost+=length*length;
        }
        return cost;
    }

    static class Edge{
        int to,cost;
        boolean flag;   //是否为小道
        Edge(int to,int cost,boolean flag){
            this.to = to;
            this.flag = flag;
            this.cost = cost;
        }
    }
}

