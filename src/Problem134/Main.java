package Problem134;

public class Main {
    public static void main(String[] args){

    }
}

class Solution{
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length,total_tank = 0,cur_tank = 0,starting = 0;
        int max = Integer.MIN_VALUE;
        int index = -1;
        for(int i=0;i<n;i++){
            total_tank+=gas[i]-cost[i];
            cur_tank += gas[i]-cost[i];
            if(cur_tank<0){
                starting = i+1;
                cur_tank = 0;
            }
        }
        return total_tank>=0? starting:-1;
    }
}
