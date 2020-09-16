package SolveP4.CSP2016044;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main { //游戏
    static final int[][] direction = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    static int n,m,t;
    static boolean[][][] isDanger;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); m = scanner.nextInt(); t = scanner.nextInt();
        isDanger = new boolean[n+1][m+1][10010];
        for(int i=0;i<t;i++){
            int R = scanner.nextInt();
            int C = scanner.nextInt();
            int start = scanner.nextInt(), end = scanner.nextInt();
            for(int j=start;j<=end;j++){
                isDanger[R][C][j] = true;
            }
        }
        boolean vis[][][] = new boolean[n+1][m+1][300];
        Queue<int[]> queue = new LinkedList<>();
        vis[1][1][0] = true;
        queue.add(new int[]{1,1,0});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curx = cur[0],cury = cur[1],curtime = cur[2];
            //System.out.println(curx+" "+cury);
            if(curx==n&&cury==m){
                System.out.println(curtime);
                return;
            }
            for(int i=0;i<4;i++){
                int newx = curx+direction[i][0],newy = cury+direction[i][1];
                if(!in(newx,newy)||vis[newx][newy][curtime+1]) continue;
                if(isDanger[newx][newy][curtime+1]) continue;
                vis[newx][newy][curtime+1] = true;
                queue.add(new int[]{newx,newy,curtime+1});
            }
        }
    }

    static boolean in(int x,int y){
        return x>0&&x<=n&&y>0&&y<=m;
    }
}
