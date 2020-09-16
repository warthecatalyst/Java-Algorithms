package SolveP4.CSP2019094;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static Queue<Stock> queue = new PriorityQueue<>();
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader inBuff = new BufferedReader(new InputStreamReader(System.in));
        String line = inBuff.readLine().trim();
        String[] parts = line.split(" ");
        m = Integer.parseInt(parts[0]); n = Integer.parseInt(parts[1]);
        for(int i=1;i<=n;i++){
            line = inBuff.readLine().trim();
            int id = Integer.parseInt(line.split(" ")[0]), score = Integer.parseInt(line.split(" ")[1]);
            for(int j=0;j<m;j++){
                Stock stock = new Stock(j,id,score);
                queue.add(stock);
            }
        }
        line = inBuff.readLine().trim();
        int opask = Integer.parseInt(line);
        for(int i=0;i<opask;i++){
            line = inBuff.readLine().trim();
            parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);
            if(type==3){
                int K = Integer.parseInt(parts[1]);
                int[] maxCount = new int[m];
                for(int j=2;j<parts.length;j++){
                    maxCount[j-2] = Integer.parseInt(parts[j]);
                }
                //System.out.println(.toString(maxCount));
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
            }else if (type==2){
                int ty = Integer.parseInt(parts[1]), num = Integer.parseInt(parts[2]);
                Stock cur = new Stock(ty,num,0);
                queue.remove(cur);
                //System.out.println(queue);
            }else{
                int ty = Integer.parseInt(parts[1]), num = Integer.parseInt(parts[2]), pr = Integer.parseInt(parts[3]);
                queue.add(new Stock(ty,num,pr));
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
