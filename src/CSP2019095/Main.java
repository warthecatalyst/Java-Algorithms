package CSP2019095;

import jdk.dynalink.linker.LinkerServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int MAXN = (int) (5e4+5);
    static long[][] dp;   //以i为根节点选择j个子节点，其距离之和
    static boolean[] imp;
    static int N,M,K;
    static List<int[]>[] tree;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        N = scanner.nextInt(); M = scanner.nextInt(); K = scanner.nextInt();
        imp = new boolean[N+1];
        for(int i=0;i<M;i++){
            int x = scanner.nextInt();
            imp[x] = true;
        }
        tree = new ArrayList[N+1];
        for(int i=0;i< tree.length;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0;i<N-1;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            tree[from].add(new int[]{to,cost});
            tree[to].add(new int[]{from,cost});
        }
        dp = new long[N+1][K+1];
        for (long[] longs : dp) {
            Arrays.fill(longs, -1);
        }
        solve(1,0);
        System.out.println(dp[1][K]);
    }

    static void solve(int u,int fa){    //u为子节点,fa为父节点
        long[] cur = dp[u]; //当前要求解的数组
        cur[0] = 0;
        for(int[] pr:tree[u]){
            if(pr[0]!=fa){
                solve(pr[0],u);
                long[] sub = dp[pr[0]];
                for(int x = K;x>0;x--){
                    for(int y=x;y>=0;y--){
                        if(cur[y]!=-1&&sub[x-y]!=-1){
                            long t = cur[y]+sub[x-y]+(long)pr[1]*(x-y)*(K-x+y);
                            if(cur[x]==-1||cur[x]>t){
                                cur[x] = t;
                            }
                        }
                    }
                }
            }
        }
        if (imp[u]){
            for(int i=K;i>0;i--){
                if(cur[i]==-1||cur[i]>cur[i-1])
                    cur[i] = cur[i-1];
            }
        }
    }
}
