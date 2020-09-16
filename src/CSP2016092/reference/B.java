package CSP2016092.reference;

import java.util.*;
import java.util.function.Consumer;
import java.util.concurrent.ArrayBlockingQueue;
import static java.lang.System.*;

public class B {

    static int counter = 0;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean used[] = new boolean[100];
        int empty[] = new int[20];
        int begin = 0;
        for (int i = 0; i < 20; i++) {
            empty[i] = 5;
        }
        for (int i = 0; i < n; i++) {
            int next = in.nextInt();
            for (int j = 0; j < 20; j++) {
                if (empty[j] >= next) {
                    for (int k = j * 5 + 5 - empty[j]; k < j * 5 + 5 - empty[j] + next; k++) {
                        used[k] = true;
                        out.print(k + 1 + " ");
                    }
                    empty[j] -= next;
                    next = 0;
                    break;
                }
            }
            while (next > 0) {
                if (!used[begin]) {
                    used[begin] = true;
                    empty[begin / 5]--;
                    out.print(begin + 1 + " ");
                    next--;
                }
                begin++;
            }
            out.println();
        }
    }
}


