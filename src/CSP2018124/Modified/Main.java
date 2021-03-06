package CSP2018124.Modified;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n,m,root;
    static int[] pre;
    static Edge[] list;
    static int Find(int x){
        if(pre[x]==x){
            return x;
        }
        else return pre[x] = Find(pre[x]);
    }

    static void Union(int x,int y){
        int xx = Find(x),yy = Find(y);
        if(xx<yy){
            pre[yy] = xx;
        }
        else{
            pre[xx] = yy;
        }
    }

    public static void main(String[] args) {
        n = scanner.nextInt(); m = scanner.nextInt(); root = scanner.nextInt();
        pre = new int[n+1];
        for(int i=1;i<=n;i++){
            pre[i] = i;
        }
        list = new Edge[m];
        for(int i=0;i<m;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int val = scanner.nextInt();
            Edge edge = new Edge(from,to,val);
            list[i] = edge;
        }
        Arrays.sort(list);
        int pickcount = 0;
        int ti = Integer.MIN_VALUE;
        for(int i=0;i<m;i++){
            Edge cur = list[i];
            int x = cur.from;
            int y = cur.to;
            if(Find(x)==Find(y)){
                continue;
            }
            Union(x,y);
            ti = Math.max(ti,cur.val);
            pickcount++;
            if(pickcount==n-1){
                break;
            }
        }
        System.out.println(ti);
    }

    static class Edge implements Comparable<Edge>{
        int from,to;
        int val;
        Edge(int from,int to,int val){
            this.from = from;
            this.to = to;
            this.val = val;
        }

        @Override
        public int compareTo(Edge o) {
            return val-o.val;
        }
    }
}