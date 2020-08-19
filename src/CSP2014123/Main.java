package CSP2014123;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static boolean[] isCancel = new boolean[5005];
    static Stock[] buy = new Stock[5005];
    static Stock[] sell = new Stock[5005];
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int cnt = 0;
        int buynum = 0, sellnum = 0;
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            //System.out.println(s);
            if(s.trim().length()==0){
                break;
            }
            if(s.contains("buy")){
                buy[buynum++] = new Stock(++cnt,Double.parseDouble(s.split(" ")[1]),Long.parseLong(s.split(" ")[2]));
            }
            else if(s.contains("sell")){
                sell[sellnum++] = new Stock(++cnt,Double.parseDouble(s.split(" ")[1]),Long.parseLong(s.split(" ")[2]));
            }else if(s.contains("cancel")){
                int cur = Integer.parseInt(s.split(" ")[1]);
                isCancel[cur] = true;
                cnt++;
            }
        }
        Arrays.sort(buy,0,buynum);
        Arrays.sort(sell,0,sellnum);
        int indexBuy = buynum-1;
        double resultprice = 0.0;
        long resultnum = 0;
        long tempNumBuy = 0;
        while(indexBuy>=0){
            //System.out.println(indexBuy);
            if(!isCancel[buy[indexBuy].id]){
                double tempprice = buy[indexBuy].price;
                tempNumBuy += buy[indexBuy].num;
                long tempNumSell = 0;
                for(int i=0;i<sellnum;i++){
                    if(!isCancel[sell[i].id]&&sell[i].price<=tempprice){
                        tempNumSell+=sell[i].num;
                    }
                }
                long tempResult = Math.min(tempNumBuy,tempNumSell);
                if(tempResult>resultnum) {
                    resultnum = tempResult;
                    resultprice = tempprice;
                }
            }
            indexBuy--;
        }
        System.out.printf("%5.2f ",resultprice);
        System.out.println(resultnum);
    }
}

class Stock implements Comparable<Stock>{
    int id;
    double price;
    long num;
    public Stock(int id,double price,long num){
        this.id = id;
        this.price = price;
        this.num = num;
    }

    @Override
    public int compareTo(Stock o) {
        return Double.compare(price, o.price);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
