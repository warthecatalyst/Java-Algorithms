package CSP2019123;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n;
    static int[] ct = new int[1500];
    public static void main(String[] args){
        n = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String line = scanner.nextLine();
            String[] cur = line.trim().split("=");
            //System.out.println(cur[0]+" -------- "+cur[1]);
            Arrays.fill(ct,0);
            String[] lefts = cur[0].trim().split("\\+");
            //System.out.println(Arrays.toString(lefts));
            String[] rights = cur[1].trim().split("\\+");
            //System.out.println(Arrays.toString(rights));
            for (String s : lefts) {
                //System.out.println(s);
                int index = 0;
                int k = 0;
                for (; k < s.length(); k++) {
                    if (s.charAt(k) >= '0' && s.charAt(k) <= '9') {
                        index = 10 * index + s.charAt(k) - '0';
                    } else {
                        break;
                    }
                }
                if (index == 0) index = 1;
                process(s, k, s.length(), index, false);
            }
            for(String s:rights){
                int index = 0;
                int k = 0;
                for(;k<s.length();k++){
                    if(s.charAt(k)>='0'&&s.charAt(k)<='9'){
                        index = 10*index+s.charAt(k)-'0';
                    }else{
                        break;
                    }
                }
                if(index == 0) index = 1;
                process(s,k,s.length(),index,true);
            }
//            System.out.println("left: ");
//            for(Map.Entry<String,Integer> entry:left.entrySet()){
//                System.out.println(entry.getKey()+"-->"+entry.getValue());
//            }
//            System.out.println("right: ");
//            for(Map.Entry<String,Integer> entry:right.entrySet()){
//                System.out.println(entry.getKey()+"-->"+entry.getValue());
//            }
            boolean flag = true;
            for(int j = 0;j<1500;j++){
                if(ct[j]!=0){
                    flag = false;
                    System.out.println("N");
                    break;
                }
            }
            if(flag){
                System.out.println("Y");
            }
        }
    }
    public static int process(String s,int begin,int end,int index,boolean fuh){
        int num = 0,mask = 1;
        for(int i=end-1;i>=begin;i--){
            char s1 = s.charAt(i);
            //System.out.println(s1);
            if(s1>='0'&&s1<='9'){
                num += mask*(s1-'0');
                mask *= 10;
            }
            else{
                mask = 1;
                if(num==0) num = 1;
                if(s1==')'){
                    i = process(s,begin,i,index*num,fuh);
                    num = 0;
                }
                else if(s1=='(') return i;
                else{
                    int code = 0;
                    if(s1>='A'&&s1<='Z'){
                        code = s1-'A';
                    }
                    else{
                        i--;
                        code = 26*(s1-'a'+1)+s.charAt(i)-'A';
                    }
                    if(!fuh) ct[code]+=num*index;
                    else ct[code]-=num*index;
                    num = 0;
                }
            }
        }
        return begin;
    }
}
