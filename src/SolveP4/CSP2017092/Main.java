package SolveP4.CSP2017092;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int N,K;
    static int time = Integer.MIN_VALUE;
    static List<Action> actions = new ArrayList<>();
    static List<Integer> retkeys = new ArrayList<>();
    static int[] keylist;
    public static void main(String[] args) {
        N = scanner.nextInt(); K = scanner.nextInt();
        keylist = new int[N];
        for(int i=0;i<N;i++){
            keylist[i] = i+1;
        }
        for(int i=0;i<K;i++){
            Action action = new Action(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
            time = Math.max(time,action.e);
            actions.add(action);
        }
        int c = 0;
        while(c<=time){
            returnKeys(c);
            borrowKeys(c);
            c++;
//            for(int i=0;i<keylist.length;i++){
//                System.out.print(keylist[i]+" ");
//            }
//            System.out.println();
        }
        for(int i=0;i<keylist.length;i++){
            System.out.print(keylist[i]+" ");
        }
    }
    static void returnKeys(int k){
        List<Action> removeactions = new ArrayList<>();
        for (Action action : actions) {
            if (action.e == k) {
                retkeys.add(action.w);
                removeactions.add(action);
            }
        }
        for(Action action:removeactions){
            actions.remove(action);
        }
        Collections.sort(retkeys);
        int j = 0;
        for(int i=0;i<keylist.length&&j<retkeys.size();i++){
            if(keylist[i]==0){
                keylist[i] = retkeys.get(j++);
            }
        }
        retkeys.clear();
    }

    static void borrowKeys(int k){
        for(Action action:actions){
            if(action.s==k){
                int w = action.w;
                for(int i=0;i<keylist.length;i++){
                    if(keylist[i] == w){
                        keylist[i] = 0;
                    }
                }
            }
        }
    }
    static class Action{
        int w,s,c,e;    //使用的钥匙编号，开始使用的时间，使用的时长，结束使用的时间
        Action(int w,int s,int c){
            this.w = w;
            this.s = s;
            this.c = c;
            this.e = s+c;
        }
    }
}
