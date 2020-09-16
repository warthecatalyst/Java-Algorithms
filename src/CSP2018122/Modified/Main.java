package CSP2018122.Modified;

import java.util.Scanner;

public class Main { //小明放学
    static long r,y,g;
    static int n;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        r = scanner.nextLong(); y = scanner.nextLong(); g = scanner.nextLong();
        n = scanner.nextInt();
        Position[] positions = new Position[n];
        for(int i=0;i<n;i++){
            positions[i] = new Position(scanner.nextInt(),scanner.nextLong());
        }
//        for(int i=0;i<n;i++){   //for debug
//            System.out.println(positions[i]);
//        }
        long time = 0;
        for(int i=0;i<n;i++){
            int t = positions[i].type;
            long cost = positions[i].time;
            if(t==2){
                cost+= r;
            }else if(t==3){
                cost = 0;
            }
            if(cost==0){
                continue;
            }
            time += cost;
            if(i<n-1){
                positions[i+1].move(time);
            }
        }
        System.out.println(time);
    }

    static class Position{
        int type;   //0代表为道路，1代表为红灯，2代表为黄灯，3代表为绿灯
        long time;   //代表在出发的时刻，当前显示的时间

        Position(int type,long time){
            this.type = type;
            this.time = time;
        }

        void move(long pass){
            if(type==0){
                return;
            }
            time -= pass;
            while(time<=0){
                if(type==1){    //为红灯
                    time+= g;
                    type = 3;
                }
                else if(type==2){ //为黄灯
                    time+= r;
                    type = 1;
                }
                else if(type==3){//为绿灯
                    time+= y;
                    type = 2;
                }
            }
        }

        @Override
        public String toString() {
            return "Position{" +
                    "type=" + type +
                    ", time=" + time +
                    '}';
        }
    }
}
