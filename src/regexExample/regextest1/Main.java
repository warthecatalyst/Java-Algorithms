package regexExample.regextest1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String s="\\";
        String regex="\\\\\\\\";
        Pattern p=Pattern.compile(regex);
        System.out.println(p);
        Matcher m=p.matcher(s);
        if(m.matches()){
            System.out.println("匹配");
        }
        else{
            System.out.println("不匹配");
        }
    }
}
