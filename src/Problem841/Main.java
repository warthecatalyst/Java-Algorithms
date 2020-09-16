package Problem841;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args){

    }
}

class Solution{
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] canVisit = new boolean[n];
        Arrays.fill(canVisit,false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int cur = queue.poll();
            canVisit[cur] = true;
            List<Integer> list = rooms.get(cur);
            for(int val:list){
                if(!canVisit[val]){
                    queue.add(val);
                }
            }
        }
        for(int i=0;i<n;i++){
            if(!canVisit[i]){
                return false;
            }
        }
        return true;
    }
}