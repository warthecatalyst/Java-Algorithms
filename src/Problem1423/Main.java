package Problem1423;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] cards = new int[n];
        for(int i=0;i<n;i++){
            cards[i] = scanner.nextInt();
        }
        Solution solution = new Solution();
        System.out.println(solution.maxScore(cards,scanner.nextInt()));
    }
}

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int[] pre = new int[cardPoints.length+1];
        pre[0] = 0;
        for(int i=1;i<pre.length;i++){
            pre[i] = pre[i-1]+cardPoints[i-1];
        }
        int ans = 0;
        for(int i=0;i<=k;i++){
            ans = Math.max(ans,pre[i]+pre[cardPoints.length]-pre[cardPoints.length+i-k]);
            //System.out.println("i = "+i+"ans = "+ans);
        }
        return ans;
    }
}