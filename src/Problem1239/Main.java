package Problem1239;

import java.util.*;

public class Main {
    public static void main(String[] args){
        List<String> arr = new ArrayList<>();
        arr.add("abc");
        arr.add("ab");
        arr.add("cd");
        arr.add("efg");
        arr.add("ef");
        arr.add("gh");
        Solution solution = new Solution();
        System.out.println(solution.maxLength(arr));
    }
}

class Solution {
    private int max = 0;

    public int maxLength(List<String> arr) {

        dfs(arr, 0, "");

        return max;
    }

    public void dfs(List<String> arr, int idx, String track) {
        if (idx == arr.size()) {
            return;
        }

        // track串联不重复的元素
        for (int i = idx; i < arr.size(); i++) {
            if (!checkDup(track, arr.get(i))) {
                max = Math.max(max, track.length() + arr.get(i).length());
                dfs(arr, i + 1, track + arr.get(i));
            }
        }
    }
    //通过set来判断是否有重复的元素
    boolean checkDup(String str1, String str2) {
        Set<Character> charSet = new HashSet<>();
        for (char ch : str1.toCharArray()) {
            if (charSet.contains(ch)) {
                return true;
            }
            charSet.add(ch);
        }

        for (char ch : str2.toCharArray()) {
            if (charSet.contains(ch)) {
                return true;
            }

            charSet.add(ch);
        }

        return false;
    }
}
