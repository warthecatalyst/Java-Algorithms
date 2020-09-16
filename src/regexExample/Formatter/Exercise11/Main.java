package regexExample.Formatter.Exercise11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String regex = "(\\?i)((^aeiou)|(\\s+[aeiou]))\\w+?[aeiou]\\b";
        Pattern pattern = Pattern.compile(regex);
        System.out.println(pattern);
        String test = "Arline ate eight apples and one orange while Anita hadn't any";
        Matcher matcher = pattern.matcher(test);
        if(matcher.matches()){
            System.out.println("find");
        }
    }
}
