package regexExample.Formatter.Test2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String regex = "[.]+";
        String test = "....";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(test);
        System.out.println(matcher.matches());
    }
}
