package SolveP3.CSP201512;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static char[][] board;
    static int n,m,q;
    static final int[][] direction = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) {
        m = scanner.nextInt(); n = scanner.nextInt(); q = scanner.nextInt();
        board = new char[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
        while(q-->0){
            int type = scanner.nextInt();
            if(type==0){//画线段
                int x1 = scanner.nextInt(),y1 = scanner.nextInt(), x2 = scanner.nextInt(), y2 = scanner.nextInt();
                if(x1==x2){
                    //上下方向
                    y1 = n-1-y1; y2 = n-1-y2;
                    if(y1>y2){
                        int temp = y2;
                        y2 = y1;
                        y1 = temp;  //保证y1更小
                    }
                    for(int i=y1;i<=y2;i++){
                        if(board[i][x1]=='-'){
                            board[i][x1] = '+';
                        }
                        else{
                            board[i][x1] = '|';
                        }
                    }
                }
                else{   //左右方向
                    int y = n-1-y1;
                    //System.out.println(y);
                    if(x1>x2){
                        int temp = x2;
                        x2 = x1;
                        x1 = temp;
                    }
                    for(int i=x1;i<=x2;i++){
                        if(board[y][i]=='|'){
                            board[y][i]='+';
                        }else{
                            board[y][i] = '-';
                        }
                    }
                }
            }else if(type==1){  //填充
                int y = scanner.nextInt(), x = scanner.nextInt();   //y为横坐标，x为纵坐标
                char c = scanner.next().trim().charAt(0);
                //System.out.println(c);
                x = n-1-x;
                boolean[][] vis = new boolean[n][m];
                Queue<int[]> queue = new LinkedList<>();
                vis[x][y] = true;
                queue.add(new int[]{x,y});
                while (!queue.isEmpty()){
                    int[] cur = queue.poll();
                    int curx = cur[0],cury = cur[1];
                    board[curx][cury] = c;
                    for(int i=0;i<4;i++){
                        int newx = curx+direction[i][0],newy = cury+direction[i][1];
                        if(!in(newx,newy)) continue;
                        if(vis[newx][newy]) continue;
                        if(board[newx][newy]=='|'||board[newx][newy]=='-'||board[newx][newy]=='+'){
                            continue;
                        }
                        vis[newx][newy] = true;
                        queue.add(new int[]{newx,newy});
                    }
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    static boolean in(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
}
