package SolveP4.CSP2014094;

import java.util.*;

public class Main {
    static final int[][] direction = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    static Scanner scanner = new Scanner(System.in);
    static int n,m,k,d;
    static boolean[][] isforbitted;     //表示该点是否不能经过
    static Set<Pair> fendian = new HashSet<>();    //栋栋的分店
    static Map<Pair,Integer> nums = new HashMap<>();   //每个用户订餐的数量
    public static void main(String[] args) {
        n = scanner.nextInt(); m = scanner.nextInt(); k = scanner.nextInt(); d = scanner.nextInt();
        isforbitted = new boolean[n+1][n+1];
        for(int i=0;i<m;i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            fendian.add(new Pair(x,y));
        }
        for(int i=0;i<k;i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int num = scanner.nextInt();
            nums.put(new Pair(x,y),num);
        }
        for(int i=0;i<d;i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            isforbitted[x][y] = true;
        }
        long ans = 0;
        for(Map.Entry<Pair,Integer> entry:nums.entrySet()){
            int num = entry.getValue();
            Pair cur = entry.getKey();
            int curx = cur.x,cury = cur.y;
            ans += (long) num*BFS(curx,cury);
        }
        System.out.println(ans);
    }
    static int BFS(int startx,int starty){
        boolean[][] vis = new boolean[n+1][n+1];
        Queue<Node> queue = new LinkedList<>();
        vis[startx][starty] = true;
        queue.add(new Node(new Pair(startx,starty),0));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int curx = cur.pair.x,cury = cur.pair.y,cost = cur.cost;
            if(fendian.contains(cur.pair)){
                return cost;
            }
            for(int i=0;i<4;i++){
                int newx = curx+direction[i][0], newy = cury+direction[i][1];
                if(!in(newx,newy)) continue;
                if(vis[newx][newy]||isforbitted[newx][newy]) continue;
                vis[newx][newy] = true;
                queue.add(new Node(new Pair(newx,newy),cost+1));
            }
        }
        return 0;
    }

    static boolean in(int x,int y){
        return x>0&&x<=n&&y>0&&y<=n;
    }

    static class Pair{
        int x,y;
        Pair(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Node{
        Pair pair;
        int cost;
        Node(Pair pair,int cost){
            this.pair = pair;
            this.cost = cost;
        }
    }
}
