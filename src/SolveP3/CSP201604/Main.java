package SolveP3.CSP201604;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int P;
    static String curDirectory;
    public static void main(String[] args) {
        P = scanner.nextInt();
        scanner.nextLine();
        curDirectory = scanner.nextLine().trim();
        while(P-->0){
            String line = scanner.nextLine();
            System.out.println(ProcessString(line));
        }
    }
    static String ProcessString(String line){
        if(line.startsWith("/")){
            line = line.replaceAll("/{2,}","/").replaceAll("/\\./","/");
            //System.out.println(line);
            String[] parts = line.split("/");
            StringBuilder s = new StringBuilder();
            for(String part:parts){
                if(part.equals("..")){
                    if(s.lastIndexOf("/")==0||s.lastIndexOf("/")==-1){
                        s = new StringBuilder();
                        //System.out.println(s);
                        continue;
                    }
                    s = new StringBuilder(s.substring(0, s.lastIndexOf("/")));
                    //System.out.println(s);
                }else if (part.equals(".")||part.equals("")) {
                    continue;
                }else{
                    s.append("/").append(part);
                    //System.out.println(s);
                }
            }
            if(s.length()==0){
                return "/";
            }
            return s.toString();
        }
        else{
            line = line.replaceAll("/{2,}","/").replaceAll("/\\./","/");
            //System.out.println(line);
            String[] parts = line.split("/");
            StringBuilder s = new StringBuilder(curDirectory);
            for(String part:parts){
                if(part.equals("..")){
                    if(s.lastIndexOf("/")==0||s.lastIndexOf("/")==-1){
                        s = new StringBuilder();
                        //System.out.println(s);
                        continue;
                    }
                    s = new StringBuilder(s.substring(0,s.lastIndexOf("/")));
                    //System.out.println(s);
                }else if(part.equals(".")||part.equals("")){
                    continue;
                }else{
                    s.append("/").append(part);
                    //System.out.println(s);
                }
            }
            if(s.length()==0){
                return "/";
            }
            return s.toString();
        }
    }
}
