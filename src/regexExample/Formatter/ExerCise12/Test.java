package regexExample.Formatter.ExerCise12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    static String Java = "Java now has regular expressions";
    public static void main(String[] args) {    //找出所有以非大写字母开头的单词并输出
        Pattern pattern = Pattern.compile("(\\b[a-z][a-zA-z]*\\b)");
        System.out.println(pattern);
        Matcher matcher = pattern.matcher(Java);
        while(matcher.find()){
            for(int j=1;j<=matcher.groupCount();j++){
                System.out.println(matcher.group(j));
            }
        }
    }
}
