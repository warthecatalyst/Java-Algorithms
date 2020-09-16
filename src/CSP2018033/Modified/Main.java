package CSP2018033.Modified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int n,t;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        n = scanner.nextInt(); t = scanner.nextInt();
        scanner.nextLine();
        Pattern[] patterns = new Pattern[n];
        String[] name = new String[n];
        for(int i=0;i<n;i++){
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            name[i] = parts[1];
            parts[0] = parts[0].replaceAll("\\.","\\\\\\.")
                    .replaceAll("<str>","([\\\\w\\\\-_]+)")
                    .replaceAll("<int>","[0]*([0-9]+)")
                    .replaceAll("<path>","([\\\\w_\\\\-/\\\\.]+)");
            patterns[i] = Pattern.compile(parts[0]);
            //System.out.println(patterns[i]);
        }
        for(int i=0;i<t;i++){
            String line = scanner.nextLine();
            boolean found = false;
            for(int j=0;j<n;j++){
                Matcher matcher = patterns[j].matcher(line);
                if(matcher.matches()){
                    System.out.print(name[j]+" ");
                    for(int k=0;k<matcher.groupCount();k++){
                        System.out.print(matcher.group(k+1)+" ");
                    }
                    System.out.println();
                    found = true;
                    break;
                }
            }
            if(!found) {
                System.out.println("404");
            }
        }
    }
}
