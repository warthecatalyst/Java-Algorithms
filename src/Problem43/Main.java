package Problem43;

public class Main {
    public static void main(String[] args){
        String s1 ="123";
        String s2 = "456";
        Solution solution = new Solution();
        System.out.println(solution.multiply(s1,s2));
    }
}

class Solution{
    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")){
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<num1.length();i++){
            int ch1 = num1.charAt(i)-'0';
            int flag = 0;
            StringBuilder cur = new StringBuilder();
            for(int j=num2.length()-1;j>=0;j--){
                int ch2 = (num2.charAt(j)-'0')*ch1+flag;
                cur.append(ch2%10);
                flag = ch2/10;
            }
            if(flag!=0){
                cur.append(flag);
            }
            flag = 0;
            cur.reverse();
            //System.out.println(cur);
            ans.append(0);
            if(ans.toString().equals("")){
                ans.append(cur);
                continue;
            }
            int m = Math.min(ans.length(),cur.length());
            int n = Math.max(ans.length(),cur.length());
            int k=1;
            StringBuilder s =new StringBuilder();
            while(k<=n){
                //System.out.println("k="+k);
                if(k<=m){
                    int ch3 = ans.charAt(ans.length()-k)-'0'+cur.charAt(cur.length()-k)-'0'+flag;
                    flag = ch3/10;
                    s.append(ch3%10);
                    k++;
                }
                else{
                    if(ans.length()>cur.length()){
                        int ch3 = ans.charAt(ans.length()-k)-'0'+flag;
                        flag = ch3/10;
                        s.append(ch3%10);
                        k++;
                    }
                    else{
                        int ch3 = cur.charAt(cur.length()-k)-'0'+flag;
                        flag = ch3/10;
                        s.append(ch3%10);
                        k++;
                    }
                }
            }
            if(flag!=0){
                s.append(flag);
            }
            ans = s.reverse();
        }
        while(ans.charAt(0)=='0'){
            ans.deleteCharAt(0);
        }
        return ans.toString();
    }
}
