package CSP2016042.Referenc;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] s = new int[15][10];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                s[i][j] = sc.nextInt();
            }
        }
        int[][] t = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                t[i][j] = sc.nextInt();
            }
        }
        int x = sc.nextInt();
        x--;
        // get height difference of each column
        int minValue = 16;
        for (int i = 0; i < 4; i++) {
            // shangjie
            int sj = -1;
            for (int j = 0; j < 4; j++) {
                if (t[j][i] == 1)
                    sj = j;
            }
            // height difference
            if (sj == -1)
                continue;
            // xiajie
            int xj = 15;
            for (int j = 0; j < 11; j++) {
                if (s[14 - j][x + i] == 1) {
                    xj = 14 - j;
                }
            }
            if (xj - sj < minValue) {
                minValue = xj - sj;
            }
        }
        for (int i = 0; i < 4; i++) { // column
            for (int j = 0; j < 4; j++) { // row
                if (t[j][i] == 1) {
                    s[j + minValue - 1][i + x] = 1;
                }
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
