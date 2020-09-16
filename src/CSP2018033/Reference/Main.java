package CSP2018033.Reference;

import java.util.*;
import java.util.regex.*;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        scanner.nextLine();
        Pattern pattern[] = new Pattern[num1];
        String name[] = new String[num1];
        for (int i = 0; i < num1; i++) {
            String line = scanner.nextLine();
            String part[] = line.split(" ");
            part[0] = part[0].replaceAll("\\.", "\\\\.")
                    .replaceAll("<str>", "([\\\\w\\.\\-\\]\\+\\)")
                    .replaceAll("<int>", "\\[0\\]\\*\\(\\[\\\\d\\]\\+\\)")
                    .replaceAll("<path>", "\\(\\[\\\\w\\.\\-\\/\\]\\+\\)");
            //System.out.println(part[0]);
            pattern[i] = Pattern.compile(part[0]);
            name[i] = part[1];
        }
        a:		for (int i = 0; i < num2; i++) {
//            for (int i1 = 0; i1 < 27000000 / num2; i1++) Math.random();
            String line = scanner.nextLine();
            for (int j = 0; j < num1; j++) {
                Matcher matcher = pattern[j].matcher(line);
                if (matcher.matches()) {
                    System.out.print(name[j] + " ");
                    for (int k = 0; k < matcher.groupCount(); k++) {
                        System.out.print(matcher.group(k + 1) + " ");
                    }
                    System.out.println();
                    continue a;
                }
            }
            System.out.println("404");
        }
    }
}
