package SolveP3.CSP201412;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int buycount = 0,soldcount =0,index = 1;
        BuyStock[] buyStocks = new BuyStock[5005];
        SoldStock[] soldStocks = new SoldStock[5005];
        boolean[] isCanceled = new boolean[5005];
        Arrays.fill(isCanceled,false);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.trim().length()==0) break;
            if(line.contains("cancel")){
                String[] parts = line.trim().split(" ");
                int cal = Integer.parseInt(parts[1]);
                isCanceled[cal] = true;
            }else if(line.contains("buy")){
                String[] parts = line.trim().split(" ");
                buyStocks[buycount++] = new BuyStock(index,Double.parseDouble(parts[1]),Integer.parseInt(parts[2]));
            }else if(line.contains("sell")){
                String[] parts = line.trim().split(" ");
                soldStocks[soldcount++] = new SoldStock(index,Double.parseDouble(parts[1]),Integer.parseInt(parts[2]));
            }
            index++;
        }
        Arrays.sort(buyStocks,0,buycount);
        Arrays.sort(soldStocks,0,soldcount);
//        for(int i=0;i<buycount;i++){
//            System.out.println(buyStocks[i]);
//        }
//        for(int i=0;i<soldcount;i++){
//            System.out.println(soldStocks[i]);
//        }
        long ans = 0;
        long buyAm = 0,sellAm = 0;
        double sellPrice = 0.0;
        for(int i=0;i<buycount;i++){
            if(isCanceled[buyStocks[i].index]){
                continue;
            }
            sellAm = 0;
            double val = buyStocks[i].val;
            buyAm += buyStocks[i].amount;
            for(int j=0;j<soldcount;j++){
                if(isCanceled[soldStocks[j].index]){
                    continue;
                }
                if(soldStocks[j].val>val){
                    break;
                }
                sellAm += soldStocks[j].amount;
            }
            long cur = Math.min(sellAm,buyAm);
            if(cur>ans){
                ans = cur;
                sellPrice = val;
            }
        }
        System.out.printf("%.2f ",sellPrice);
        System.out.print(ans);
    }

    static class BuyStock implements Comparable<BuyStock>{
        int index;  //表示这是操作几
        double val; //价格
        int amount; //成交量
        BuyStock(int index,double val,int amount){
            this.index = index;
            this.val = val;
            this.amount = amount;
        }

        @Override
        public int compareTo(BuyStock o) {  //把购买价格按照从高到低的顺序排序
            return -Double.compare(val,o.val);
        }

        @Override
        public String toString() {
            return "BuyStock{" +
                    "index=" + index +
                    ", val=" + val +
                    ", amount=" + amount +
                    '}';
        }
    }

    static class SoldStock implements Comparable<SoldStock>{
        int index;
        double val;
        int amount;
        SoldStock(int index,double val,int amount){
            this.index = index;
            this.val = val;
            this.amount = amount;
        }

        @Override
        public int compareTo(SoldStock o) {
            return Double.compare(val,o.val);
        }

        @Override
        public String toString() {
            return "SoldStock{" +
                    "index=" + index +
                    ", val=" + val +
                    ", amount=" + amount +
                    '}';
        }
    }
}

