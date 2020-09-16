package CSP2016044;

import java.util.*;
public class Main {
    static final int[][] direction = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();// n个节点
        int m = sc.nextInt();// m条边
        int t = sc.nextInt();// t个危险的方格
        Node[] nodes = new Node[t];
        for (int i = 0; i < t; i++) {
            nodes[i] = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        //map 用来存储所有的危险方格：
        Map<Node, Node> map = new HashMap<>();
        for (int i = 0; i < t; i++) {
            map.put(nodes[i], nodes[i]);
        }
        boolean[][][] vis = new boolean[n + 1][m + 1][300];
        Queue<Node> queue = new LinkedList<>();
        vis[1][1][0] = true;
        queue.add(new Node(1, 1, 0));
        int result = 0;
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            if (temp.x == n && temp.y == m) {
                result = temp.val;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int tmpX = temp.x + direction[i][0];// 下一步的x坐标
                int tmpY = temp.y + direction[i][1];// 下一步的y坐标
                int tmpVal = temp.val + 1;// 到达这个点的时刻
                Node tmpNode = new Node(tmpX, tmpY, tmpVal);
                if (tmpX > 0 && tmpX <= n && tmpY > 0 && tmpY <= m && !vis[tmpX][tmpY][tmpVal]) {
                    //如果x和y的坐标没有越界，且这个点在该时刻没有被访问，则判断该点是不是危险点
                    if (map.containsKey(tmpNode)) {
                        //如果是危险点，则判断是不是在危险时段，如果不在危险时段则将该店加入queue
                        int from = map.get(tmpNode).a;
                        int to = map.get(tmpNode).b;
                        if (tmpVal < from || tmpVal > to) {
                            queue.add(tmpNode);
                            vis[tmpX][tmpY][tmpVal] = true;
                        }
                    } else {
                        queue.add(tmpNode);
                        vis[tmpX][tmpY][tmpVal] = true;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
class Node {
    int x;// x行
    int y;// y列
    int a;// 在a到b时刻是危险的
    int b;
    int val;// 保存到达这个点的耗时
    public Node(int x, int y, int a, int b) {
        super();
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.val = 1;
    }
    public Node(int x, int y, int val) {
        super();
        this.x = x;
        this.y = y;
        this.val = val;
    }
    // 为了使用Map 需要实现重写equals()和hashCode()方法：
    @Override
    public boolean equals(Object obj) {
        return x == ((Node) obj).x && y == ((Node) obj).y;
    }
    @Override
    public int hashCode() {
        return x * 100 + y;
    }
}