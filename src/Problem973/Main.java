package Problem973;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){

    }
}

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length, m = points[0].length;
        Dist[] dist = new Dist[n];
        for(int i=0;i<n;i++){
            dist[i] = new Dist(points[i][0]*points[i][0]+points[i][1]*points[i][1],i);
        }
        Arrays.sort(dist);
        int[][] ans = new int[K][2];
        for(int i=0;i<K;i++){
            Dist cur = dist[i];
            ans[i][0] = points[cur.num][0];
            ans[i][1] = points[cur.num][1];
        }
        return ans;
    }

    static class Dist implements Comparable<Dist>{
        int dis,num;
        Dist(int dis,int num){
            this.dis = dis;
            this.num = num;
        }

        @Override
        public int compareTo(Dist o) {
            return this.dis-o.dis;
        }
    }
}
