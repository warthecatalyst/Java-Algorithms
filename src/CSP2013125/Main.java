package CSP2013125;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    final static int N = 60;
    final static int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};//下上右左
    static char[][] map = new char[N][N];
    static boolean[][] vis = new boolean[N][N]; //用于第一轮BFS，表示能否到达某个点
    static boolean[][] vis1 = new boolean[N][N];    //用于第二轮BFS,表示能否由这个点到达终点
    static int sx,sy,tx,ty;
    static int R,C;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        R = scanner.nextInt();
        C = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<R;i++){
            String s = scanner.nextLine();
            for(int j=0;j<C;j++){
                map[i][j] = s.charAt(j);
                if(map[i][j]=='S'){
                    sx = i;
                    sy = j;
                }
                if(map[i][j]=='T'){
                    tx = i;
                    ty = j;
                }
            }
        }
        bfs1();
//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(vis[i][j]+" ");
//            }
//            System.out.println();
//        }
        if(!vis[tx][ty]){
            System.out.println("I'm stuck!");
            return;
        }
        int ans = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(vis[i][j]&&bfs2(i,j)){
                    //System.out.println("i = "+i+" j = "+j);
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    static void bfs1(){ //从起点开始BFS，查询能到达的点
        Queue<int[]> queue= new LinkedList<>();
        queue.add(new int[]{sx,sy});
        vis[sx][sy] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int nx = cur[0], ny = cur[1];
            for(int i=0;i<4;i++){
                if(map[nx][ny]=='-'&&(i==0||i==1)) continue;
                if(map[nx][ny]=='|'&&(i==2||i==3)) continue;
                if(map[nx][ny]=='.'&&i!=0) continue;
                int x = nx+direction[i][0];
                int y = ny+direction[i][1];
                if(!in(x,y)||vis[x][y]||map[x][y]=='#') continue;
                //System.out.println("x = "+x+" y = "+y);
                vis[x][y] = true;
                queue.add(new int[]{x,y});
            }
        }
    }

    static boolean bfs2(int x,int y){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                vis1[i][j] = false;
            }
        }   //将所有能到达的地方清零
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        vis1[x][y] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int nx = cur[0], ny = cur[1];
            if(map[nx][ny]=='T'){
                return false;
            }
            for(int i=0;i<4;i++){
                if(map[nx][ny]=='-'&&(i==0||i==1)) continue;
                if(map[nx][ny]=='|'&&(i==2||i==3)) continue;
                if(map[nx][ny]=='.'&&i!=0) continue;
                int x1 = nx+direction[i][0];
                int y1 = ny+direction[i][1];
                if(!in(x1,y1)||vis1[x1][y1]||map[x1][y1]=='#') continue;
                vis1[x1][y1] = true;
                queue.add(new int[]{x1,y1});
            }
        }
        return true;
    }

    static boolean in(int x,int y){
        return x>=0&&x<R&&y>=0&&y<C;
    }
}

