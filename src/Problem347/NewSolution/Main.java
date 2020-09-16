package Problem347.NewSolution;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        Solution solution = new Solution();
        int[] ans = solution.topKFrequent(nums,2);
        for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]+" ");
        }
    }
}

class Solution {    //解法2
    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            int freq = map.getOrDefault(num, 0);
            freq++;
            map.put(num, freq);
        }
        List<Map.Entry<Integer,Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue()-o1.getValue(); //从大到小排序
            }
        });
        for(int i=0;i<k;i++){
            ans[i] = entryList.get(i).getKey();
        }
        return ans;
    }
}


