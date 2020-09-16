package CSP2018093;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int n,k;
    static Node[] text;
    static int[] depth;
    static List<Integer> ans = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); k = scanner.nextInt();
        scanner.nextLine();
        text = new Node[n+1];
        depth = new int[n+1];
        for(int i=1;i<=n;i++){
            String line = scanner.nextLine().trim();
            int depthcount = 0;
            int labelbegin = 0,labelend = 0,idbegin = 0;
            for(;labelbegin<line.length();labelbegin++){
                if(line.charAt(labelbegin)=='.'){
                    depthcount++;
                }
                else{
                    break;
                }
            }
            depth[i] = depthcount/2;
            text[i] = new Node();
            if(depth[i]==0){
                text[i].parent = i; //父节点就为自己,子节点无需添加
            }else{
                int j = i-1;
                for(;j>=1;j--){
                    if (depth[j]==depth[i]-1){
                        break;
                    }
                }
                text[i].parent = j;
                text[j].children.add(i);
            }
            //System.out.println("depth[i] = "+depth[i]);
            for(int kk = depth[i]*2;kk<line.length();kk++){
                char c = line.charAt(kk);
                if(c=='#'){
                    labelend = kk-1; //前面有个空格
                    idbegin = kk;
                    break;
                }
            }
            if(labelend==0){
                text[i].label = line.substring(labelbegin).toLowerCase();
                text[i].id = "";
            }else{
                text[i].label = line.substring(labelbegin,labelend);
                text[i].id = line.substring(idbegin);
            }
            //System.out.println(text[i]);
        }
//        for(int i=1;i<text.length;i++){
//            System.out.println(text[i]);
//        }
        for(int i=0;i<k;i++){
            String line = scanner.nextLine();
            String[] parts = line.trim().split(" ");
            ans = new ArrayList<>();
            List<Integer> tob = new ArrayList<>();
            for(int j=1;j<=n;j++){
                tob.add(j);
            }
            process(parts,0,tob);
            System.out.print(ans.size());
            for (Integer an : ans) {
                System.out.print(" " + an);
            }
            System.out.println();
        }
    }
    static void process(String[] parts,int cur,List<Integer> tob){
        if(cur==parts.length-1){
            String curs = parts[cur].trim();
            for(int linecal:tob){
                if(curs.contains("#")&&text[linecal].containsID(curs)){
                    ans.add(linecal);
                }
                else if(text[linecal].containsLabel(curs.toLowerCase())){
                    ans.add(linecal);
                }
            }
            return;
        }
        String curs = parts[cur].trim();
        //System.out.println(curs);
        List<Integer> next = new ArrayList<>();
        for(int linecal:tob){
            //System.out.println("label = "+text[linecal].label+", id = "+text[linecal].id);
            if(curs.contains("#")&&text[linecal].containsID(curs)){
                next.addAll(text[linecal].children);
            }else if(text[linecal].containsLabel(curs.toLowerCase())){
                next.addAll(text[linecal].children);
            }
        }
        process(parts,cur+1,next);
    }
    static class Node{
        int parent;
        List<Integer> children;
        String label;
        String id;
        Node(){
            children = new ArrayList<>();
        }
        boolean containsLabel(String s){
            return label.equals(s);
        }
        boolean containsID(String s){
            return id.equals(s);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "parent=" + parent +
                    ", children=" + children +
                    ", label='" + label + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
