package CSP2014034;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
    }
}

class Solution{
    int n,m,k,r;
    Pair[] cur = new Pair[205];
    int[] c= new int[200];
    double[][] d = new double[200][200];

    public Solution(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();
        r = scanner.nextInt();
        for(int i=0;i<m+n;i++){
            cur[i] = new Pair(scanner.nextInt(),scanner.nextInt());
        }
        for(int i=0;i<m+n;i++){
            for(int j=i+1;j<m+n;j++){
                d[i][j] = d[j][i] = distance(cur[i],cur[j]);
            }
        }
        System.out.println(bfs());
    }

    int bfs(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0,0));
        Arrays.fill(c,k+1);   //初始化
        while(!queue.isEmpty()){
            Node now = queue.poll();
            int index = now.index,cnt1 = now.cnt1,cnt2 = now.cnt2;
            if(index == 1) return cnt2-1;
            for(int i=0;i<m+n;i++){
                if(i==n) cnt1++;
                if(cnt1>k) break;
                if(d[index][i]<=r&&cnt1<c[i]){
                    c[i] = cnt1;
                    queue.add(new Node(i,cnt1,cnt2+1));
                }
            }
        }
        return -1;
    }

    public double distance(Pair a,Pair b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    }
}

class Pair{
    long x,y;
     public Pair(long x,long y){
         this.x = x;
         this.y = y;
     }

}

class Node{
    int index,cnt1,cnt2;    //供搜索用，分别代表编号、经过的新增路由器数，经过的总路由器数
    public Node(int index,int cnt1,int cnt2){
        this.index = index;
        this.cnt1 = cnt1;
        this.cnt2 = cnt2;
    }
}