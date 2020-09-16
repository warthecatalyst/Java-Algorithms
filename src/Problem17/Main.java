package Problem17;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations(""));
    }
}

class Solution {
    List<String> list = new ArrayList<>();
    String[] map = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        dfs(0,"",digits);
        return list;
    }
    void dfs(int cur,String s,String digits){
        if(cur>=digits.length()){
            if(!s.equals("")){
                list.add(s);
            }
            return;
        }
        int curval = Integer.parseInt(String.valueOf(digits.charAt(cur)));
        String s1 = map[curval];
        for(int i=0;i<s1.length();i++){
            dfs(cur+1,s+s1.charAt(i),digits);
        }
    }
}