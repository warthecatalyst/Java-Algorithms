package Problem151;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.reverseWords("a good  example"));
    }
}

class Solution {
    public String reverseWords(String s) {
        String[] s1 = s.trim().split(" +");
        StringBuilder stringBuilder = new StringBuilder(s1[s1.length-1]);
        for(int i=s1.length-2;i>=0;i--){
            //System.out.println(stringBuilder);
            stringBuilder.append(" ").append(s1[i]);
        }
        return stringBuilder.toString();
    }
}
