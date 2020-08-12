package CSP2014094;

import java.util.*;

public class Main {
    final static int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
    final static int N = 1005;
    static int n,m,k,d;
    static Queue<Node> queue = new LinkedList<>();
    static boolean[][] vis;
    static int[][] w;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        vis = new boolean[n+1][n+1];
        w = new int[n+1][n+1];
        m = scanner.nextInt();
        k = scanner.nextInt();
        d = scanner.nextInt();
        for(int i=0;i<m;i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            vis[x][y] = true;
            queue.add(new Node(x,y,0));
        }
        for(int i=0;i<k;i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            w[x][y] = z;
        }
        for(int i=0;i<d;i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            vis[x][y] = true;
        }
        System.out.println(BFS());
    }
    public static long BFS(){
        long ans = 0;
        int cnt = 0;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i=0;i<4;i++){
                int x = cur.x+direction[i][0];
                int y = cur.y+direction[i][1];
                int cost = cur.cost+1;
                if((x>0&&x<=n&&y>0&&y<=n)&&!vis[x][y]){
                    vis[x][y] = true;
                    if(w[x][y]!=0){
                        ans += (long)w[x][y]*cost;
                        if(++cnt == k){
                            return ans;
                        }
                    }
                    queue.add(new Node(x,y,cost));
                }
            }
        }
        return ans;
    }
}

class Node{
    int x,y,cost;
    public Node(int x,int y,int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}
