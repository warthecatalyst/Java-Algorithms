package CSP2015093.reference;

import java.util.*;
import java.util.function.Consumer;
import java.util.concurrent.ArrayBlockingQueue;
import static java.lang.System.*;

public class Main {

    static int counter = 0;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        in.nextLine();
        String content[] = new String[n];
        for (int i = 0; i < n; i++) {
            content[i] = in.nextLine();
        }

        for (int i = 0; i < m; i++) {
            String line = in.nextLine();
            String name = "{{ " + line.substring(0, line.indexOf(' ')) + " }}";
            String rep = line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"'));
            for (int j = 0; j < n; j++) {
                content[j] = content[j].replace(name, rep);
            }
        }

        for (int i = 0; i < n; i++) {
            out.println(content[i].replaceAll("\\{\\{ .* \\}\\}", ""));
        }
    }
}
