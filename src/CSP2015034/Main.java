package CSP2015034;

import javax.naming.InsufficientResourcesException;
import java.util.*;

public class Main {
    static int n,m;
    static List<Integer>[] neighbours;
    static boolean[] visited;
    static int[] distance;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        neighbours = new ArrayList[n+m+1];
        for(int i=0;i<neighbours.length;i++){
            neighbours[i] = new ArrayList<Integer>();
        }
        visited = new boolean[n+m+1];
        distance = new int[n+m+1];
        for(int i = 2;i<=n;i++){
            int link = scanner.nextInt();
            neighbours[i].add(link);
            neighbours[link].add(i);
        }
        for(int i=1;i<=m;i++){
            int link = scanner.nextInt();
            neighbours[i+n].add(link);
            neighbours[link].add(i+n);
        }
        dfs(1,0);
        int maxN = 0, maxDis = 0;
        for(int i=1;i<distance.length;i++){
            if(distance[i]>maxDis){
                maxN = i;
                maxDis = distance[i];
            }
        }
        Arrays.fill(distance,0);
        Arrays.fill(visited,false);
        dfs(maxN,0);
        int ans = Integer.MIN_VALUE;
        for(int i=1;i<distance.length;i++){
            ans = Math.max(ans,distance[i]);
        }
        System.out.println(ans);
    }
    public static void dfs(int curNode,int curstep){
        visited[curNode] = true;
        for(int i=0;i<neighbours[curNode].size();i++){
            int m = neighbours[curNode].get(i);
            if(visited[m])
                continue;
            distance[m] = curstep+1;
            dfs(m,curstep+1);
        }
    }
}