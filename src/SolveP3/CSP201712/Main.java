package SolveP3.CSP201712;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main{
    public static void main(String args[]) {
        new Solve().solve();
    }
}

class Solve {
    public int nowComRank = 0;
    public Calendar startCal = Calendar.getInstance(), endCal = Calendar.getInstance();
    public List<Answer> ans = new ArrayList<Answer>();
    public Date startDat, endDat;
    public Map<String, Integer> map = new HashMap<String, Integer>();

    public SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

    public void solve() {
        Scanner sc = new Scanner(System.in);
        // 输入
        String line = sc.nextLine();
        String temp[] = line.split(" ");
        int n = Integer.parseInt(temp[0]);

        try {
            startDat = sdf.parse(temp[1]);
            endDat = sdf.parse(temp[2]);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        startCal.setTime(startDat);
        endCal.setTime(endDat);

        for (int lineindex = 0; lineindex < n; lineindex++) {
            // <minutes> <hours> <day of month> <month> <day of week> <command>
            line = sc.nextLine();
            ans.addAll(generateAns(line));
        }

        Collections.sort(ans);
        // 输出
        for (Answer answer : ans) {
            System.out.println(answer);
        }

    }

    public void pushSet(String[] line) {
        // <minutes> <hours> <day of month> <month> <day of week> <command>
        sminute.clear();
        shour.clear();
        sday.clear();
        smonth.clear();
        sweekday.clear();

        sminute.addAll(singleRange(0, 59, line[0]));
        shour.addAll(singleRange(0, 23, line[1]));
        sday.addAll(singleRange(1, 31, line[2]));
        smonth.addAll(singleRange(1, 12, line[3]));
        sweekday.addAll(singleRange(0, 6, line[4]));

    }

    Set<Integer> sminute = new HashSet<Integer>();
    Set<Integer> shour = new HashSet<Integer>();
    Set<Integer> sweekday = new HashSet<Integer>();
    Set<Integer> sday = new HashSet<Integer>();
    Set<Integer> smonth = new HashSet<Integer>();
    List<Answer> list = new ArrayList<Answer>();
    int rank = 0;

    public List<Answer> generateAns(String line) {
        ++rank;
        list.clear();
        nowComRank++;
        // System.out.println("生成Ans：start" + line);
        String[] temp = line.split(" ");
//        for (int i = 0; i < 5; i++) {
//            temp[i] = temp[i].toLowerCase();
//        }
        pushSet(temp);
        // System.out.println("push完毕" + sminute.size() + " " + shour.size() + "
        // " + sweekday.size() + " " + sday.size()
        // + " " + smonth.size());
        // System.out.println(startCal.get(Calendar.YEAR));
        Calendar oneday = Calendar.getInstance();
        oneday.setFirstDayOfWeek(Calendar.SUNDAY);
        for (int iyear = startCal.get(Calendar.YEAR); iyear < endCal.get(Calendar.YEAR) + 1; iyear++) {
            for (int imonth : smonth) {
                for (int iday : sday) {
                    for (int ihour : shour) {
                        for (int iminute : sminute) {

                            oneday.set(iyear, imonth - 1, iday, ihour, iminute, 0);
                            oneday.getTime();
                            // Calendar检查年月日后对不合理的日期会顺延，这是是为了确认日期合理
                            if (oneday.get(Calendar.MONTH) + 1 == imonth && oneday.get(Calendar.YEAR) == iyear
                                    && oneday.get(Calendar.DATE) == iday
                                    && sweekday.contains(oneday.get(Calendar.DAY_OF_WEEK) - 1)
                                    && !oneday.before(startCal) && oneday.before(endCal)) {

                                list.add(new Answer(sdf.format(new Date(oneday.getTimeInMillis())), temp[5], rank));
                                // System.out.println("list add" +
                                // Csp2017123.sdf.format(oneday.getTime()));
                            }
                        }
                    }
                }

            }
        }
        return list;
    }

    public Set<Integer> singleRange(int start, int end, String line) {
        Set<Integer> ansSet = new HashSet<Integer>();
        if (line.equals("*")) {
            for (int i = start; i < end + 1; i++) {
                ansSet.add(i);
            }
            return ansSet;
        }
        line += ",";
        String[] temp = line.split(",");
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("-")) {
                String temp1[] = temp[i].split("-");
                for (int index1 = convToNum(temp1[0]); index1 < convToNum(temp1[1]) + 1; index1++) {
                    ansSet.add(index1);
                }
            } else
                ansSet.add(convToNum(temp[i]));
        }
        return ansSet;

    }

    //我最刚开始是用Map写的，但因为没有清楚前导零只得了40分，用parseInt则没有这个问题
    public int convToNum(String line) {
        switch (line.toLowerCase()) {
            case "sun":
                return 0;
            case "mon":
            case "jan":
                return 1;
            case "tue":
            case "feb":
                return 2;
            case "wed":
            case "mar":
                return 3;
            case "thu":
            case "apr":
                return 4;
            case "fri":
            case "may":
                return 5;
            case "sat":
            case "jun":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dec":
                return 12;
            default:
                try {
                    return Integer.parseInt(line);
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }
                break;
        }
        return -1;
    }

    // return map.get(line);

}

class Answer implements Comparable<Answer> {

    public int comRank;

    public String time;
    public String command;

    public Answer(String time, String command, int comrank) {
        this.time = time;
        this.command = command;
        this.comRank = comrank;
    }

    public String toString() {
        return this.time + " " + command;
    }

    @Override
    public int compareTo(Answer o) {
        int res = this.time.compareTo(o.time);
        return res == 0 ? this.comRank - o.comRank : res;
    }

}
