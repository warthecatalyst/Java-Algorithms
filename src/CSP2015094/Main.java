package CSP2015094;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
public class Main{
    static List<Integer> list[];
    static boolean visited[];
    static int DFN[];
    static int LOW[];
    static Stack<Integer> s ;
    static int index;
    static int ans = 0;
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        int a,b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            list[a].add(b);
        }
        s = new Stack<Integer>();
        visited = new boolean[n+1];
        DFN = new int[n+1];
        LOW = new int[n+1];
        index = 0;
        for (int i = 1; i <= n; i++) {
            if(!visited[i])
                tarjan(i);
        }
//		tarjan(1);
        System.out.println(ans);
    }
    private static void tarjan(int u) {
        visited[u] = true;
        DFN[u] = LOW[u] = ++index;
        s.push(u);
        int v;
        for (int i = 0; i < list[u].size(); i++) {
            v = list[u].get(i);
            if(!visited[v]){
                tarjan(v);
                LOW[u] = Math.min(LOW[u], LOW[v]);
            }else if(s.contains(v)){
                LOW[u] = Math.min(LOW[u],DFN[v]);
            }
        }
        if(DFN[u] == LOW[u]){
            int count = 0;
            do{
                v = s.pop();
                count++;
            }while(u!=v);
            if(count>0){
                ans += count*(count-1)/2;
            }
            //自己好好想想为什么！找了半天BUG=。=
//			if(count>1){
//				ans += count;
//			}
        }
    }
}
