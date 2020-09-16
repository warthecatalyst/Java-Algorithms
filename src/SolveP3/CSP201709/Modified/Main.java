package SolveP3.CSP201709.Modified;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static int n,m;
    static String pre = "",lay = "";
    static int flag = 0;
    static Map<String,String> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); m = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String line = scanner.nextLine().trim();
            line = line.replaceAll(" ","");
            processLine(line);
            //System.out.println(map);
        }
        //System.out.println(map);
        for(int i=0;i<m;i++){
            String line = scanner.nextLine().trim();
            line = "."+line;
            System.out.println(map.getOrDefault(line,"NOTEXIST"));
        }
    }

    static void processLine(String line){
        for(int i=0;i<line.length();i++){
            char c = line.charAt(i);
            switch (c){
                case '{':{
                    if(lay.length()==0) break;
                    pre += '.'+lay;
                    //System.out.println(pre);
                    flag = 0;
                    map.put(pre,"OBJECT");
                    break;
                }
                case '}':{
                    int index = pre.lastIndexOf('.');
                    if(index==0||index==-1){
                        pre = "";
                        break;
                    }
                    pre = pre.substring(0,index);
                    break;
                }
                case '"':{
                    StringBuilder temp = new StringBuilder();
                    for(i=i+1;i<line.length();i++){
                        if(line.charAt(i)=='\\'){
                            i++;
                            temp.append(line.charAt(i));
                        }else if(line.charAt(i)=='\"'){
                            break;
                        }else{
                            temp.append(line.charAt(i));
                        }
                    }
                    if(flag==0){
                        lay = temp.toString();
                    }else{
                        map.put(pre+"."+lay,"STRING "+temp.toString());
                    }
                    break;
                }
                case ':':flag = 1;break;
                case ',':flag = 0;break;
            }
        }
    }
}
