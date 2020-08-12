package Problem130;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args){

    }
}

class Solution {
    static final int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
    int n,m;
    boolean[][] visited;
    public void solve(char[][] board) {
        if(board==null||board.length==0||board[0]==null||board[0].length==0){
            return;
        }
        n = board.length;
        m = board[0].length;
        visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            if (board[i][0]=='O'){
                bfs(board,i,0);
            }
        }
        for(int j=1;j<m;j++){
            if(board[0][j]=='O'){
                bfs(board,0,j);
            }
        }
        for(int i=0;i<n;i++){
            if(board[i][m-1]=='O'){
                bfs(board,i,m-1);
            }
        }
        for(int j=1;j<m-1;j++){
            if(board[n-1][j]=='O'){
                bfs(board,n-1,j);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }
    public void bfs(char[][] board,int x,int y){
        if(visited[x][y]){
            return;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int nx = cur[0],ny = cur[1];
            for(int i=0;i<4;i++){
                int newx = nx+direction[i][0],newy = ny+direction[i][1];
                if(in(newx,newy)&&board[newx][newy]=='O'&&!visited[newx][newy]){
                    visited[newx][newy] = true;
                    queue.add(new int[]{newx,newy});
                }
            }
        }
    }
    boolean in(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
}
