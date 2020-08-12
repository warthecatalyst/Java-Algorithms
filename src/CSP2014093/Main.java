package CSP2014093;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
    }
}

class Solution{
    String cur;
    int flag;
    int n;
    List<String> stringList = new ArrayList<>();
    public Solution(){
        Scanner scanner = new Scanner(System.in);
        cur = scanner.nextLine();
        //System.out.println(cur);
        flag = scanner.nextInt();
        n = scanner.nextInt();
        scanner.nextLine(); //吃掉空行
        if(flag==0){
            cur = cur.toLowerCase();
        }
        for(int i=0;i<n;i++){
            String s = scanner.nextLine();
            //System.out.println(s);
            tojudge(s);
        }
        for(int i=0;i<stringList.size();i++){
            System.out.println(stringList.get(i));
        }
    }
    void tojudge(String s){
        String cur1;
        String string;
        if(flag == 0){  //大小写不敏感
            cur1 = cur.toLowerCase();
            string = s.toLowerCase();
        }
        else{
            cur1 = cur;
            string = s;
        }
        if(string.contains(cur1)){
            stringList.add(s);
        }
    }
}
