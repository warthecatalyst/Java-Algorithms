package Problem20;

import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.isValid("{[]}"));
    }
}

class Solution{
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char ch:s.toCharArray()){
            switch (ch){
                case '(':stack.add('(');break;
                case ')':{
                    if(stack.isEmpty()){
                        return false;
                    }
                    if (stack.peek()=='('){
                        stack.pop();
                        break;
                    }
                    else{
                        return false;
                    }
                }
                case '[':stack.add('[');break;
                case ']':{
                    if(stack.isEmpty()){
                        return false;
                    }
                    if(stack.peek()=='['){
                        stack.pop();
                        break;
                    }
                    else{
                        return false;
                    }
                }
                case '{':stack.add('{');break;
                case '}':{
                    if(stack.isEmpty()){
                        return false;
                    }
                    if(stack.peek()=='{'){
                        stack.pop();
                        break;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
