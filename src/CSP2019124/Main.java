package CSP2019124;


import java.util.*;


public class Main {
    static int m,n,t,k;
    static Queue<Action> queue = new PriorityQueue<>();        //优先队列，用于找出最早的时间
    static List<Integer>[] neibours;
    static List<Integer>[] ans;
    public static boolean canAccept(List<Integer> old,List<Integer> New){
        if(New == null){
            return false;
        }
        if(old == null){
            return true;
        }
        if(New.size()>old.size()){
            return true;
        }
        if(New.size()==old.size()){
            return New.get(New.size()-1)<old.get(New.size()-1);
        }
        return false;
    }

    public static void spread(int vertex,int time){ //把当前的扩散出去
        for(int i:neibours[vertex]){
            if(canAccept(ans[i],ans[vertex])){
                Action action = new Action(time,i);
                action.actions[0] = new ArrayList<>(ans[vertex]);   //注意了!!!!
                queue.add(action);
            }
        }
    }
    public static void query(int vertex,int time){  //查询vertex节点在time时间段的情况
        //首先更新小于等于当前时间段的所有操作
        while(!queue.isEmpty()&&queue.peek().time<=time){
            Action cur = queue.poll();
            //System.out.println("time = "+cur.time+" , vertex = "+cur.ver);
            //先执行更新，在添加新快
            List<Integer> curlist0 = cur.actions[0];
            List<Integer> curlist1 = cur.actions[1];
            //System.out.println(curlist0+"  "+curlist1);
            boolean flag = canAccept(ans[cur.ver],curlist0)||(curlist1!=null&&!curlist1.isEmpty());
            //System.out.println("Can Spread? "+flag);
            if(canAccept(ans[cur.ver],curlist0)){
                ans[cur.ver] = new ArrayList<>(curlist0);
            }
            if(curlist1!=null&&curlist1.size()>0){
//                System.out.println(String.valueOf(cur.ver)+ans[cur.ver]);
//                System.out.println("ans[1] = "+ans[1]);
                ans[cur.ver].addAll(curlist1);
//                System.out.println(ans[1]==ans[2]);
                //System.out.println(String.valueOf(cur.ver)+ans[cur.ver]);
//                System.out.println("ans[1] = "+ans[1]);
            }
            if(flag){
                spread(cur.ver,cur.time+t);
//                Action[] plus = new Action[queue.size()];
//                queue.toArray(plus);
//                for (Action action : plus) {
//                    System.out.println(action);
//                }
            }
        }
        //更新完毕
        System.out.print(ans[vertex].size());
        for(int i=0;i<ans[vertex].size();i++){
            System.out.print(" "+ans[vertex].get(i));
        }
        System.out.println();
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); m = scanner.nextInt();
        neibours = new ArrayList[n+1];
        ans = new ArrayList[n+1];
        for(int i=0;i<neibours.length;i++){
            neibours[i] = new ArrayList<>();
            ans[i] = new ArrayList<>();
            ans[i].add(0);
        }
        for(int i=0;i<m;i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            neibours[from].add(to);
            neibours[to].add(from);
        }
        t = scanner.nextInt();k = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<k;i++){
            String line = scanner.nextLine();
            String[] parts = line.trim().split(" ");
            if(parts.length==3){    //添加一个块
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                Action action = new Action(b,a);
                action.actions[1].add(c);
                queue.add(action);
//                Action[] plus = new Action[queue.size()];
//                queue.toArray(plus);
//                for(int j=0;j<plus.length;j++){
//                    System.out.println(plus[j]);
//                }
            }else{
                query(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
            }
        }
    }

    static class Action implements Comparable<Action> { //表示在time时刻，编号为ver的节点需要更新的操作
        int time;
        int ver;
        List<Integer>[] actions;    //只有两个,0为需要更新的链条，1为需要添加的链条

        Action(int time,int ver){
            this.time = time;
            this.ver = ver;
            this.actions = new ArrayList[2];
            this.actions[0] = new ArrayList<>();
            this.actions[1] = new ArrayList<>();
        }

        Action(int time,int ver,List<Integer>[] actions){
            this.time = time;
            this.ver = ver;
            this.actions = actions;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Action)) return false;
            Action action = (Action) o;
            return time == action.time &&
                    ver == action.ver &&
                    Arrays.equals(actions, action.actions);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(time, ver);
            result = 31 * result + Arrays.hashCode(actions);
            return result;
        }

        @Override
        public int compareTo(Action o) {
            if(this.time==o.time&&this.ver==o.ver){
                if(actions[0].size()==o.actions[0].size()){ //先加入的节点先加入
                    return 0;
                }else{
                    return o.actions[0].size()-actions[0].size();   //要更新的长度更长的先执行，否则会破坏顺序
                }
            }
            if(time==o.time){
                return ver-o.ver;
            }
            return this.time-o.time;
        }

        @Override
        public String toString() {
            return "Action :{" +
                    "time=" + time +
                    ", ver=" + ver +
                    ", actions=" + Arrays.toString(actions) +
                    '}';
        }
    }
}
