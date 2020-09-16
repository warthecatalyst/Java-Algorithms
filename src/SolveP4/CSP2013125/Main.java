package SolveP4.CSP2013125;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static final int[][] direction = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};    //下上右左
    static int R,C;
    static char[][] board;
    static int Sx,Sy,Tx,Ty;
    static boolean[][] vis; //从起点能否到达该点
    public static void main(String[] args){
        R = scanner.nextInt();
        C = scanner.nextInt();
        scanner.nextLine();
        board = new char[R][C];
        vis = new boolean[R][C];
        for(int i=0;i<R;i++){
            String line = scanner.nextLine().trim();
            for(int j=0;j<C;j++){
                board[i][j] = line.charAt(j);
                if(board[i][j]=='S'){
                    Sx = i;
                    Sy = j;
                }
                if(board[i][j]=='T'){
                    Tx = i;
                    Ty = j;
                }
            }
        }
        BFS1();
        if(!vis[Tx][Ty]){
            System.out.println("I'm stuck!");
            return;
        }
        int ans = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(vis[i][j]&&BFS2(i,j)){
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
    static void BFS1(){
        Queue<int[]> queue = new LinkedList<>();
        vis[Sx][Sy] = true;
        queue.add(new int[]{Sx,Sy});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curx = cur[0],cury = cur[1];
            for(int i=0;i<4;i++){
                if(board[curx][cury]=='-'&&(i==0||i==1)) continue;
                if(board[curx][cury]=='|'&&(i==2||i==3)) continue;
                if(board[curx][cury]=='.'&&(i!=0)) continue;
                int newx = curx+direction[i][0],newy = cury+direction[i][1];
                if(!in(newx,newy)) continue;
                if(vis[newx][newy]||board[newx][newy]=='#') continue;
                vis[newx][newy] = true;
                queue.add(new int[]{newx,newy});
            }
        }
    }

    static boolean BFS2(int startx,int starty){
        boolean[][] vis1 = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        vis1[startx][starty] = true;
        queue.add(new int[]{startx,starty});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int curx = cur[0], cury = cur[1];
            if(curx==Tx&&cury==Ty){
                return false;
            }
            for(int i=0;i<4;i++){
                if(board[curx][cury]=='-'&&(i==0||i==1)) continue;
                if(board[curx][cury]=='|'&&(i==2||i==3)) continue;
                if(board[curx][cury]=='.'&&(i!=0)) continue;
                int newx = curx+direction[i][0],newy = cury+direction[i][1];
                if(!in(newx,newy)) continue;
                if(vis1[newx][newy]||board[newx][newy]=='#') continue;
                vis1[newx][newy] = true;
                queue.add(new int[]{newx,newy});
            }
        }
        return true;
    }
    static boolean in(int x,int y){
        return x>=0&&x<R&&y>=0&&y<C;
    }
}
