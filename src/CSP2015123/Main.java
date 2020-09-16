package CSP2015123;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int[][] direction = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};//上下左右
    static int m,n,q;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        char[][] draw = new char[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                draw[i][j] = '.';
            }
        }
        q = scanner.nextInt();
        for(int i=0;i<q;i++){
            int operator = scanner.nextInt();
            if(operator==0){    //画线操作
                int x1 = scanner.nextInt(),y1 = scanner.nextInt(),x2 = scanner.nextInt(),y2 = scanner.nextInt();
                int startx1 = n-1-y1,startx2 = n-1-y2;
                //System.out.println("startx1 = "+startx1+",startx2 = "+startx2+",y1 = "+x1+"y2 = "+x2);
                if(x1==x2){
                    if(startx1>startx2){
                        int temp = startx1;
                        startx1 = startx2;
                        startx2 = temp;
                    }
                    for(int j=startx1;j<=startx2;j++){
                        if(draw[j][x1]=='-'){
                            draw[j][x1] = '+';
                        }
                        else {
                            draw[j][x1] = '|';
                        }
                    }
                } else{   //y1 == y2
                    if(x1>x2){
                        //System.out.println("log");
                        int temp = x1;
                        x1 = x2;
                        x2 = temp;
                    }
                    for(int j=x1;j<=x2;j++){
                        if(draw[startx1][j]=='|'){
                            draw[startx1][j] = '+';
                        }
                        else{
                            //System.out.print("startx1 = "+startx1+",j = "+j);
                            draw[startx1][j] = '-';
                        }
                    }
                }
//                for(int kk=0;kk<n;kk++){
//                    for(int ll=0;ll<m;ll++){
//                        System.out.print(draw[kk][ll]);
//                    }
//                    System.out.println();
//                }
            }
            else{   //填充操作,BFS完成
                int y = scanner.nextInt(),x = scanner.nextInt();
                String fill = scanner.next();
                //System.out.println(fill);
                x = n-1-x;
                Queue<int[]> queue = new LinkedList<>();
                boolean[][] visited = new boolean[n][m];
                for(int kk=0;kk<n;kk++){
                    Arrays.fill(visited[kk],false);
                }
                queue.add(new int[]{x,y});
                visited[x][y] = true;
                while(!queue.isEmpty()){
                    int[] cur = queue.poll();
                    int curx = cur[0],cury = cur[1];
                    draw[curx][cury] = fill.charAt(0);
                    for(int j=0;j<4;j++){
                        int newx = curx+direction[j][0],newy = cury+direction[j][1];
                        if(!in(newx,newy)||visited[newx][newy]||draw[newx][newy]=='-'||draw[newx][newy]=='|'||draw[newx][newy]=='+'){
                            continue;
                        }
                        visited[newx][newy] =true;
                        queue.add(new int[]{newx,newy});
                    }
                }
//                for(int kk=0;kk<n;kk++){
//                    for(int ll=0;ll<m;ll++){
//                        System.out.print(draw[kk][ll]);
//                    }
//                    System.out.println();
//                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(draw[i][j]);
            }
            System.out.println();
        }
    }
    static boolean in(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
}
