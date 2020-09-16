package Problem347;

import java.util.*;

public class Main {
    public static void main(String[] args){
        int[] nums = new int[]{1,1,1,2,2,3};
        Solution solution = new Solution();
        int[] ans = solution.topKFrequent(nums,2);
        for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]+" ");
        }
    }
}

class Solution {    //解法1
    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        List<Frequency> fre = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],count++);
                fre.add(new Frequency(nums[i]));
            }
            else{
                int index = map.get(nums[i]);
                Frequency cur = fre.get(index);
                cur.freq++;
                fre.set(index,cur);
            }
        }
        Collections.sort(fre);
//        for (Frequency frequency : fre) {
//            System.out.println(frequency);
//        }
        for(int i=0;i<k;i++){
            ans[i] = fre.get(i).val;
        }
        return ans;
    }

    static class Frequency implements Comparable<Frequency>{
        int val;
        int freq;

        Frequency(int val){
            this.val = val;
            freq = 1;
        }
        @Override
        public int compareTo(Frequency o) {
            return o.freq-freq;
        }

        @Override
        public String toString() {
            return "Frequency{" +
                    "val=" + val +
                    ", freq=" + freq +
                    '}';
        }
    }
}
