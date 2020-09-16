package CSP2015124;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Collections;

public class Main{
    static List<Integer> list[];	//存储数据
    static boolean vis[][];		//对vis[a][b] = 边a-b是否被访问过
    static Stack<Integer> path = new Stack<Integer>();	//最后的欧拉路径
    //并查集
    static int pre[];
    //查找+压缩
    static int find(int x){
        if(x == pre[x]) return x;
        else return pre[x] = find(pre[x]);
    }
    static void join(int x,int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            pre[x] = y;
        }
    }
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        vis = new boolean[n+1][n+1];
        pre = new int[n+1];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = i;
        }
        list = new ArrayList[n+1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<Integer>();
        }
        int a,b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            list[a].add(b);
            list[b].add(a);
            join(a,b);
        }
        boolean haveSolution = true;
        //have wrong?加上这一部分就运行错误
        int root = find(1);
        for (int i = 2; i <= n; i++) {
            if(root!=find(i)){
                haveSolution = false;
                break;
            }
        }
        int num = 0;
        for (int i = 1; i <= n; i++) {
            if(list[i].size()%2 != 0){
                num++;
            }
        }
        if(num!=0 && num!=2){
            haveSolution = false;
        }
        if(haveSolution){
            //排序，这样dfs出来的结果就是自然顺序
            for (int i = 0; i < list.length; i++) {
                Collections.sort(list[i]);
            }
            //从结点1开始查找
            dfs(1);
            //输出结果
            while(!path.isEmpty()){
                System.out.print(path.pop()+" ");
            }
        }else{
            //不存在该路
            System.out.println("-1");
        }
    }
    private static void dfs(int i) {
        for (int j = 0; j < list[i].size(); j++) {
            int next = list[i].get(j);
            if(!vis[i][next]){
                vis[i][next] = true;
                vis[next][i] = true;
                dfs(next);
            }
        }
        path.push(i);
    }
}
