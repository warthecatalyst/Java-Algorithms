package SolveP3.CSP201509;

import java.util.*;
import java.util.regex.Pattern;

public class Main {
    static int N,K;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        N = scanner.nextInt(); K = scanner.nextInt();
        String[] text = new String[N];
        Map<String,List<Integer>> map = new HashMap<>();
        Map<String,Boolean> isSubstituted = new HashMap<>();
        scanner.nextLine();
        for(int i=0;i<N;i++){
            String line = scanner.nextLine().trim();
            text[i] = line;
            if(line.contains("{{ ")&&line.contains(" }}")){
                String cur = line.substring(line.indexOf("{{ ")+3,line.indexOf(" }}"));
                //System.out.println(cur);
                List<Integer> list = map.getOrDefault(cur,new ArrayList<>());
                list.add(i);
                map.put(cur,list);
                isSubstituted.put(cur,false);
            }
        }
        for(int i=0;i<K;i++){
            String line = scanner.nextLine().trim();
            String var =  line.substring(0,line.indexOf("\"")-1);String val = line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""));
            //System.out.println(var+" "+val);
            if(!map.containsKey(var)){
                continue;
            }
            isSubstituted.put(var,true);
            List<Integer> list = map.get(var);
            String regex = "{{ "+var+" }}";
            for(int cc:list){
                text[cc] = text[cc].replace(regex,val);
            }
        }
        for(Map.Entry<String,Boolean> entry:isSubstituted.entrySet()){
            if(!entry.getValue()){  //未被替换过
                List<Integer> list = map.get(entry.getKey());
                String regex = "\\{\\{ "+entry.getKey()+" }}";
                for(int cc:list){
                    text[cc] = text[cc].replaceAll(regex,"");
                }
            }
        }
        for(int i=0;i<N;i++){
            System.out.println(text[i]);
        }
    }
}
