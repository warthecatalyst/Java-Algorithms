package CSP2019034;

import java.util.Scanner;

public class Main {
    static int T,n;
    static Scanner scanner = new Scanner(System.in);
    static Process[] processes;
    static boolean[][][] visited;
    public static void main(String[] args) {
        T = scanner.nextInt();
        n = scanner.nextInt();
        scanner.nextLine();
        while(T-->0){
            processes = new Process[n];
            visited = new boolean[n][n][10];
            for(int i=0;i<n;i++){
                String line = scanner.nextLine();
                String[] parts = line.trim().split(" ");
                int len = parts.length;
                //System.out.println(len);
                processes[i] = new Process(len);
                for(int j=0;j<len;j++){
                    if(parts[j].charAt(0)=='S'){
                        int receiver = Integer.parseInt(parts[j].substring(1));
                        Operation operation = new Operation(true,receiver);
                        processes[i].operations[j] = operation;
                    }else{
                        int sender = Integer.parseInt(parts[j].substring(1));
                        Operation operation = new Operation(false,sender);
                        processes[i].operations[j] = operation;
                    }
                }
            }
            boolean flag = true;
            for(int i=0;i<n;i++){
                process(i);
            }
            for(int i=0;i<n;i++){
                if(!processes[i].isOver()){
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println(0);
            }else{
                System.out.println(1);
            }
        }
    }
    static void process(int i){
        if(processes[i].isOver()){
            return;
        }
        Operation cur = processes[i].get();
        int receiver = cur.Receiver;
        if(visited[i][receiver][processes[i].index]){
            return;
        }
        if(processes[receiver].isOver()){
            return;
        }
        Operation cur1 = processes[receiver].get();
        if(cur1.Receiver==i&&(cur.Send!=cur1.Send)){
            visited[i][receiver][processes[i].index] = true;
            visited[receiver][i][processes[receiver].index] = true;
            processes[i].move();
            processes[receiver].move();
            process(i);
            process(receiver);
        }else{
            visited[i][receiver][processes[i].index] = true;
            visited[receiver][i][processes[receiver].index] = true;
            process(receiver);
        }
    }

    static class Process{
        int index;
        Operation[] operations;
        Process(int num){
            index = 0;
            operations = new Operation[num];
        }
        Operation get(){
            return operations[index];
        }
        void move(){
            index++;
        }
        boolean isOver(){
            return index==operations.length;
        }
    }

    static class Operation{
        boolean Send;   //是否为发送请求
        int Receiver;

        Operation(boolean b,int re){
            Send = b;
            Receiver = re;
        }
    }
}

/*
三组测试用例
3 2
R1 S1
S0 R0
R1 S1
R0 S0
R1 R1 R1 R1 S1 S1 S1 S1
S0 S0 S0 S0 R0 R0 R0 R0

2 3
R1 S1
R2 S0 R0 S2
S1 R1
R1
R2 S0 R0
S1 R1

1 4
S1 R3
S2 R0
S3 R1
R2 S0
*/