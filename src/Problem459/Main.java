package Problem459;

public class Main {
    public static void main(String[] args){
        String s = "aba";
        Solution solution = new Solution();
        System.out.println(solution.repeatedSubstringPattern(s));
    }
}

class Solution{
    public boolean repeatedSubstringPattern(String s) {
        int m = s.length();
loop:        for(int i=1;i<=m/2;i++){
            //System.out.println("i="+i);
            if(m%i==0){
                for(int j=0;j<i;j++){
                    int k = j+i;
                    char first = s.charAt(j);
                    while(k<m){
                        if(s.charAt(k)==first){
                            k+=i;
                        }
                        else{
                            continue loop;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
