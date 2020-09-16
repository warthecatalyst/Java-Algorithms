package CSP2018032;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n,t,L;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); L = scanner.nextInt(); t = scanner.nextInt();
        Ball[] balls = new Ball[n];
        for(int i=0;i<n;i++){
            balls[i] = new Ball(scanner.nextInt(),i+1);
        }
        Arrays.sort(balls);
//        for(int i=0;i<n;i++){
//            System.out.println(balls[i]);
//        }
        for(int time =0;time<t;time++){
            for(int i=0;i<n;i++){   //所有的球先移动，再判断是否相撞
                balls[i].move();
            }
            for(int i=0;i<n;i++){
                balls[i].collision();
                if(i!=n-1){
                    balls[i].collision(balls[i+1]);
                }
            }
        }
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1-i;j++){
                if(balls[j].index>balls[j+1].index){
                    Ball temp = balls[j];
                    balls[j] = balls[j+1];
                    balls[j+1] = temp;
                }
            }
        }
        for(int i=0;i<n;i++){
            System.out.print(balls[i].position+" ");
        }
    }

    static class Ball implements Comparable<Ball>{
        int position;
        int direction;
        int index;

        Ball(int position,int index){
            this.position = position;
            this.index = index;
            direction = 1;
        }
        void move(){
            position += direction;
        }
        void collision(){
            if (position==0||position==L){
                direction = -direction;
            }
        }

        void collision(Ball o){
            if(position==o.position){
                direction = -direction;
                o.direction = -o.direction;
            }
        }

        @Override
        public int compareTo(Ball o) {
            return position-o.position;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "position=" + position +
                    ", direction=" + direction +
                    ", index=" + index +
                    '}';
        }
    }
}
