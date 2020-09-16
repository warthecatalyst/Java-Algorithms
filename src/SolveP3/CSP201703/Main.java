package SolveP3.CSP201703;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static List<String> answer = new ArrayList<>();

    static Pattern emphaPattern;
    static Pattern linkPattern;
    public static void main(String[] args) {
        String emphaRegex = "([^_]*)_([^_]*)_([^_]*)";
        String linkRegex = "([^\\[])*\\[([^]]*)]\\(([^)]*)\\)";
        emphaPattern = Pattern.compile(emphaRegex);
        linkPattern = Pattern.compile(linkRegex);
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("-1")){
                break;
            }
            processLine(line);
        }
        if(!answer.isEmpty()){
            String ans = answer.get(answer.size()-1);
            if(ans.contains("<li>")){
                answer.add("</ul>");
            }
            else if(ans.trim().length()!=0&&!ans.contains("</ul>")&&!ans.contains("<h")&&!ans.contains("<li>")&&!ans.contains("</p>")){
                ans += "</p>";
                answer.set(answer.size()-1,ans);
            }
        }
        for(String ans:answer){
            if(ans.equals(""))continue;
            System.out.println(ans);
        }

    }
    static void processLine(String line){
        if(line.contains("#")){
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0,i = 0;
            for(;i<line.length();i++){
                if(line.charAt(i)=='#'){
                    count++;
                }else{
                    break;
                }
            }
            //System.out.println(i);
            stringBuilder.append("<h").append(count).append(">").append(processOline(line.substring(i+1))).append("</h"+count+">");
            //System.out.println(stringBuilder);
            answer.add(stringBuilder.toString());
        }else{
            if(line.trim().length()==0){
                if(answer.isEmpty()||answer.get(answer.size()-1).contains("<h")){
                    answer.add("");
                }
                else if(answer.get(answer.size()-1).contains("<li>")){
                    answer.add("</ul>");
                    answer.add("");
                }else if(!answer.get(answer.size()-1).contains("<ul>")){
                    String ans = answer.get(answer.size()-1);
                    ans+="</p>";
                    answer.set(answer.size()-1,ans);
                    answer.add("");
                }
            }
            else if(line.contains("*")){
                if(answer.isEmpty()||answer.get(answer.size()-1).equals("")){
                    answer.add("<ul>");
                }
                int index = 1;
                while(line.charAt(index)==' '){
                    index++;
                }
                //System.out.println("<li>"+processOline(line.substring(index))+"</li>");
                answer.add("<li>"+processOline(line.substring(index))+"</li>");
            }else{
                StringBuilder sb = new StringBuilder();
                if(answer.isEmpty()||answer.get(answer.size()-1).equals("")){
                    sb.append("<p>").append(processOline(line));
                }else{
                    sb.append(processOline(line));
                }
                //System.out.println(sb);
                answer.add(sb.toString());
            }
        }
    }
    static String processOline(String line){    //处理行内的强调或者超链接
        if(!line.contains("_")&&!line.contains("[")){
            return line;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int fuh = 0;
        String link = "";
        for(int i=0;i<line.length();i++){
            if(line.charAt(i)=='['){    //超链接
                stringBuilder.append("<a href=\"");
                i++;
                int end = i;
                while(line.charAt(end)!=']'){
                    end++;
                }
                link = line.substring(i,end);
                i = end+1;
            }else if(line.charAt(i)==')'){
                stringBuilder.append("\">").append(link).append("</a>");
            }else if(line.charAt(i)=='_'){
                if(fuh==0){
                    stringBuilder.append("<em>");
                }else{
                    stringBuilder.append("</em>");
                }
                fuh = 1-fuh;
            }else{
                stringBuilder.append(line.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}
