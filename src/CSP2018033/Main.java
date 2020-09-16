package CSP2018033;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<String> ans = new ArrayList<>();
    static boolean endWith = false;     //当前的输入的URL是否以'/'结尾
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(),m = scanner.nextInt();
        String[] rules = new String[n];
        String[] rulesName = new String[n];
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            rules[i] = parts[0];
            rulesName[i] = parts[1];
        }
//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(rules[i].trim().split("/")));
//            System.out.println(rulesName[i]);
//        }
        for(int i=0;i<m;i++){
            boolean flag = false;
            String line = scanner.nextLine().trim();
            endWith = line.charAt(line.length() - 1) == '/';
            String[] parts = line.split("/");
            for(int j=0;j<n;j++){
                ans.clear();
                String[] rulesJ = rules[j].split("/");
                //System.out.println(Arrays.toString(rulesJ));
                if(isMatch(0,rulesJ,parts)){
                    flag = true;
                    System.out.print(rulesName[j]+" ");
                    if(!ans.isEmpty()){
                        for(String s:ans){
                            System.out.print(s+" ");
                        }
                    }
                    System.out.println();
                    break;
                }
            }
            if(!flag){
                System.out.println("404");
            }
        }
    }
    static boolean isMatch(int cur,String[] rulesJ,String[] parts){
        if(cur>=parts.length){
            //System.out.println(Arrays.toString(rulesJ));
            return true;
        }
        if(cur>=rulesJ.length){
            return false;
        }
        if(rulesJ[cur].equals("<path>")){
            StringBuilder sb = new StringBuilder();
            for(int i = cur;i<parts.length;i++){
                if(i==parts.length-1&&!endWith){
                    sb.append(parts[i]);
                }
                else{
                    sb.append(parts[i]).append("/");
                }
            }
            ans.add(sb.toString());
            return true;
        }
        if(rulesJ[cur].equals("<str>")){
            ans.add(parts[cur]);
            return isMatch(cur+1,rulesJ,parts);
        }
        if(rulesJ[cur].equals("<int>")){
            if(isNumber(parts[cur])){
                ans.add(parts[cur]);
                return isMatch(cur+1,rulesJ,parts);
            }
            else{
                return false;
            }
        }
        String currule = rulesJ[cur];
        String tobet = parts[cur];
        if(currule.equals(tobet)){
            return isMatch(cur+1,rulesJ,parts);
        }else{
            return false;
        }
    }

    static boolean isNumber(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }
}
