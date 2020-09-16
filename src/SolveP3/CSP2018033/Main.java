package SolveP3.CSP2018033;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        scanner.nextLine();
        Pattern[] patterns = new Pattern[n];
        String[] rulesName = new String[n];
        String intRegex = "[0]*(\\\\d+)";
        String strRegex = "([\\\\w_\\\\-.]+)";
        String pathRegex = "([\\\\w_\\\\-/.]+)";
        for(int i=0;i<n;i++){
            String line = scanner.nextLine();
            String[] parts = line.trim().split(" ");
            rulesName[i] = parts[1];
            parts[0] = parts[0].replaceAll("\\.","\\\\.")
                    .replaceAll("<int>",intRegex).replaceAll("<str>",strRegex)
                    .replaceAll("<path>",pathRegex);
            patterns[i] = Pattern.compile(parts[0]);
            //System.out.println(patterns[i]);
        }
        for(int i=0;i<m;i++){
            String line = scanner.nextLine().trim();
            boolean flag = false;
            for(int j=0;j<patterns.length;j++){
                Matcher matcher = patterns[j].matcher(line);
                if(matcher.matches()){
                    flag = true;
                    System.out.print(rulesName[j]+" ");
                    for(int k=1;k<=matcher.groupCount();k++){
                        System.out.print(matcher.group(k)+" ");
                    }
                    System.out.println();
                }
            }
            if(!flag){
                System.out.println("404");
            }
        }
    }
}
