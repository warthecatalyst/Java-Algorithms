package Problem786;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = solution.kthSmallestPrimeFraction(new int[]{1,2,3,5},3);
        System.out.println(ans[0]+" "+ans[1]);
    }
}

class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double low = 0,high = 1;
        int[] ans = new int[]{0,1};
        while(high-low>1e-9){
            double mid = (high+low)/2.0;
            int[] res = under(mid,A);
            if(res[0]<K){
                low = mid;
            }
            else{
                ans[0] = res[1];
                ans[1] = res[2];
                high = mid;
            }
        }
        return ans;
    }

    int[] under(double x,int[] primes){
        int numer = 0, denom = 1, count = 0, i = -1;
        for (int j = 1; j < primes.length; ++j) {
            // For each j, find the largest i so that primes[i] / primes[j] < x
            // It has to be at least as big as the previous i, so reuse it ("two pointer")
            while (primes[i+1] < primes[j] * x) ++i;

            // There are i+1 fractions: (primes[0], primes[j]),
            // (primes[1], primes[j]), ..., (primes[i], primes[j])
            count += i+1;
            if (i >= 0 && numer * primes[j] < denom * primes[i]) {
                numer = primes[i];
                denom = primes[j];
            }
        }
        return new int[]{count, numer, denom};
    }
}
