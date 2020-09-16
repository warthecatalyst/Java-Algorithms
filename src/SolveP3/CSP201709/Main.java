package SolveP3.CSP201709;

import javax.swing.*;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int m,n;
    static String pre = "";
    static int flag = 0;
    static Map<String,String> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); m = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String line = scanner.nextLine().trim();
            ProcessLine(line);
            System.out.println(map);
            System.out.println(pre);
        }
        for(int i=0;i<m;i++){
            String line = scanner.nextLine().trim();
            line = "."+line;
            System.out.println(map.getOrDefault(line,"NOTEXIST"));
        }
    }

    static void ProcessLine(String line) {
        line = line.replaceAll("\\\\\\\\","\\\\").replaceAll("\\\\\"","\"");
        //System.out.println(line);
        String keyRegex = "\"(.*) *:";
        String valueRegex = ": *\"(.*) *";
        Pattern keyPattern = Pattern.compile(keyRegex);
        Pattern valuePattern = Pattern.compile(valueRegex);String key = "";String value = "";
        if(!line.contains("{")&&!line.contains("}")){
            Matcher keyMatcher = keyPattern.matcher(line);
            if(keyMatcher.find()){
                key = keyMatcher.group(1);
            }
            Matcher valuematch = valuePattern.matcher(line);
            if(valuematch.find()){
                value = valuematch.group(1);
            }
            if(!key.isEmpty()&&!value.isEmpty()){
                key = key.substring(0,key.lastIndexOf("\""));
                value = value.substring(0,value.lastIndexOf("\""));
                map.put(pre+"."+key,"STRING "+value);
            }
        }else if(!line.contains("{")){
            String[] parts = line.split("}");
            System.out.println(Arrays.toString(parts));
            if(parts.length==0){
                return;
            }
            //System.out.println(parts.length);
            for(int i=1;i<parts.length;i++){
                System.out.println(Arrays.toString(parts));
                if(parts[i].trim().length()==0||parts[i].equals(",")){
                    continue;
                }
                ProcessLine(parts[i]);
                int index = pre.lastIndexOf(".");
                //System.out.println(index);
                if(index==0||index==-1){
                    pre = "";
                }else {
                    pre = pre.substring(0, index);
                }
            }
        }
        else{
            String[] parts = line.split("\\{");
            if(parts.length==0){
                return;
            }
            System.out.println(Arrays.toString(parts));
            Matcher keyMatcher = keyPattern.matcher(parts[1]);
            if(keyMatcher.find()){
                key = keyMatcher.group(1);
            }
            System.out.println(key);
//            if(!key.isEmpty()){
//                System.out.println(key);
//            }else {
//                System.out.println("empty key");
//            }
            if(!key.isEmpty()){
                key = key.substring(0,key.lastIndexOf('\"'));
                map.put(pre+"."+key,"OBJECT");
                pre +="."+key;
            }
            for(int j=1;j<parts.length;j++){
                ProcessLine(parts[j]);
            }
        }
    }
}
