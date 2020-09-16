package CSP2018034;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int T;
    static int[][] board = new int[4][4];
    static boolean judge(int k){
        for(int i=1;i<=3;i++){
            if(board[i][1]==board[i][2]&&board[i][2]==board[i][3]&&board[i][3]==k) return true;
            if(board[1][i]==board[2][i]&&board[2][i]==board[3][i]&&board[3][i]==k) return true;
        }
        if(board[1][1]==board[2][2]&&board[2][2]==board[3][3]&&board[3][3]==k) return true;
        return board[3][1] == board[2][2] && board[2][2] == board[1][3] && board[1][3] == k;
    }
    static int dfs(int k){
        int le = 0,curmax = -0x7f,curmin = 0x7f;
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                if(board[i][j]==0){
                    le++;
                }
            }
        }
        if(k==1&&judge(2)) return -le-1;
        if(k==2&&judge(1)) return le+1;
        if(le==0) return 0;
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                if(board[i][j]==0){
                    board[i][j] = k;
                    if(k==1) curmax = Math.max(curmax,dfs(2));
                    if(k==2) curmin = Math.min(curmin,dfs(1));
                    board[i][j] = 0;   //不要忘记修改
                }
            }
        }
        if(k==1){
            return curmax;
        }
        else{
            return curmin;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        while(T-->0) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }
            int result = dfs(1);
            System.out.println(result);
        }
    }
}
