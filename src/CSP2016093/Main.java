package CSP2016093;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int winner = 0;
    static int player1 = 30;
    static int player2 = 30;
    static Node[] play1 = new Node[8];
    static Node[] play2 = new Node[8];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int turn = 0;
        for(int i=0;i<n;i++){
            String cur = scanner.nextLine();
            if(cur.contains("end")){
                turn = 1-turn;
                continue;
            }
            if(cur.contains("summon")){
                String[] s1 = cur.trim().split(" ");
                int position = Integer.parseInt(s1[1]);
                Node node = new Node(Integer.parseInt(s1[2]),Integer.parseInt(s1[3]));
                if(turn==0){
                    Node temp =play1[position];
                    play1[position] = node;
                    for(int j = position+1;j<=7;j++){
                        Node temp1 =play1[j];
                        play1[j] = temp;
                        temp = temp1;
                    }
//                    for(int j = 1;j<=7;j++){    //debug
//                        System.out.println(play1[j]);
//                    }
                }
                else{
                    Node temp =play2[position];
                    play2[position] = node;
                    for(int j = position+1;j<=7;j++){
                        Node temp1 = play2[j];
                        play2[j] = temp;
                        temp = temp1;
                    }
//                    for(int j = 1;j<=7;j++){    //debug
//                        System.out.println(play2[j]);
//                    }
                }
            }
            if(cur.contains("attack")){
                String[] s1 = cur.trim().split(" ");
                int attacker = Integer.parseInt(s1[1]);
                int defender = Integer.parseInt(s1[2]);
                if(turn==0){
                    if(defender==0){
                        player2-= play1[attacker].attack;
                        if(winner==0&&player2<=0){
                            winner = 1;
                        }
                    }
                    else{
                        play2[defender].health -= play1[attacker].attack;
                        play1[attacker].health -= play2[defender].attack;
                        if(play2[defender].health<=0){
                            play2[defender] = null;
                            if (7 - defender >= 0) System.arraycopy(play2, defender + 1, play2, defender, 7 - defender);
                        }
                        if(play1[attacker].health<=0){
                            play1[attacker] = null;
                            if(7-attacker>=0) System.arraycopy(play1,attacker+1,play1,attacker,7-attacker);
                        }
//                        for(int j = 1;j<=7;j++){    //debug
//                            System.out.println(play2[j]);
//                        }
                    }
                }
                else{
                    if(defender==0){
                        player1-= play2[attacker].attack;
                        if(winner==0&&player1<=0){
                            winner = -1;
                        }
                    }
                    else{
                        play1[defender].health -= play2[attacker].attack;
                        play2[attacker].health -= play1[defender].attack;
                        if(play1[defender].health<=0){
                            play1[defender] = null;
                            if (7 - defender >= 0) System.arraycopy(play1, defender + 1, play1, defender, 7 - defender);
                        }
                        if(play2[attacker].health<=0){
                            play2[attacker] = null;
                            if(7-attacker>=0) System.arraycopy(play2,attacker+1,play2,attacker,7-attacker);
                        }
//                        for(int j = 1;j<=7;j++){    //debug
//                            System.out.println(play1[j]);
//                        }
                    }
                }
            }
        }
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        System.out.println(winner);
        System.out.println(player1);
        for(int j=1;j<=7;j++){
            if(play1[j]!=null){
                arr1.add(play1[j].health);
            }
            if(play2[j]!=null){
                arr2.add(play2[j].health);
            }
        }
        System.out.print(arr1.size()+" ");
        for(int val:arr1){
            System.out.print(val+" ");
        }
        System.out.println();
        System.out.println(player2);
        System.out.print(arr2.size()+" ");
        for(int val:arr2){
            System.out.print(val+" ");
        }
    }

    static class Node implements Cloneable{
        int health,attack;
        Node(){

        }
        Node(int attack,int health){
            this.health = health;
            this.attack = attack;
        }
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Node(this.health,this.attack);
        }

        @Override
        public String toString() {
            return "{" +
                    "health=" + health +
                    ", attack=" + attack +
                    '}';
        }
    }
}
