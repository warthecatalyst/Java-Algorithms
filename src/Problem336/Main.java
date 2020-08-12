package Problem336;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String[] words = new String[]{"abcd","dcba","lls","s","sssll"};
        Solution solution = new Solution();
        System.out.println(solution.palindromePairs(words));
    }
}

class Solution{     //暴力算法，超时
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> lists = new ArrayList<>();
        for(int i=0;i<words.length;i++){
            for(int j=0;j<words.length;j++){
                if(j==i){
                    continue;
                }
                StringBuilder stringBuilder = new StringBuilder(words[i]);
                stringBuilder.append(words[j]);
                //System.out.println(stringBuilder);
                if(isPalindrome(stringBuilder.toString())){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    lists.add(list);
                }
            }
        }
        return lists;
    }
    boolean isPalindrome(String s){
        int low = 0,high = s.length()-1;
        while(low<high){
            if(s.charAt(low)==s.charAt(high)){
                low++;
                high--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
