package Problem1546;

import java.util.*;
public class Main {
    public static void main(String[] args){

    }
}

class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int size = nums.length;
        int ans = 0;
        int i = 0;
        while (i < size) {
            Set<Integer> set = new HashSet<Integer>() {{
                add(0);
            }};
            int sum = 0;
            while (i < size) {
                sum += nums[i];
                if (set.contains(sum - target)) {
                    ans++;
                    break;
                } else {
                    set.add(sum);
                    i++;
                }
            }
            i++;
        }
        return ans;
    }
}

