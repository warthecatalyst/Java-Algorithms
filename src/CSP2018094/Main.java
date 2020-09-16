package CSP2018094;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int n;
    static int[] nums;
    static boolean found = false;
    static List<Integer> result;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = scanner.nextInt();
        }
        List<Integer> list = new ArrayList<>();
        DFS(0,list);
        for(int i=0;i<n;i++){
            System.out.print(result.get(i)+" ");
        }
    }
    static void DFS(int cur,List<Integer> list){
        if(!found&&cur==n){ //递归结束
            boolean flag = true;
            for(int i=0;i<n;i++){   //检查是否符合要求
                if(i==0){
                    int ans = (list.get(i)+list.get(i+1))/2;
                    if(ans!=nums[i]){
                        flag = false;
                        break;
                    }
                }else if(i==n-1){
                    int ans = (list.get(i-1)+list.get(i))/2;
                    if(ans!=nums[i]){
                        flag = false;
                        break;
                    }
                }else{
                    int ans = (list.get(i-1)+list.get(i)+list.get(i+1))/3;
                    if(ans!=nums[i]){
                        flag = false;
                        break;
                    }
                }
            }
            if(flag){
                found = true;
                result = new ArrayList<>(list);
            }
            return;
        }
        if(!found) {
            if (cur == 0) {
                int max = 2 * nums[cur];
                for (int i = 1; i <= max; i++) {
                    list.add(i);
                    DFS(cur + 1, list);
                    list.remove(list.size()-1);
                }
            } else if (cur == n - 1) {
                int max = 2*nums[cur];
                for(int i=1;i<=max;i++){
                    if(i+list.get(list.size()-1)!=2*nums[cur]){
                        continue;
                    }
                    list.add(i);
                    DFS(cur+1,list);
                    list.remove(list.size()-1);
                }
            }
            else{
                int max = 3*nums[cur];
                for(int i=1;i<=max;i++){
                    if(cur==1){
                        if(i+list.get(list.size()-1)<2*nums[0]||(i+list.get(list.size()-1)>2*nums[0]+1)){
                            continue;
                        }
                    }else{
                        if((list.get(list.size()-1)+list.get(list.size()-2)+i)<3*nums[cur-1]||list.get(list.size()-1)+list.get(list.size()-2)+i>3*nums[cur-1]+2){
                            continue;
                        }
                    }
                    list.add(i);
                    DFS(cur+1, list);
                    list.remove(list.size()-1);
                }
            }
        }
    }
}
