package SolveP3.CSP2016123;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static int p,r,u,q;
    public static void main(String[] args) {
        Map<String,Boolean> hasLevel = new HashMap<>();
        Map<String,Integer> maxLevel = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        p = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<p;i++){
            String line = scanner.nextLine();
            if(line.contains(":")){
                String[] parts = line.split(":");
                hasLevel.put(parts[0],true);
                maxLevel.put(parts[0],Integer.parseInt(parts[1]));
            }else{
                hasLevel.put(line,false);
            }
        }
        r = scanner.nextInt();
        scanner.nextLine();
        Map<String,Map<String,Integer>> roles = new HashMap<>();
        for(int i=0;i<r;i++){
            String line = scanner.nextLine();
            String parts[] = line.trim().split(" ");
            Map<String,Integer> temp = new HashMap<>();
            for(int j=2;j<parts.length;j++){
                if(parts[j].contains(":")){
                    String[] part1 = parts[j].split(":");
                    temp.put(part1[0],Integer.parseInt(part1[1]));
                }else{
                    temp.put(parts[j],-1);
                }
            }
            roles.put(parts[0],temp);
        }
        //System.out.println(roles);
        Map<String,Map<String,Integer>> users = new HashMap<>();
        u = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<u;i++){
            String line = scanner.nextLine();
            String[] parts = line.trim().split(" ");
            Map<String,Integer> temp = new HashMap<>();
            for(int j=2;j<parts.length;j++){
                String role = parts[j];
                Map<String,Integer> curmap = roles.get(role);
                for(Map.Entry<String,Integer> entry:curmap.entrySet()){
                    int level = temp.getOrDefault(entry.getKey(),-2);
                    if(entry.getValue()>level){
                        temp.put(entry.getKey(),entry.getValue());
                    }
                }
            }
            users.put(parts[0],temp);
        }
        //System.out.println(users);
        q = scanner.nextInt();
        for(int i=0;i<q;i++){
            String queryUser = scanner.next();
            String queryPri = scanner.next();
            if(!users.containsKey(queryUser)){
                System.out.println(false);
                continue;
            }
            String curPri = "";
            int level = -1;
            if(queryPri.contains(":")){
                curPri = queryPri.split(":")[0];
                level = Integer.parseInt(queryPri.split(":")[1]);
            }else{
                curPri = queryPri;
            }
            Map<String,Integer> temp = users.get(queryUser);
            if(!hasLevel.containsKey(curPri)){
                System.out.println(false);
                continue;
            }
            if(hasLevel.get(curPri)){
                if(!temp.containsKey(curPri)){
                    System.out.println(false);
                    continue;
                }
                int lev = temp.get(curPri);
                if(level==-1){
                    System.out.println(lev);
                    continue;
                }
                System.out.println(lev>=level);
            }else{
                System.out.println(temp.containsKey(curPri));
            }
        }
    }
}
