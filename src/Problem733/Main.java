package Problem733;

public class Main {
    public static void main(String[] args){

    }
}

class Solution {
    static final int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
    boolean[][] visited;
    int value,n,m;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image==null||image.length==0||image[0]==null||image[0].length==0){
            return image;
        }
        n = image.length;
        m = image[0].length;
        visited = new boolean[n][m];
        value = image[sr][sc];
        dfs(image,sr,sc,newColor);
        return image;
    }
    void dfs(int[][] image,int r,int c,int newColor){
        if(visited[r][c]){
            return;
        }
        visited[r][c] = true;
        image[r][c] = newColor;
        for(int i=0;i<4;i++){
            int newr = r+direction[i][0];
            int newc = c+direction[i][1];
            if(in(newr,newc)&&image[newr][newc]==value){
                dfs(image,newr,newc,newColor);
            }
        }
    }
    boolean in(int r,int c){
        return r>=0&&r<n&&c>=0&&c<m;
    }
}
