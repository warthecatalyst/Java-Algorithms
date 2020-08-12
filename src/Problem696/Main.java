package Problem696;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        String s = "001100111";
        System.out.println(solution.countBinarySubstrings(s));
    }
}

class Solution {
    public int countBinarySubstrings(String s) {
        List<Integer> list = new ArrayList<>();
        int ans = 0,count = 1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==s.charAt(i-1)){
                count++;
            }
            if(s.charAt(i)!=s.charAt(i-1)){
                list.add(count);
                count = 1;
            }
        }
        list.add(count);
        //System.out.println(list);
        for(int j=0;j<list.size()-1;j++){
            ans+=Math.min(list.get(j),list.get(j+1));
        }
        return ans;
    }
}
