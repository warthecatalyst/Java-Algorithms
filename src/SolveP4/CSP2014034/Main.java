package SolveP4.CSP2014034;

import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n,m,k;
    static long r;
    static Scanner scanner = new Scanner(System.in);
    static Vertex[] vertices;
    public static void main(String[] args) {
        n = scanner.nextInt(); m = scanner.nextInt(); k = scanner.nextInt(); r = scanner.nextLong();
        vertices = new Vertex[n+m];
        for(int i=0;i<n+m;i++){
            if(i>=n){
                vertices[i] = new Vertex(scanner.nextLong(),scanner.nextLong(),true,i);
            }
            else{
                vertices[i] = new Vertex(scanner.nextLong(),scanner.nextLong(),false,i);
            }
        }
//        for(int i=0;i<n+m;i++){
//            System.out.println(vertices[i]);
//        }
        boolean[][] vis = new boolean[n+m][n+m];
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertices[0]);
        while(!queue.isEmpty()){
            Vertex cur = queue.poll();
            int length = cur.length;    //当前的路径长度
            int cost = cur.cost;        //当前使用的添加的路由器
            int num = cur.num;
            //System.out.println("num = "+num+", length = "+length+", cost = "+cost);
            if(num==1){
                System.out.println(length-1);
                return;
            }
            for(int i=1;i<vertices.length;i++){
                if(vertices[i].distance(cur)<=r&&i!=num){
                    if(vis[i][num]){
                        continue;
                    }
                    if(!vertices[i].isAdd&&cost<=k){
                        queue.add(new Vertex(vertices[i].x,vertices[i].y,vertices[i].isAdd,vertices[i].num,
                        length+1,cost));
                    }
                    else if(vertices[i].isAdd&&cost+1<=k){
                        queue.add(new Vertex(vertices[i].x,vertices[i].y,vertices[i].isAdd,vertices[i].num,
                        length+1,cost+1));
                    }
                    else{
                        continue;
                    }
                    vis[i][num] = true;
                    vis[num][i] = true;
                }
            }
        }
    }

    static class Vertex{
        int num;
        boolean isAdd;
        long x,y;
        int length; //到达当前点已经走过的步数
        int cost;   //到达当前点所经过的额外的路由器
        Vertex(long x,long y,boolean isAdd,int num){
            this.x = x;
            this.y = y;
            this.isAdd = isAdd;
            this.num =num;
            length = 0;
            cost = 0;
        }
        Vertex(long x,long y,boolean isAdd,int num,int length,int cost){
            this.x = x;
            this.y = y;
            this.isAdd = isAdd;
            this.num = num;
            this.length = length;
            this.cost = cost;
        }
        double distance(Vertex o){
            long t1 = o.x-x;
            long t2 = o.y-y;
            return Math.sqrt(t1*t1+t2*t2);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "num=" + num +
                    ", isAdd=" + isAdd +
                    ", x=" + x +
                    ", y=" + y +
                    ", length=" + length +
                    ", cost=" + cost +
                    '}';
        }
    }
}
