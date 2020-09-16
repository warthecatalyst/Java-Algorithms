package SolveP4.CSP2019094.Modified;

import java.util.*;

public class Main {
    static Queue<Stock> queue = new PriorityQueue<>();
    static int n,m;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();n = scanner.nextInt();
        for(int i=0;i<n;i++){
            int id = scanner.nextInt(),sc = scanner.nextInt();
            for(int j=0;j<m;j++){
                queue.add(new Stock(j,id,sc));
            }
        }
        int opask = scanner.nextInt();
        while (opask-->0){
            int type = scanner.nextInt();
            if(type == 3){
                int K = scanner.nextInt();
                int[] maxCount = new int[m];
                for(int j=0;j<m;j++){
                    maxCount[j] = scanner.nextInt();
                }
                int picknum = 0;
                List<Integer>[] lists = new ArrayList[m];
                for(int j=0;j<lists.length;j++){
                    lists[j] = new ArrayList<>();
                }
                boolean[] isfull = new boolean[m];
                Set<Stock> set = new HashSet<>();
                while (!queue.isEmpty()&&picknum<K){
                    Stock cur = queue.poll();
                    set.add(cur);
                    int ctype = cur.type;
                    if(lists[ctype].size()==maxCount[ctype]){
                        isfull[ctype] = true;
                        boolean flag = true;
                        for(int kk=0;kk<m;kk++){
                            if(!isfull[kk]){
                                flag = false;
                                break;
                            }
                        }
                        if (flag){
                            break;
                        }
                        continue;
                    }
                    lists[ctype].add(cur.num);
                    picknum++;
                }
                queue.addAll(set);
                for(int j=0;j<m;j++){
                    Collections.sort(lists[j]);
                }
                for (List<Integer> list : lists) {
                    if (list.isEmpty()) {
                        System.out.println(-1);
                        continue;
                    }
                    for (int v : list) {
                        System.out.print(v + " ");
                    }
                    System.out.println();
                }
            }else if(type==2){
                int ty = scanner.nextInt(), id = scanner.nextInt();
                Stock stock = new Stock(ty,id,0);
                queue.remove(stock);
            }else if(type==1){
                int ty = scanner.nextInt(), num = scanner.nextInt(), sc = scanner.nextInt();
                queue.add(new Stock(ty,num,sc));
            }
        }
    }

    static class Stock implements Comparable<Stock>{
        int type,num,score;
        Stock(int type,int num,int score){
            this.type = type;
            this.score = score;
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Stock)) return false;
            Stock stock = (Stock) o;
            return type == stock.type &&
                    num == stock.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, num);
        }

        @Override
        public int compareTo(Stock o) {
            if(score==o.score){
                if(type==o.type){
                    return num-o.num;
                }
                return type-o.type;
            }
            return o.score-score;
        }

        @Override
        public String toString() {
            return "Stock{" +
                    "type=" + type +
                    ", num=" + num +
                    ", score=" + score +
                    '}';
        }
    }
}
