package Problem679;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Solution so = new Solution();
        System.out.println(so.judgePoint24(new int[]{1,3,4,6}));
    }
}

class Solution{
    static final int TARGET = 24;
    static final double EPS = 1e-6;
    static final int ADD = 0,MULTIPLY = 1,SUBTRACT = 2,DIVIDE = 3;
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for(int num:nums){
            list.add((double) num);
        }
        return solve(list);
    }

    public boolean solve(List<Double> list){
        if(list.size()==0){
            return false;
        }
        if(list.size()==1){
            return Math.abs(list.get(0)-TARGET)<EPS;
        }
        int size = list.size();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(i!=j){
                    List<Double> list1 = new ArrayList<>();
                    for(int i1=0;i1<size;i1++){
                        if(i1!=i&&i1!=j){
                            list1.add(list.get(i1));
                        }
                    }
                    for(int k=0;k<4;k++){
                        if(k<2&&i>j){   //+和*一次就够了
                            continue;
                        }
                        if(k==ADD){
                            list1.add(list.get(i)+list.get(j));
                        }else if (k == MULTIPLY) {
                            list1.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRACT) {
                            list1.add(list.get(i) - list.get(j));
                        } else{
                            if (Math.abs(list.get(j)) < EPS)   {
                                continue;
                            } else {
                                list1.add(list.get(i) / list.get(j));
                            }
                        }
                        if (solve(list1)) {
                            return true;
                        }
                        list1.remove(list1.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
