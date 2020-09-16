package CSP2015122;

import java.awt.desktop.ScreenSleepEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static final int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};   //下、上、右、左
    static int[][] map;
    static int n,m;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();m = scanner.nextInt();
        map = new int[n][m];
        Set<int[]> set = new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j] = scanner.nextInt();
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //判断行和列
                int k = j+1,l = i+1;
                boolean flag = false;
                while(in(i,k)&&map[i][k]==map[i][j]){
                    k++;
                }
                if(k-j>=3){
                    flag = true;
                    for(int ccnt = j+1;ccnt<k;ccnt++){
                        set.add(new int[]{i,ccnt});
                    }
                }
                while(in(l,j)&&map[l][j]==map[i][j]){
                    l++;
                }
                if(l-i>=3){
                    flag = true;
                    for(int rcnt = i+1;rcnt<l;rcnt++){
                        set.add(new int[]{rcnt,j});
                    }
                }
                if(flag){
                    set.add(new int[]{i,j});
                }
            }
        }
        for (int[] cur : set) {
            int x = cur[0], y = cur[1];
            map[x][y] = 0;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    static boolean in(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
}
