package CSP2017124.Reference;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Dij.Node[][] martix = new Dij.Node[n][n];
        for(int i= 0;i<m;i++) {
            int t = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            martix[a-1][b-1] = new Dij.Node(c, t==0?true:false);
            martix[b-1][a-1] = new Dij.Node(c, t==0?true:false);
        }
        Dij dij = new Dij(martix, n);
        dij.dij(0);
        dij.output();
    }
}

/**
 * 核心算法实现类
 * @author colin
 */

class Dij {
    private int vertexNum;//顶点数量
    private Node[][] martix;//邻接矩阵
    private boolean[] U;//候选集合点，还没有找到最短路径的点
    private Map<Integer,Path> dist = new HashMap<>();//保存最短路径key代表终点 value代表当前的最短路径
    private int currentMidVertex;//当前的中间点,也就是每次产生的最短路径的那个点，该点之前不再S集合中
    private int startVertex = 0;

    public Dij(Node[][] martix,int vertexNum) {
        this.martix = martix;
        this.vertexNum = vertexNum;
        U = new boolean[vertexNum];
    }

    private void init(int startVertex) {
        //初始化U集合和dist集合
        for(int i = 0; i<vertexNum; i++) {
            U[i] = true;
            if(i!=startVertex) {
                if(martix[startVertex][i]!=null) {
                    Path path = new Path();
                    if(!martix[startVertex][i].isWideRoad) {
                        path.totalDistance = martix[startVertex][i].distance * martix[startVertex][i].distance;
                        path.lastStagePath.distance = path.totalDistance;
                        path.lastStagePath.isWideRoad = false;
                    } else {
                        path.totalDistance = martix[startVertex][i].distance;
                        path.lastStagePath.distance = martix[startVertex][i].distance;
                        path.lastStagePath.isWideRoad = true;
                    }
                    dist.put(i, path);
                } else {
                    Path path = new Path();
                    path.totalDistance = Long.MAX_VALUE;
                    dist.put(i, path);
                }
            }
        }
        U[startVertex] = false;
        //初始化中间点
        this.currentMidVertex = startVertex;
    }

    public void dij(int startVertex) {//从哪个顶点出发
        this.startVertex = startVertex;
        init(startVertex);
        //顶点个数-1次循环，每次从U集合中取出一个点
        for(int i = 1;i<vertexNum;i++) {//循环顶点个数-1那么多次，每次加入一个顶点到S集合中
            currentMidVertex = shorestPath();//找到dist中当前最短路径的终点是哪一个
            if(currentMidVertex==Long.MAX_VALUE) {//如果出现v1-v2没有路径，那么从U集合中能拿出来的点都拿出来了
                continue;
            }
            U[currentMidVertex] = false;//从候选集合中拿出去
            //更新dist，使得其他路径经过currentMidVertex，如果路径值更小那么更新
            Set<Entry<Integer,Path>> set = dist.entrySet();
            for (Entry<Integer, Path> entry : set) {
                if(entry.getKey()!=currentMidVertex&&U[entry.getKey()]) {
                    Path first = dist.get(currentMidVertex);
                    //新的路径就等于比如：当前的最短路径的终点是v3，若起点是v1，终点是v6，那么新的路径就是要找到是否可以经过v3从v1到v6，而只需要检测是否v3可以达到v6，如果可以那么新的路径就为v1->XXX->v3->v6
                    Node second = path(currentMidVertex,entry.getKey());
                    Path wait2update = entry.getValue();
                    //第二段没路可走
                    if(second==null) {
                        continue;
                    }
                    //如果后一段是小路
                    long result = Long.MAX_VALUE;
                    if(!second.isWideRoad) {
                        //如果前一段路也是小路
                        if(!first.lastStagePath.isWideRoad) {
                            long a = first.lastStagePath.distance;
                            //新构成的路径长度
                            result = (int) (first.totalDistance +2* Math.sqrt(a)*second.distance+ second.distance * second.distance);
                            if(result < wait2update.totalDistance) {
                                wait2update.totalDistance = result;
                                wait2update.lastStagePath.distance = (int) (a+2* Math.sqrt(a)*second.distance+ second.distance * second.distance);
                                wait2update.lastStagePath.isWideRoad = false;
                            }
                            //前一段路是大路
                        } else {
                            result = first.totalDistance+second.distance * second.distance;
                            if(result < wait2update.totalDistance) {
                                wait2update.totalDistance = result;
                                wait2update.lastStagePath.distance = second.distance * second.distance;
                                wait2update.lastStagePath.isWideRoad = false;
                            }
                        }
                        //后一段路是大路
                    } else {
                        result  = first.totalDistance + second.distance;
                        if(result < wait2update.totalDistance) {
                            wait2update.totalDistance = result;
                            wait2update.lastStagePath.distance = second.distance;
                            wait2update.lastStagePath.isWideRoad = true;
                        }
                    }
                }
            }
        }
    }

    public void output() {
        System.out.println(dist.get(vertexNum-1).totalDistance);
    }

    private Node path(int start, Integer destination) {//寻找一个路径起点是start，终点是destination
        if(martix[start][destination]!=null) {
            return martix[start][destination];
        }
        return null;
    }
    //遍历dist返回当前的最短路径的终点是哪个
    public int shorestPath() {
        Set<Entry<Integer,Path>> set = dist.entrySet();
        int minKey = Integer.MAX_VALUE;
        long minPath = Long.MAX_VALUE;
        for (Entry<Integer, Path> entry : set) {
            if(U[entry.getKey()]) {
                int key = entry.getKey();
                long value = entry.getValue().totalDistance;
                if(value < minPath) {
                    minPath = value;
                    minKey = key;
                }
            }
        }
        return minKey;
    }
    /**
     *邻接矩阵元素数据结构
     */
    public static class Node{
        public long distance = Long.MAX_VALUE;//
        public boolean isWideRoad = true;
        public Node(int distance,boolean isWideRoad) {
            this.distance = distance;
            this.isWideRoad = isWideRoad;
        }
        public Node() {
        }
    }
    /**
     *描述到某个终点的最短距离
     *需要不断更新
     */
    public static class Path{
        public long totalDistance = Long.MAX_VALUE;//保存距离的总和
        public Node lastStagePath = new Node();//保存最新阶段的路径，需要区分是大路还是小路
    }
}

