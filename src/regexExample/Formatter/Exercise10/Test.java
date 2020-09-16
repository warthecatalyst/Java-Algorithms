package regexExample.Formatter.Exercise10;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String tes = "Java now has regular expressions";
        List<String> list = new ArrayList<>();
        list.add("^Java");
        list.add("\\Breg.*");
        list.add("n.w\\s+h(a|i)s");
        list.add("s?");
        list.add("s*");
        list.add("s+");
        list.add("s{4}");
        list.add("s{1}.");
        list.add("s{0,3}");
        for(String s:list){
            Pattern pattern = Pattern.compile(s);
            System.out.println(pattern);
            Matcher m = pattern.matcher(tes);
            System.out.println(m.find());
        }
    }
}
