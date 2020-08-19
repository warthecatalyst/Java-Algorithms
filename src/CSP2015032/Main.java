package CSP2015032;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            int cur = scanner.nextInt();
            int cal = map.getOrDefault(cur,0);
            map.put(cur,++cal);
        }
        Comparator<Map.Entry<Integer,Integer>> valcmp = new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if(!o2.getValue().equals(o1.getValue())){
                    return o2.getValue()-o1.getValue();
                }
                else return o1.getKey()-o2.getKey();
            }
        };
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
        list.sort(valcmp);
        for (Map.Entry<Integer, Integer> integerIntegerEntry : list) {
            System.out.println(integerIntegerEntry.getKey() + " " + integerIntegerEntry.getValue());
        }
    }
}
