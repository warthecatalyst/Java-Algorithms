package CSP2019124.Reference;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    static Scanner scanner;
    static int m, n;

    static int next() {
        return scanner.nextInt();
//		return (int) (Math.random() * 10000);
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        LinkedList<Integer>[] nei = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            nei[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = next();
            int b = next();
            nei[a].add(b);
            nei[b].add(a);
        }

        int[][] lk = new int[505][505];
        int[] lth = new int[505];

        for (int i = 1; i <= n; i++) {
            lth[i] = 1;
        }

        int t = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();

        Queue<Pt> pp = new ArrayBlockingQueue<>(30005);
        LinkedList<Nw> q[] = new LinkedList[1005];

        for (int i = 0; i < q.length; i++) {
            q[i] = new LinkedList<>();
        }

        int lastt = -1;

        for (int i = 0; i < k; i++) {
            String l = scanner.nextLine();
            String part[] = l.split("\\s");
            int time = Integer.parseInt(part[1]);

            while (!pp.isEmpty() && time > pp.peek().t) {
                int x = pp.poll().x;
                System.out.print(lth[x]);
                for (int jj = 0; jj < lth[x]; jj++) {
                    System.out.print(" ");
                    System.out.print(lk[x][jj]);
                }
                System.out.println();
            }

            while (time != lastt) {
                lastt++;
                LinkedList<Nw> nq = new LinkedList<>();
                while (!q[lastt % t].isEmpty()) {
                    Nw el = q[lastt % t].poll();
                    Iterator<Integer> it = nei[el.x].iterator();
                    while (it.hasNext()) {
                        Integer ii = it.next();
                        if (lth[ii] < el.lth || lth[ii] == el.lth && el.lk[el.lth - 1] < lk[ii][el.lth - 1]) {
                            lk[ii] = el.lk;
                            lth[ii] = el.lth;
                            Nw pt = new Nw();
                            pt.x = ii;
                            pt.lk = el.lk;
                            pt.lth = el.lth;
                            nq.add(pt);
                        }
                    }
                }
                q[lastt % t] = nq;
            }
            int indx = Integer.parseInt(part[0]);
            if (part.length == 2) {
                Pt pt = new Pt();
                pt.t = time;
                pt.x = indx;
                pp.add(pt);
            }
            else {
                lk[indx] = lk[indx].clone();
                lk[indx][lth[indx]] = Integer.parseInt(part[2]);
                lth[indx]++;
                Nw pt = new Nw();
                pt.x = indx;
                pt.lk = lk[indx];
                pt.lth = lth[indx];
                q[time % t].add(pt);
            }
        }
        while (!pp.isEmpty()) {
            int x = pp.poll().x;
            System.out.print(lth[x]);
            for (int jj = 0; jj < lth[x]; jj++) {
                System.out.print(" ");
                System.out.print(lk[x][jj]);
            }
            System.out.println();
        }
    }

    // System.out.print();

    static class Pt {
        int t, x;
    }

    static class Nw {
        int x;
        int lth;
        int[] lk;
    }
}
