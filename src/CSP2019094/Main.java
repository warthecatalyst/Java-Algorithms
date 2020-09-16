package CSP2019094;


import java.util.*;

public class Main {
    static final int INF = 0x7f7f7f7f;
    static int m,n,opnum;
    static Scanner scanner = new Scanner(System.in);
    static Set<Stock> set = new HashSet<>();    //存储全部的商品
    //static Set<Pair> isDeleted = new HashSet<>();
    public static void main(String[] args) {
        m = scanner.nextInt(); n = scanner.nextInt();
        for(int i=0;i<n;i++){
            int commodity = scanner.nextInt();  //所有m类商品编号为commidity的得分
            int score = scanner.nextInt();
            for(int j=0;j<m;j++){
                Stock temp = new Stock(j,commodity,score);
                set.add(temp);
            }
        }
        opnum = scanner.nextInt();
        //scanner.nextLine();
        for(int i=0;i<opnum;i++){
            int operation = scanner.nextInt();
            if(operation==1){
                int type = scanner.nextInt(), commodity = scanner.nextInt(),score = scanner.nextInt();
                Stock temp = new Stock(type,commodity,score);
                set.add(temp);
                //System.out.println(set);
            }else if(operation==2){
                int type = scanner.nextInt(), commodity = scanner.nextInt();
                Stock stock = new Stock(type,commodity,0);
                //System.out.println("Before Delete"+set);
                set.remove(stock);        //删不掉？
                //System.out.println("After Delete"+set);
                //isDeleted.add(new Pair(type,commodity));
            }else if(operation==3){
                int K = scanner.nextInt();
                int[] maxCount = new int[m];
                for(int j=0;j<m;j++){
                    maxCount[j] = scanner.nextInt();
                }
                int pickCount = 0;
                List<Stock> list = new ArrayList<>(set);
                Collections.sort(list);
                boolean[] isfull = new boolean[m];
                List<Integer>[] lists = new ArrayList[m];
                for(int j=0;j<m;j++){
                    lists[j] = new ArrayList<>();
                }
                for(Stock stock:list){
                    int ctype = stock.type;
                    if(lists[ctype].size()==maxCount[ctype]){
                        //System.out.println(ctype);
                        isfull[ctype] = true;
                        boolean flag = true;
                        for(int j=0;j<lists.length;j++){
                            if(!isfull[j]){
                                flag = false;
                                break;
                            }
                        }
                        if(flag){
                            break;
                        }
                        continue;
                    }
                    pickCount++;
                    lists[stock.type].add(stock.commodity);
                    if(pickCount==K){
                        break;
                    }
                }
                for(int j=0;j<m;j++){
                    Collections.sort(lists[j]);
                }
                for(int j=0;j<m;j++){
                    if(lists[j].size()==0){
                        System.out.println(-1);
                        continue;
                    }
                    for(int val:lists[j]){
                        System.out.print(val+" ");
                    }
                    System.out.println();
                }
            }
        }
    }

    static class Stock implements Comparable<Stock>{
        int type;
        int commodity;
        int score;

        Stock(int type,int commodity,int score){
            this.type = type;
            this.score = score;
            this.commodity = commodity;
        }

        @Override
        public int compareTo(Stock o) {
            if(score==o.score){//分数相同按照type和commodity进行排序
                if(type==o.type){
                    return commodity-o.commodity;
                }
                return type-o.type;
            }   //分数越大越前
            return o.score-score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Stock)) return false;
            Stock stock = (Stock) o;
            return type == stock.type &&
                    commodity == stock.commodity;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, commodity);
        }

        @Override
        public String toString() {
            return "Stock{" +
                    "type=" + type +
                    ", commodity=" + commodity +
                    ", score=" + score +
                    '}';
        }
    }
}
