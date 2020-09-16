package PAT.Problem1001.Test;

public class Main {
    static final int MIN = -1000000;
    static final int MAX = 1000000;
    public static void main(String[] args) {
        int a,b;
        for(a = MIN;a<=MAX;a++){
            for(b=MIN;b<=MAX;b++){
                int ans = a+b;
                boolean flag = (a+b)<0;
                String s = String.valueOf(Math.abs(a+b));
                //System.out.println(s);
                StringBuilder sb = new StringBuilder();
                while(s.length()>3){
                    StringBuilder dd = new StringBuilder(s.substring(s.length()-3));
                    dd.reverse();
                    sb.append(dd).append(",");
                    //System.out.println(sb);
                    s = s.substring(0,s.length()-3);
                }
                StringBuilder dd = new StringBuilder(s);
                dd.reverse();
                sb.append(dd);
                if(flag){
                    sb.append("-");
                }
                sb.reverse();
                String s1 = sb.toString();
                s1 = s1.replaceAll(",","");
                int myans = Integer.parseInt(s1);
                if(myans!=ans){
                    System.out.println("a = "+a+", b = "+b);
                    System.out.println("correct ans: "+ans);
                    System.out.println("my ans:"+sb);
                    break;
                }
            }
        }
        System.out.println("Correct!");
    }
}
