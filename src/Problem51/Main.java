package Problem51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        List<List<String>> lists = solution.solveNQueens(8);
        for(int i=0;i<lists.size();i++){
            List<String> list = lists.get(i);
            System.out.println("解法"+(i+1)+":");
            for(String s:list){
                System.out.println(s);
            }
        }
    }
}

class Solution {
    int total;
    int[] queens;  //存放每一行摆放的位置
    boolean[] col;  //存放每一列是否有棋子
    boolean[] left; //存放从左下到右上的对角线有没有棋子
    boolean[] right;    //存放从右下到左上的对角线有没有棋子
    List<List<String>> ans;
    public List<List<String>> solveNQueens(int n) {
        total = n;
        queens = new int[n];
        col = new boolean[n];
        left = new boolean[2*n];
        right = new boolean[2*n];
        Arrays.fill(col,false);
        Arrays.fill(left,false);
        Arrays.fill(right,false);
        ans = new ArrayList<>();
        solve(0);
        return ans;
    }
    public void solve(int i){
        if(i>=total){
            List<String> cc = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            for(int j=0;j<total;j++){
                for(int k=0;k<total;k++){
                    if(queens[j]==k){
                        stringBuilder.append('Q');
                    }
                    else{
                        stringBuilder.append('.');
                    }
                }
                cc.add(new String(stringBuilder));
                stringBuilder = new StringBuilder();
            }
            ans.add(cc);
            return;
        }
        for(int j=0;j<total;j++){
            if(!col[j]&&!left[i+j]&&!right[total+i-j]){
                queens[i] = j;
                col[j] = true;
                left[i+j] = true;
                right[total+i-j] = true;
                solve(i+1);
                col[j] = false;
                left[i+j] = false;
                right[total+i-j] = false;
             }
        }
    }
}
