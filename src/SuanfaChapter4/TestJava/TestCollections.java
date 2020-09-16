package SuanfaChapter4.TestJava;

import java.util.*;
public class TestCollections {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Integer[] a = new Integer[10];
        a = list.toArray(a);
        System.out.println(Arrays.toString(a));

    }
}
