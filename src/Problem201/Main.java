package Problem201;

public class Main {
    public static void main(String[] args){

    }
}

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        while(m<n){
            n = n & (n-1);
        }
        return n;
    }
}
