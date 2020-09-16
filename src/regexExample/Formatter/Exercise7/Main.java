package regexExample.Formatter.Exercise7;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {    //检查一个句子是否以大写字母开头，以句号结尾
        String pattern = "[A-Z].*\\.";
        String test1 = "An apple.";
        String test2 = "An apple";
        String test3 = "an Apple.";
        String test4 = "an apple";
        System.out.println(test1.matches(pattern));
        System.out.println(test2.matches(pattern));
        System.out.println(test3.matches(pattern));
        System.out.println(test4.matches(pattern));
    }
}
