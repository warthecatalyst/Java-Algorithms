package Problem79;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','D'},{'S','F','E','S'},{'A','D','E','E'}};
        Solution solution = new Solution();
        System.out.println(solution.exist(board,"ABCESEEEFS"));
    }
}

class Solution{
    final int[][] direction = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    int m,n;
    boolean isfound = false;
    Set<Pair> vis = new HashSet<>();
    public boolean exist(char[][] board, String word) {
        n = board.length; m = board[0].length;
        char c = word.charAt(0);
        List<int[]> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]==c){
                    list.add(new int[]{i,j});
                }
            }
        }
        for(int i=0;i<list.size();i++){
            DFS(board,list.get(i),word,0,list.get(i));  //找到所有的起始点
        }
        return isfound;
    }
    void DFS(char[][] board,int[] curPos,String word,int cur,int[] root){
        if(isfound){
            return;
        }
        int curx = curPos[0],cury = curPos[1];
        System.out.println("curx = "+curx+" cury = "+cury+" "+board[curx][cury]);
        vis.add(new Pair(root[0],root[1],curx,cury));
        if(cur==word.length()-1){
            isfound = true;
            return;
        }
        if(cur>=word.length()){
            return;
        }
        for(int i=0;i<4;i++){
            int newx = curx+direction[i][0],newy = cury+direction[i][1];
            if(!in(newx,newy))continue;
            if(board[newx][newy]!=word.charAt(cur+1)) continue;
            if(vis.contains(new Pair(root[0],root[1],newx,newy))) continue;
            DFS(board,new int[]{newx,newy},word,cur+1,root);
        }
    }
    boolean in(int x,int y){
        return x>=0&&x<n&&y>=0&y<m;
    }
    static class Pair{
        int rootx,rooty,x,y;
        Pair(int rootx,int rooty,int x,int y){
            this.rootx = rootx;
            this.rooty = rooty;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return rootx == pair.rootx &&
                    rooty == pair.rooty &&
                    x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rootx, rooty, x, y);
        }
    }
}