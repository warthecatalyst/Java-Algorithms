package Problem657;

public class Main {
    public static void main(String[] args){

    }
}

class Solution {
    public boolean judgeCircle(String moves) {
        int left = 0,right = 0,up = 0,down = 0;
        for(int i=0;i<moves.length();i++){
            char cur = moves.charAt(i);
            if(cur=='R') right++;
            if(cur=='L') left++;
            if(cur=='U') up++;
            if(cur=='D') down++;
        }
        return left==right&&up==down;
    }
}
