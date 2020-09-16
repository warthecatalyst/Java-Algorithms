package PAT.Problem1002;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final double eps = 1e-6;
    public static void main(String[] args) {
//        double a = 2.4+0.6;
//        System.out.println(Math.abs(a-(int)a)<eps);
        Scanner scanner = new Scanner(System.in);
        double[] coefficients = new double[1010];
        //boolean[] hasNum = new boolean[1010];
        int k1 = scanner.nextInt();
        for(int i=0;i<k1;i++){
            int num = scanner.nextInt();
            //hasNum[num] = true;
            double cof = scanner.nextDouble();
            coefficients[num] += cof;
        }
        int k2 = scanner.nextInt();
        for(int i=0;i<k2;i++){
            int num = scanner.nextInt();
            //hasNum[num] = true;
            double cof = scanner.nextDouble();
            //System.out.println("num = "+num+" cof = "+cof);
            coefficients[num]+=cof;
        }
        //System.out.println(coefficients[1]);
        List<Pair> ans = new ArrayList<>();
        for(int i=coefficients.length-1;i>=0;i--){
            if(coefficients[i]!=0){
                ans.add(new Pair(i,coefficients[i]));
            }
        }
        System.out.print(ans.size());

        for(Pair pair:ans){
            System.out.print(" "+pair.num);
            int cof = (int)(pair.cof+0.5);
            //System.out.println(" "+(pair.cof-cof));
//            if(Math.abs(pair.cof-cof)<=eps){
////                int cof = (int) pair.cof;
//                System.out.print(" "+cof);
//            }else {
//                System.out.printf(" %.1f", pair.cof);
//            }
            System.out.printf(" %.1f",pair.cof);
        }
    }
    static class Pair{
        int num;
        double cof;
        Pair(int num,double cof){
            this.num = num;
            this.cof = cof;
        }
    }
}
