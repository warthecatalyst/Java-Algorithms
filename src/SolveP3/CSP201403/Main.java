package SolveP3.CSP201403;

import java.util.*;

public class Main { //100分
    static int n;
    static Map<String,Boolean> hasParameter = new HashMap<>();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String mlh = scanner.next();
        for(int i=0;i<mlh.length();i++){
            char c = mlh.charAt(i);
            if(i!=mlh.length()-1&&mlh.charAt(i+1)==':'){
                i++;
                hasParameter.put('-'+String.valueOf(c),true);
            }else{
                hasParameter.put('-'+String.valueOf(c),false);
            }
        }
//        for(Map.Entry<String,Boolean> entry:hasParameter.entrySet()){
//            System.out.println(entry.getKey()+"-->"+entry.getValue());
//        }
        n = scanner.nextInt();
        scanner.nextLine();
        for(int i=1;i<=n;i++){
            String line = scanner.nextLine();
            String[] parts = line.trim().split(" ");
            List<String> cur = new ArrayList<>();
            Map<String,String> map = new HashMap<>();
            for(int j = 1;j<parts.length;j++){
                //System.out.println(parts[j]);
                if(!hasParameter.containsKey(parts[j])){
                    break;
                }
                if(hasParameter.get(parts[j])&&j!=parts.length-1){
                    map.put(parts[j],parts[j+1]);
                    j++;
                } else if(!hasParameter.get(parts[j])){
                    map.put(parts[j],"");
                }
            }
            for(Map.Entry<String,String> entry:map.entrySet()){
                if(entry.getValue().equals("")){
                    cur.add(entry.getKey());
                }else{
                    cur.add(entry.getKey()+" "+entry.getValue());
                }
            }
            Collections.sort(cur);
            System.out.print("Case "+i+": ");
            for(String s:cur){
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
}
