package CSP2016042;

import java.util.Scanner;

public class Main {
    static int[][] board = new int[15][10];
    static int[][] into = new int[4][4];
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<15;i++){
            for(int j=0;j<10;j++){
                board[i][j] = scanner.nextInt();
            }
        }
        int left = 4, right = -1;   //找到最左边的行,最右边的行
        int low = -1, high = 4;    //最下方的列
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                into[i][j] = scanner.nextInt();
                if(into[i][j]!=0){
                    left = Math.min(left,j);
                    right = Math.max(right,j);
                    low = Math.max(low,i);
                    high = Math.min(high,i);
                }
            }
        }
        int fit = scanner.nextInt();
        int cur = 0;
        while(cur<15+high-low&&fitin(left,right,low,high,fit,cur)){
            cur++;
        }
        cur = cur-1;
        for(int i=cur;i<cur+low-high+1;i++){
            for(int j=fit;j<fit+right-left+1;j++){
                if(into[i-cur+high][j-fit+left]==1){
                    board[i][j]=1;
                }
            }
        }
        for(int i=0;i<15;i++){
            for(int j=0;j<10;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    static boolean fitin(int left,int right,int low,int high,int fit,int cur){
        for(int i = cur;i<cur+low-high+1;i++){
            for(int j=fit;j<fit+right-left+1;j++){
                if(into[i-cur+high][j-fit+left]==1&&board[i][j]==1){
                    return false;
                }
            }
        }
        return true;
    }
}
