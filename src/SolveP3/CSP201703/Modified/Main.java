package SolveP3.CSP201703.Modified;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
public class Main {
    static List<String> answer = new ArrayList<>();
    static Pattern emphaPattern;
    static Pattern linkPattern;
    public static void main(String[] args) {
        String emphaRegex = "([^_]*)_([^_]*)_([^_]*)";
        String linkRegex = "([^\\[]*)\\[([^]]*)\\]\\(([^\\)]*)\\)([^\\[]*)";
        emphaPattern = Pattern.compile(emphaRegex);
        linkPattern = Pattern.compile(linkRegex);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("-1")){
                break;
            }
            processLine(line);
        }
        if(!answer.isEmpty()){
            String lastline = answer.get(answer.size()-1);
            if(lastline.contains("<li>")){
                answer.add("</ul>");
            }else if(lastline.length()!=0&&!lastline.contains("<h")&&!lastline.contains("</ul>")&&!lastline.contains("<li>")){
                if(!lastline.endsWith("</p>")){
                    answer.set(answer.size()-1,lastline+"</p>");
                }
            }
        }
        for(String ans:answer){
            if(ans.trim().length()==0) continue;
            System.out.println(ans);
        }
    }
    static void processLine(String line){
        if(line.trim().length()==0){
            if(answer.isEmpty()||answer.get(answer.size()-1).trim().length()==0){
                answer.add("");
            }else{
                String lastline = answer.get(answer.size()-1);
                if(lastline.length()!=0&&!lastline.contains("<li>")&&!lastline.contains("<h")&&!lastline.contains("</ul>")&&!lastline.endsWith("</p>")){
                    lastline += "</p>";
                    answer.set(answer.size()-1,lastline);
                }else if(lastline.contains("<li>")){
                    answer.add("</ul>");
                }
                answer.add("");
            }
            //System.out.println(answer);
            return;
        }
        if(line.contains("#")){
            int i=0;
            for(;i<line.length();i++){
                if(line.charAt(i)!='#'){
                    break;
                }
            }
            int count = i;
            line = "<h"+count+">"+processOline(line.substring(i+1))+"</h"+count+">";
            answer.add(line);
        }else if(line.contains("*")){
            if(answer.isEmpty()||answer.get(answer.size()-1).equals("")){
                answer.add("<ul>");
            }
            int count = 1;
            while(line.charAt(count)==' '){
                count++;
            }
            answer.add("<li>"+processOline(line.substring(count))+"</li>");
        }else{
            StringBuilder sb =new StringBuilder();
            if(answer.isEmpty()||answer.get(answer.size()-1).equals("")){
                sb.append("<p>");
            }
            sb.append(processOline(line));
            answer.add(sb.toString());
        }
    }

    static String processOline(String line){    //行内结构
        if(!line.contains("_")&&!line.contains("[")){   //无强调也无超链接
            return line;
        }
        //替换所有的强调结构
        Matcher emphaMatch = emphaPattern.matcher(line);
        String temp = "";
        while (emphaMatch.find()){
            temp += emphaMatch.group(1)+"<em>"+emphaMatch.group(2)+"</em>"+emphaMatch.group(3);
        }
        if(temp.length()!=0){
            line = temp;
        }
        //System.out.println(line);
        //替换所有的超链接结构
        Matcher linkMatch = linkPattern.matcher(line);
        temp = "";
        while (linkMatch.find()){
            temp += linkMatch.group(1)+"<a href=\""+linkMatch.group(3)+"\">"+linkMatch.group(2)+"</a>"+linkMatch.group(4);
        }
        if(temp.length()!=0){
            line = temp;
        }
        //System.out.println(temp);
        return line;
    }
}
