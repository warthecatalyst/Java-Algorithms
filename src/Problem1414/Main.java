package Problem1414;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println("ans = "+solution.findMinFibonacciNumbers(10));
    }
}

class Solution{
    int[] fibo;
    static int ans = 0;
    public int findMinFibonacciNumbers(int k) {
        //System.out.println("k="+k);
        if(k==0){
            return ans;
        }
        if(fibo==null){
            fibo = new int[k+1];
            fibo[0] = fibo[1] = 1;
            for(int i=2;i<k+1;i++){
                fibo[i] = fibo[i-1]+fibo[i-2];
            }
        }
        int i = 0;
        for(;i<=k;i++){
            if(fibo[i]>k){
                break;
            }
        }
        i = i-1;    //刚好小于等于k的最大的斐波那契数
        //System.out.println(fibo[i]);
        ans++;
        return findMinFibonacciNumbers(k-fibo[i]);
    }
}
