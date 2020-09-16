package CSP2019032;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String line = scanner.nextLine().trim();
            Stack<Integer> stack = new Stack<>();
            for(int j=0;j<line.length();j++){
                char c = line.charAt(j);
                if(c>='1'&&c<='9'){
                    stack.add(c-'0');
                }
                else{
                    if(c=='/'){
                        int top = stack.pop();
                        j++;
                        int next = line.charAt(j)-'0';
                        stack.add(top/next);
                    }
                    else if(c=='x'){
                        int top = stack.pop();
                        j++;
                        int next = line.charAt(j)-'0';
                        stack.add(top*next);
                    }
                    else if(c=='-'){
                        j++;
                        int next = -(line.charAt(j)-'0');
                        stack.add(next);
                    }
                    else if(c=='+'){
                        continue;
                    }
                }
            }
            int ans = 0;
            while(!stack.isEmpty()){
                ans+=stack.pop();
            }
            //System.out.println(ans);
            if(ans==24){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }
}
