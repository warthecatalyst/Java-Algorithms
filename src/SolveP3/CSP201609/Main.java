package SolveP3.CSP201609;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int player1 = 30;
    static int player2 = 30;
    static Follower[] playlist1 = new Follower[8];
    static Follower[] playlist2 = new Follower[8];
    static int winner = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int turn = 0;   //0代表1号玩家，1代表2号玩家
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            if (line.contains("summon")) {
                String[] parts = line.split(" ");
                int position = Integer.parseInt(parts[1]);
                int attack = Integer.parseInt(parts[2]);
                int health = Integer.parseInt(parts[3]);
                Follower follower = new Follower(attack, health);
                if (turn == 0) {
                    for (int j = 6; j >= position; j--) {
                        playlist1[j + 1] = playlist1[j];
                    }
                    playlist1[position] = follower;
//                    for (int j = 1; j <= 7; j++) {
//                        System.out.println(playlist1[j]);
//                    }
                } else {
                    for (int j = 6; j >= position; j--) {
                        playlist2[j + 1] = playlist2[j];
                    }
                    playlist2[position] = follower;
//                    for (int j = 1; j <= 7; j++) {
//                        System.out.println(playlist2[j]);
//                    }
                }
            } else if (line.contains("attack")) {
                String[] parts = line.split(" ");
                int attacker = Integer.parseInt(parts[1]);
                int defender = Integer.parseInt(parts[2]);
                if (turn == 0) {
                    if (defender == 0) {
                        player2 -= playlist1[attacker].attack;
                        if (player2 <= 0) {
                            winner = 1;
                        }
                    } else {
                        playlist1[attacker].losthealth(playlist2[defender].attack);
                        playlist2[defender].losthealth(playlist1[attacker].attack);
                        if (playlist1[attacker].isDead()) {
                            for (int j = attacker; j < 6; j++) {
                                playlist1[j] = playlist1[j + 1];
                            }
                            playlist1[7] = null;
                        }
                        if (playlist2[defender].isDead()) {
                            for (int j = defender; j < 6; j++) {
                                playlist2[j] = playlist2[j + 1];
                            }
                            playlist2[7] = null;
                        }
//                        for (int j = 1; j <= 7; j++) {
//                            System.out.println(playlist1[j]);
//                        }
//                        for (int j = 1; j <= 7; j++) {
//                            System.out.println(playlist2[j]);
//                        }
                    }
                } else {
                    if (defender == 0) {
                        player1 -= playlist2[attacker].attack;
                        if (player1 <= 0) {
                            winner = -1;
                        }
                    } else {
                        playlist2[attacker].losthealth(playlist1[defender].attack);
                        playlist1[defender].losthealth(playlist2[attacker].attack);
                        if (playlist2[attacker].isDead()) {
                            for (int j = attacker; j < 6; j++) {
                                playlist2[j] = playlist2[j + 1];
                            }
                            playlist2[7] = null;
                        }
                        if (playlist1[defender].isDead()) {
                            for (int j = defender; j < 6; j++) {
                                playlist1[j] = playlist1[j + 1];
                            }
                            playlist1[7] = null;
                        }
//                        for (int j = 1; j <= 7; j++) {
//                            System.out.println(playlist1[j]);
//                        }
//                        for (int j = 1; j <= 7; j++) {
//                            System.out.println(playlist2[j]);
//                        }
                    }
                }
            } else if (line.contains("end")) {
                turn = 1 - turn;
            }
        }
        System.out.println(winner);
        System.out.println(player1);
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            if (playlist1[i] != null) {
                ans1.add(playlist1[i].health);
            }
            if (playlist2[i] != null) {
                ans2.add(playlist2[i].health);
            }
        }
        System.out.print(ans1.size() + " ");
        for (Integer integer : ans1) {
            System.out.print(integer + " ");
        }
        System.out.println();
        System.out.println(player2);
        System.out.print(ans2.size()+" ");
        for(int k:ans2){
            System.out.print(k+" ");
        }
    }
    static class Follower{
        int health,attack;
        Follower(int attack,int health){
            this.attack = attack;
            this.health = health;
        }
        void losthealth(int attacknum){
            health-=attacknum;
        }
        boolean isDead(){
            return health<=0;
        }

        @Override
        public String toString() {
            return "Follower{" +
                    "health=" + health +
                    ", attack=" + attack +
                    '}';
        }
    }
}
