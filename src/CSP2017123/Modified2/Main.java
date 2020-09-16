package CSP2017123.Modified2;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int[] dayOfMonth = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
    static int comRank = 0;
    static List<Answer> answers = new ArrayList<>();    //存储结果
    static Calendar start = Calendar.getInstance(),end = Calendar.getInstance();
    static Date startD,endD;
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
    static Set<Integer> smonth = new HashSet<>();
    static Set<Integer> sday = new HashSet<>();
    static Set<Integer> sweekday = new HashSet<>();
    static Set<Integer> shour = new HashSet<>();
    static Set<Integer> sminute = new HashSet<>();
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.trim().split(" ");
        int n = Integer.parseInt(parts[0]);
        startD = simpleDateFormat.parse(parts[1]);
        endD = simpleDateFormat.parse(parts[2]);
        start.setTime(startD); end.setTime(endD);
        for(int i=0;i<n;i++){
            line = scanner.nextLine().trim();
            parts = line.split(" ");
            processLine(parts);
        }
        Collections.sort(answers);
        for(Answer ans:answers){
            System.out.println(ans);
        }
    }
    static boolean isLeapYear(int year){
        return year%400==0||(year%4==0&&year%100!=0);
    }
    static void processLine(String[] parts){
        comRank++;
        sminute.clear();
        shour.clear();
        sday.clear();
        smonth.clear();
        sweekday.clear();

        sminute.addAll(process(0,59,parts[0]));
        shour.addAll(process(0,23,parts[1]));
        sday.addAll(process(1,31,parts[2]));
        smonth.addAll(process(1,12,parts[3]));
        sweekday.addAll(process(0,6,parts[4]));
        Calendar cal = Calendar.getInstance();
        for(int year = start.get(Calendar.YEAR);year<=end.get(Calendar.YEAR);year++){
            for(int month:smonth){
                for(int day:sday){
                    for(int hour:shour){
                        for(int minute:sminute){
                            cal.set(year,month-1,day,hour,minute,0);
                            if(cal.get(Calendar.YEAR)==year&&cal.get(Calendar.MONTH)==month-1&&cal.get(Calendar.DATE)==day
                                    &&sweekday.contains(cal.get(Calendar.DAY_OF_WEEK)-1) &&!cal.before(start)&&cal.before(end)){
                                Answer answer = new Answer(simpleDateFormat.format(new Date(cal.getTimeInMillis())),parts[5],comRank);
                                answers.add(answer);
                            }
                        }
                    }
                }
            }
        }
//        for(Answer ans:list){
//            System.out.println(ans);
//        }
    }
    static Set<Integer> process(int start,int end,String s){
        Set<Integer> current = new HashSet<>();
        if(s.equals("*")){
            for(int i=start;i<=end;i++){
                current.add(i);
            }
            return current;
        }
        String[] temp = s.split(",");
        for(int i=0;i<temp.length;i++){
            if(temp[i].contains("-")){
                String[] temp1 = temp[i].split("-");
                int st = returnNum(temp1[0]),en = returnNum(temp1[1]);
                for(int j = st;j<=en;j++){
                    current.add(j);
                }
            }else{
                current.add(returnNum(temp[i]));
            }
        }
        return current;
    }

    static int returnNum(String cur){
        if(onlyContainsNum(cur)){
            return Integer.parseInt(cur);
        }
        switch (cur){
            case "Sun": return 0;
            case "Mon":
            case "Jan":return 1;
            case "Tue":
            case "Feb":return 2;
            case "Wed":
            case "Mar":return 3;
            case "Thu":
            case "Apr":return 4;
            case "Fri":
            case "May":return 5;
            case "Sat":
            case "Jun":return 6;
            case "Jul":return 7;
            case "Aug":return 8;
            case "Sep":return 9;
            case "Oct":return 10;
            case "Nov":return 11;
            case "Dec":return 12;
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
            int res = this.time.compareTo(o.time);
            return res == 0 ? this.comRank - o.comRank : res;
        }
    }
}
