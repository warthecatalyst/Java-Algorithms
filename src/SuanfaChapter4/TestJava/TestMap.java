package SuanfaChapter4.TestJava;

import java.util.*;
import java.util.regex.Pattern;

public class TestMap {  //测试一下
    static Map<Integer,Map<Integer, List<Integer>[]>> actionmap = new TreeMap<>();
    public static void main(String[] args){
        Pattern pattern =  Pattern.compile("CS_ACM_1701");
        System.out.println(pattern);
    }
}
