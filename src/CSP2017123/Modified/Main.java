package CSP2017123.Modified;


import java.beans.IntrospectionException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int nowComRank = 0;
    static List<Answer> answers = new ArrayList<>();
    static Calendar start = Calendar.getInstance(), end = Calendar.getInstance();
    static Date startD,endD;
    static Map<String,Integer> map = new HashMap<>();
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
    static Set<Integer> sminute = new HashSet<Integer>();
    static Set<Integer> shour = new HashSet<Integer>();
    static Set<Integer> sweekday = new HashSet<Integer>();
    static Set<Integer> sday = new HashSet<Integer>();
    static Set<Integer> smonth = new HashSet<Integer>();
    static List<Answer> list = new ArrayList<Answer>();
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.trim().split(" ");
        int n = Integer.parseInt(parts[0]);
        startD = simpleDateFormat.parse(parts[1]);
        endD = simpleDateFormat.parse(parts[2]);
        start.setTime(startD);
        end.setTime(endD);
        for(int i=0;i<n;i++){
            line = scanner.nextLine();
            answers.addAll(getAns(line));
        }
        Collections.sort(answers);
        for(Answer answer:answers){
            System.out.println(answer);
        }
    }
    static List<Answer> getAns(String line){
        list.clear();
        nowComRank++;
        String[] parts = line.split(" ");
        pushSet(parts);
        Calendar oneday = Calendar.getInstance();
        for(int year = start.get(Calendar.YEAR);year <= end.get(Calendar.MONTH);year++){
            for(int month:smonth){
                for(int day: sday){
                    for(int hour:shour){
                        for(int minute:sminute){
                            oneday.set(year,month-1,day,hour,minute,0);
                            if(sweekday.contains(oneday.get(Calendar.DAY_OF_WEEK))){
                                if(!oneday.before(start)&&oneday.before(end)){
                                    StringBuilder s = new StringBuilder();
                                    s.append(year).append(month).append(day).append(hour).append(minute);
                                    Answer answer = new Answer(s.toString(),parts[5],nowComRank);
                                    list.add(answer);
                                }
                            }
                        }
                    }
                }
            }
        }
        for(Answer answer:list){
            System.out.println(answer);
        }
        return list;
    }



    static void pushSet(String[] parts){
        sminute.clear();
        sday.clear();
        shour.clear();
        sweekday.clear();
        smonth.clear();

        sminute.addAll(singleRange(0,59,parts[0]));
        shour.addAll(singleRange(0,23,parts[1]));
        sday.addAll(singleRange(1,31,parts[2]));
        smonth.addAll(singleRange(1,12,parts[3]));
        sweekday.addAll(singleRange(0,6,parts[4]));
    }

    static Set<Integer> singleRange(int start,int end,String line){
        Set<Integer> ansSet = new HashSet<>();
        if(line.equals("*")){
            for(int i=start;i<=end;i++){
                ansSet.add(i);
            }
        }
        String[] temp = line.split(",");
        for(int i=0;i<temp.length;i++){
            if(temp[i].contains("-")){
                String[] temp1 = temp[i].split("-");
                int star = Integer.parseInt(temp1[0]),en = Integer.parseInt(temp1[1]);
                for(int j=star;j<=en;j++){
                    ansSet.add(j);
                }
            }else{
                ansSet.add(ConvToNum(temp[i]));
            }
        }
        return ansSet;
    }

    static int ConvToNum(String cur){
        if(onlyContainsNum(cur)){
            return Integer.parseInt(cur);
        }
        cur = cur.toLowerCase();
        switch (cur){
            case "sun": return 0;
            case "mon":
            case "jan":return 1;
            case "tue":
            case "feb":return 2;
            case "wed":
            case "mar":return 3;
            case "thu":
            case "apr":return 4;
            case "fri":
            case "may":return 5;
            case "sat":
            case "jun":return 6;
            case "jul":return 7;
            case "aug":return 8;
            case "sep":return 9;
            case "oct":return 10;
            case "nov":return 11;
            case "dec":return 12;
        }
        return -1;
    }

    static boolean onlyContainsNum(String cur){
        String regex = "\\d*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cur);
        return matcher.matches();
    }
    static class Answer implements Comparable<Answer>{
        String time,command;
        int comRank;

        Answer(String time,String command,int comRank){
            this.time = time;
            this.command = command;
            this.comRank = comRank;
        }
        @Override
        public String toString() {
            return time+" "+command;
        }

        @Override
        public int compareTo(Answer o) {
            if(time.equals(o.time)){
                return comRank-o.comRank;
            }
            return time.compareTo(o.time);
        }
    }

}
