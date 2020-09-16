package CSP2020061;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n,m;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        Pair[] pairs = new Pair[n];
        int[] counts = new int[n];
        for(int i=0;i<n;i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String z = scanner.next();
            pairs[i] = new Pair(x,y,z);
        }
loop:        for(int i=0;i<m;i++){
            int o0 = scanner.nextInt(),o1 = scanner.nextInt(),o2 = scanner.nextInt();
            for(int j=0;j<n;j++){
                counts[j] = (o0+pairs[j].x*o1+pairs[j].y*o2)*pairs[j].type;
            }
            boolean flag = counts[0]>0;
            for(int j=0;j<n;j++){
                if(counts[j]>0&&!flag||counts[j]<0&&flag){
                    System.out.println("No");
                    continue loop;
                }
            }
            System.out.println("Yes");
        }
    }

    static class Pair{
        int x,y;
        int type;
        Pair(int x,int y,String s){
            this.x = x;
            this.y = y;
            if(s.equals("A")){
                type = 1;
            }
            else{
                type = -1;
            }
        }
    }
}
