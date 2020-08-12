package EightQueens;

import javax.xml.transform.SourceLocator;

public class Main {
    public static void main(String[] args){
        Queen queen = new Queen();
    }
}

class Queen{
    static int cur = 1;
    int n;      //当前的棋盘大小
    int[] ans;  //用于存储数据
    int[] col;  //当某一列存在时，为1
    int[] leftup;   //左上至右下对角线
    int[] rightup;  //右上至左下对角线

    public Queen(){
        n = 8;
        ans = new int[8];
        col = new int[8];
        leftup = new int[8*2];
        rightup = new int[8*2];
        digui(0);
    }

    public Queen(int n){
        this.n = n;
        ans = new int[n];
        col = new int[n];
        leftup = new int[2*n];
        rightup = new int[2*n];
        digui(0);
    }

    public void digui(int row){
        if(row>=n){
            printans();
            return;
        }
        for(int j = 0;j<n;j++){
            if(col[j]!=1&&leftup[n+row-j]!=1&&rightup[row+j]!=1){
                ans[row] = j;
                col[j] = 1;
                leftup[n+row-j] = 1;
                rightup[row+j] = 1;
                digui(row+1);
                col[j] = 0;
                leftup[n+row-j] = 0;
                rightup[row+j] = 0;
            }
        }
    }

    public void printans(){
        System.out.println("Answer "+cur+++":");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(ans[i]==j){
                    System.out.print("Q");
                }
                else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
