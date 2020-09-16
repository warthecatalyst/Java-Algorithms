package CSP2016123;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Boolean> hasLevel = new HashMap<>();//存放权限是否有等级
        Map<String, Integer> maxLevel = new HashMap<>();//存放权限的等级
        Map<String, Map> roles = new HashMap<>();//存放角色的权限
        Map<String, Map> users = new HashMap<>();//存放用户的权限(只存该权限的最高等级)
        int p = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<p;i++){
            String cur = scanner.nextLine();
            String[] cur1 = cur.trim().split(":");
            if(cur1.length==1){
                hasLevel.put(cur1[0],false);
            }else{
                hasLevel.put(cur1[0],true);
                maxLevel.put(cur1[0],Integer.parseInt(cur1[1]));
            }
        }
        int r = scanner.nextInt();
        for(int i=0;i<r;i++){
            String rolename = scanner.next();
            int num = scanner.nextInt();
            
        }
    }
}
