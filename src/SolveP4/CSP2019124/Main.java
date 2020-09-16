package SolveP4.CSP2019124;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Integer>[] ans;
    static List<Integer>[] edges;
    static Queue<Action> queue = new PriorityQueue<>();
    static int n,m,t,k;
    static void query(int vertex,int time){
        while (!queue.isEmpty()&&queue.peek().time<=time){
            Action action = queue.poll();
            int ver = action.ver;
            int curtime = action.time;
            List<Integer> list = action.act;
            boolean canSpread = false;
            if(action.isAdd){
                canSpread = true;
                for(int val:list){
                    ans[ver].add(val);
                }
            }else{
                if(canAccept(ver,list)){
                    canSpread = true;
                    ans[ver] = new ArrayList<>(list);
                }
            }
            if(canSpread){
                spread(ver,curtime+t);
            }
        }
//        List<Action> cc = new ArrayList<>(queue);
//        for(Action action:cc){
//            System.out.println(action);
//        }
        System.out.print(ans[vertex].size()+" ");
        for(int i=0;i<ans[vertex].size();i++){
            System.out.print(ans[vertex].get(i)+" ");
        }
        System.out.println();
    }
    static void spread(int vertex,int time){
        for(int v:edges[vertex]){
            if(canAccept(v,ans[vertex])){
                Action action = new Action(time,v,ans[vertex]);
                if(queue.contains(action)){
                    continue;
                }
                queue.add(action);
            }
        }
//        List<Action> cc = new ArrayList<>(queue);
//        for(Action action:cc){
//            System.out.println(action);
//        }
    }
    static boolean canAccept(int vertex,List<Integer> list){
        if(ans[vertex].size()<list.size()){
            //System.out.println(ans[vertex]);
            return true;
        }
        if(ans[vertex].size()==list.size()){
            //System.out.println(ans[vertex]);
            return list.get(list.size()-1)<ans[vertex].get(ans[vertex].size()-1);
        }
        return false;
    }
    public static void main(String[] args) {
        n = scanner.nextInt();m = scanner.nextInt();
        ans = new ArrayList[n+1];
        edges = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            edges[i] = new ArrayList<>();
            ans[i] = new ArrayList<>();
            ans[i].add(0);
        }
        for(int i=0;i<m;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            edges[from].add(to);
            edges[to].add(from);
        }
        t = scanner.nextInt(); k = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<k;i++){
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            if(parts.length==2){
                int vertex = Integer.parseInt(parts[0]);
                int time = Integer.parseInt(parts[1]);
                query(vertex,time);
            }else if(parts.length==3){
                int ver = Integer.parseInt(parts[0]);
                int time = Integer.parseInt(parts[1]);
                int block = Integer.parseInt(parts[2]);
                queue.add(new Action(time,ver,block));
//                List<Action> cc = new ArrayList<>(queue);
//                for(Action action:cc){
//                    System.out.println(action);
//                }
            }
        }
    }
    static class Action implements Comparable<Action>{
        int time,ver;   //ver节点在time的时间要进行的操作1和操作2
        boolean isAdd;
        List<Integer> act;
        Action(int time,int ver,int block){ //添加操作
            this.time = time;
            this.ver = ver;
            isAdd = true;
            act = new ArrayList<>();
            act.add(block);
        }
        Action(int time,int ver,List<Integer> list){
            this.time = time;
            this.ver = ver;
            this.isAdd = false;
            this.act = new ArrayList<>(list);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Action)) return false;
            Action action = (Action) o;
            return time == action.time &&
                    ver == action.ver &&
                    isAdd == action.isAdd &&
                    Objects.equals(act, action.act);
        }

        @Override
        public int hashCode() {
            return Objects.hash(time, ver, isAdd, act);
        }

        @Override
        public int compareTo(Action o) {
            if(time==o.time){
                if(ver==o.ver){
                    if(isAdd==o.isAdd){
                        return 0;
                    }
                    else{
                        if(this.isAdd){ //添加的操作在最后面
                            return 1;
                        }else{
                            return -1;
                        }
                    }
                }
                return ver-o.ver;
            }
            return time-o.time;
        }

        @Override
        public String toString() {
            return "Action{" +
                    "time=" + time +
                    ", ver=" + ver +
                    ", isAdd=" + isAdd +
                    ", act=" + act +
                    '}';
        }
    }
}
