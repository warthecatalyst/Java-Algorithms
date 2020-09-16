package CSP2015125;

import java.util.Scanner;

public class Main {
    static int m;
    static boolean[][] matrix;
    static boolean[] vector;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        matrix = new boolean[m][m];
        vector = new boolean[m];
        for(int i=0;i<m;i++){
            int sm = scanner.nextInt();
            for(int j=m-1;j>0;j--){
                matrix[i][j] = (sm%10)==1;
            }
        }
        int sm = scanner.nextInt();
        for(int j=m-1;j>0;j--){
            vector[j] = (sm%10)==1;
        }
        int n = scanner.nextInt();
    }
    static void calculate(int k){
        boolean[][] copy = matrix.clone();
        if(k==0){
            for(int i=0;i<m;i++){
                if(vector[i]){
                    System.out.print(1);
                }
                else {
                    System.out.print(0);
                }
            }
            System.out.println();
            return;
        }
        while(k>1){
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){
                    boolean flag = copy[i][j]&&matrix[i][0];
                    for(int l=1;l<m;l++){
                        flag = ((!flag)&&(matrix[i][k]&&copy[i][j]))||((flag)&&!(matrix[i][k]&&copy[i][j]));
                    }
                    copy[i][j] = flag;
                }
            }
            k--;
        }
        for(int i=0;i<m;i++){
            boolean flag = vector[i];
            for(int l=1;l<m;l++){
                flag = ((!flag)&&(matrix[i][l]&&vector[l]))||((flag)&&!(matrix[i][l]&&vector[l]));
            }
            vector[i] = flag;
        }
    }
}
