package CSP2014122;

import java.util.Scanner;

public class Main {
    static final int[][] direction = {{0,1},{1,-1},{1,0},{-1,1}};
    static int n;
    static Scanner scanner;
    public static void main(String[] args){
        scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[][] map = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j] = scanner.nextInt();
            }
        }
        int i=0,j=0,k=0;
        while(i<n&&j<n) {
            System.out.print(map[i][j] + " ");
            if(i==n-1&&j==n-1){
                break;
            }
            //System.out.println(" k = " + k+" i = "+i+" j = "+j);
            i+=direction[k][0];
            j+=direction[k][1];
            if(i>=n||j>=n){
                i-=direction[k][0];
                j-=direction[k][1];
                if(k==2){
                    i+=direction[0][0];
                    j+=direction[0][1];
                    k = 3;
                    continue;
                }
                else if(k==0){
                    i+=direction[2][0];
                    j+=direction[2][1];
                    k = 1;
                    continue;
                }
            }
            if(k==0){
                k = 1;
            }
            else if(k==1&&(i==n-1||j==0)){
                k = 2;
            }
            else if(k==2){
                k = 3;
            }
            else if(k==3&&(i==0||j==n-1)){
                k = 0;
            }
        }
    }
}

