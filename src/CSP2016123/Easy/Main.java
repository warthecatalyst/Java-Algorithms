package CSP2016123.Easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int p,q,r,u;
        Scanner scanner = new Scanner(System.in);
        p = scanner.nextInt();
        String[] pri = new String[p];
        scanner.nextLine();
        for(int i=0;i<p;i++){
            pri[i] = scanner.next();
        }
        r = scanner.nextInt();
        scanner.nextLine();
        Map<String,String[]> roles = new HashMap<>();
        for(int i=0;i<r;i++){
            String cur =scanner.nextLine();
            String[] cur1 = cur.trim().split(" ");
            int max = Integer.parseInt(cur1[1]);
            String[] cur2 = new String[max];
            if (max >= 0) System.arraycopy(cur1, 2, cur2, 0, max);
            roles.put(cur1[0],cur2);
        }
        u = scanner.nextInt();
        scanner.nextLine();
        Map<String,String[]> users = new HashMap<>();
        for(int i=0;i<u;i++){
            String cur = scanner.nextLine();
            String[] cur1 = cur.trim().split(" ");
            int max = Integer.parseInt(cur1[1]);
            String[] cur2 = new String[max];
            if(max>=0) System.arraycopy(cur1,2,cur2,0,max);
            users.put(cur1[0],cur2);
        }
        q = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<q;i++){
            String line = scanner.nextLine();
            String quser = line.trim().split(" ")[0];
            String qpri = line.trim().split(" ")[1];
            boolean flag = false;
            for (String s : pri) {
                if (qpri.equals(s)) {
                    flag = true;
                    break;
                }
            }
            if(!flag){
                System.out.println(false);
                continue;
            }
            if(!users.containsKey(quser)){
                System.out.println(false);
                continue;
            }
            flag = false;
            for(String role:users.get(quser)){
                if(!flag) {
                    for (String curpri : roles.get(role)) {
                        if(curpri.equals(qpri)){
                            flag = true;
                            System.out.println(true);
                        }
                    }
                }
            }
            if(!flag){
                System.out.println(false);
            }
        }
    }
}
