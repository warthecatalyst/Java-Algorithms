package CSP2015093;

import java.util.*;

public class Main {
    public static void main(String[] args){
        int m,n;
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        String[] htmltext = new String[m+1];
        Map<String, List<Integer>> map = new HashMap<>();
        scanner.nextLine();
        for(int i=1;i<=m;i++){
            htmltext[i] = scanner.nextLine();
            if(htmltext[i].contains("{{ ")&&htmltext[i].contains(" }}")){
                //System.out.println(htmltext[i]);
                int index1 = htmltext[i].indexOf("{{");
                int index2 = htmltext[i].indexOf("}}");
                String curvar =htmltext[i].substring(index1+3,index2-1);
                curvar = curvar.trim();
                //System.out.println(curvar);
                if((!Character.isLetter(curvar.charAt(0))&&curvar.charAt(0)!='_')||curvar.length()>16){
                    //System.out.println("not a variable");
                    continue;
                }
                if(!map.containsKey(curvar)){
                    List<Integer> arr = new ArrayList<>();
                    arr.add(i);
                    map.put(curvar,arr);
                }
                else{
                    List<Integer> arr = map.get(curvar);
                    arr.add(i);
                    map.put(curvar,arr);
                }
            }
        }
//        for(Map.Entry<String,List<Integer>> entry:map.entrySet()){
//                    System.out.print(entry.getKey()+" ");
//                    System.out.println(entry.getValue());
//        }
        for(int i=1;i<=n;i++){
            String nextLine = scanner.nextLine();
            String curvar = nextLine.split(" ")[0].trim();
            //System.out.println(curvar);
            String varValue =nextLine.substring(nextLine.indexOf('\"')+1,nextLine.length()-1);
            //System.out.println(varValue);
            List<Integer> arr = map.get(curvar);
            //System.out.println(arr);
            for (int curline : arr) {
                String temp = "\\{\\{ " + curvar + " }}";
                htmltext[curline] = htmltext[curline].replaceAll(temp, varValue);
                //System.out.println(htmltext[curline]);
            }
            map.remove(curvar);
        }
        //System.out.println("varcount = "+varcount);
        for (Map.Entry<String,List<Integer>> entry:map.entrySet()){
            String curvar = entry.getKey();
            List<Integer> arr = entry.getValue();
            for (int curline : arr) {
                String temp = "\\{\\{ " + curvar + " }}";
                htmltext[curline] = htmltext[curline].replaceAll(temp, "");
            }
        }
        for(int i=1;i<=m;i++){
            System.out.println(htmltext[i]);
        }
    }
}
