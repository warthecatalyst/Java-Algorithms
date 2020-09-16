package CSP2018035;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int Q = 1000000007;
    static final int T = 10;
    static int n,m,L,R;
    static Node[] nodes;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        for(int i=0;i<T;i++){
            n = scanner.nextInt(); m = scanner.nextInt(); L = scanner.nextInt(); R = scanner.nextInt();
            nodes = new Node[n+1];
            for(int j=1;j<=n;j++){
                nodes[j] = new Node(scanner.nextInt());
            }
            for(int j=1;j<=n-1;j++){
                int cur = scanner.nextInt();
                nodes[cur].childrens.add(nodes[j+1]);
                nodes[j+1].parent = nodes[cur];
            }

        }
    }
    static class Node{
        int a;
        List<Node> childrens;
        Node parent;
        Node(int a){
            this.a = a;
            childrens = new ArrayList<>();
            parent = null;
        }
    }
}
