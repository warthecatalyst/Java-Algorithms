package LCP13;

import java.util.*;

public class Main {
    public static void main(String[] args){

    }
}

class Solution{
    int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};    //上下左右四个方向
    int n,m;
    List<int[]> buttons = new ArrayList<>();    //按钮和石头
    List<int[]> stones = new ArrayList<>();
    int sx,sy,tx,ty;    //起点终点
    public int minimalSteps(String[] maze) {
        n = maze.length;
        m = maze[0].length();
        //机关和石头
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(maze[i].charAt(j)=='M'){
                    buttons.add(new int[]{i,j});
                }
                if(maze[i].charAt(j)=='O'){
                    stones.add(new int[]{i,j});
                }
                if(maze[i].charAt(j)=='S'){
                    sx = i;
                    sy = j;
                }
                if(maze[i].charAt(j)=='T'){
                    tx = i;
                    ty = j;
                }
            }
        }
        int nb = buttons.size();
        int ns = stones.size();
        int[][] startDist = bfs(sx,sy,maze);

        //边界情况，没有机关
        if(nb==0){
            return startDist[tx][ty];
        }
        //从某个机关到其他机关，起点与终点的最短距离
        int[][] dist = new int[nb][nb+2];
        for(int i=0;i<nb;i++){
            Arrays.fill(dist[i],-1);
        }
        int[][][] dd = new int[nb][][];
        for (int i = 0; i < nb; i++) {
            int[][] d = bfs(buttons.get(i)[0], buttons.get(i)[1], maze);
            dd[i] = d;
            // 从某个点到终点不需要拿石头
            dist[i][nb + 1] = d[tx][ty];
        }

        for (int i = 0; i < nb; i++) {
            int tmp = -1;
            for (int k = 0; k < ns; k++) {
                int midX = stones.get(k)[0], midY = stones.get(k)[1];
                if (dd[i][midX][midY] != -1 && startDist[midX][midY] != -1) {
                    if (tmp == -1 || tmp > dd[i][midX][midY] + startDist[midX][midY]) {
                        tmp = dd[i][midX][midY] + startDist[midX][midY];
                    }
                }
            }
            dist[i][nb] = tmp;
            for (int j = i + 1; j < nb; j++) {
                int mn = -1;
                for (int k = 0; k < ns; k++) {
                    int midX = stones.get(k)[0], midY = stones.get(k)[1];
                    if (dd[i][midX][midY] != -1 && dd[j][midX][midY] != -1) {
                        if (mn == -1 || mn > dd[i][midX][midY] + dd[j][midX][midY]) {
                            mn = dd[i][midX][midY] + dd[j][midX][midY];
                        }
                    }
                }
                dist[i][j] = mn;
                dist[j][i] = mn;
            }
        }

        // 无法达成的情形
        for (int i = 0; i < nb; i++) {
            if (dist[i][nb] == -1 || dist[i][nb + 1] == -1) {
                return -1;
            }
        }

        // dp 数组， -1 代表没有遍历到
        int[][] dp = new int[1 << nb][nb];
        for (int i = 0; i < 1 << nb; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < nb; i++) {
            dp[1 << i][i] = dist[i][nb];
        }

        // 由于更新的状态都比未更新的大，所以直接从小到大遍历即可
        for (int mask = 1; mask < (1 << nb); mask++) {
            for (int i = 0; i < nb; i++) {
                // 当前 dp 是合法的
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < nb; j++) {
                        // j 不在 mask 里
                        if ((mask & (1 << j)) == 0) {
                            int next = mask | (1 << j);
                            if (dp[next][j] == -1 || dp[next][j] > dp[mask][i] + dist[i][j]) {
                                dp[next][j] = dp[mask][i] + dist[i][j];
                            }
                        }
                    }
                }
            }
        }
        int ret = -1;
        int finalMask = (1 << nb) - 1;
        for (int i = 0; i < nb; i++) {
            if (ret == -1 || ret > dp[finalMask][i] + dist[i][nb + 1]) {
                ret = dp[finalMask][i] + dist[i][nb + 1];
            }
        }
        return ret;
    }

    int[][] bfs(int x,int y,String[] maze){
        int[][] ret = new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(ret[i],-1);
        }
        ret[x][y] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int curx = now[0],cury = now[1];
            for(int k=0;k<4;k++){
                int nx = curx+direction[k][0],ny = cury+direction[k][1];
                if(in(nx,ny)&&maze[nx].charAt(ny)!='#'&&ret[nx][ny]==-1){
                    ret[nx][ny] = ret[curx][cury]+1;
                    queue.add(new int[]{nx,ny});
                }
            }
        }
        return ret;
    }

    boolean in(int x,int y){
        return x<n&&x>=0&&y<m&&y>=0;
    }

}
