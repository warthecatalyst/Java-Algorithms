package CSP2017123;

import java.util.*;

public class Main {
    static Map<String,String[]> map = new HashMap<>();
    static Map<String,Integer> dayss = new HashMap<>();
    static Map<String,Integer> monthss = new HashMap<>();
    public static void main(String[] args){
        dayss.put("Sun",0);
        dayss.put("Mon",1);
        dayss.put("Tue",2);
        dayss.put("Wed",3);
        dayss.put("Thu",4);
        dayss.put("Fri",5);
        dayss.put("Sat",6);
        monthss.put("Jan",1);
        monthss.put("Feb",2);
        monthss.put("Mar",3);
        monthss.put("Apr",4);
        monthss.put("May",5);
        monthss.put("Jun",6);
        monthss.put("Jul",7);
        monthss.put("Aug",8);
        monthss.put("Sep",9);
        monthss.put("Oct",10);
        monthss.put("Nov",11);
        monthss.put("Dec",12);
        Scanner scanner = new Scanner(System.in);
        Calendar cal = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        int n = scanner.nextInt();
        String s = scanner.next(), t = scanner.next();
        //System.out.println(s.substring(8,10));
        cal.set(Calendar.YEAR,Integer.parseInt(s.substring(0,4)));
        cal.set(Calendar.MONTH,Integer.parseInt(s.substring(4,6))-1);   //MONTH取值范围0-11
        cal.set(Calendar.DATE,Integer.parseInt(s.substring(6,8)));
        cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(s.substring(8,10)));
        cal.set(Calendar.MINUTE,Integer.parseInt(s.substring(10)));
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        //System.out.println(cal);
        end.set(Calendar.YEAR,Integer.parseInt(t.substring(0,4)));
        end.set(Calendar.MONTH,Integer.parseInt(t.substring(4,6))-1);
        end.set(Calendar.DATE,Integer.parseInt(t.substring(6,8)));
        end.set(Calendar.HOUR_OF_DAY,Integer.parseInt(t.substring(8,10)));
        end.set(Calendar.MINUTE,Integer.parseInt(t.substring(10)));
        end.set(Calendar.SECOND,0);
        end.set(Calendar.MILLISECOND,0);
        //System.out.println(cal.before(end));
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String line = scanner.nextLine();
            String[] cur = line.trim().split(" ");
            String command = cur[cur.length-1];
            String[] cur1 = new String[5];
            System.arraycopy(cur, 0, cur1, 0, 5);
            map.put(command,cur1);
        }
//        for(Map.Entry<String,String[]> entry:map.entrySet()) {
//            String command = entry.getKey();
//            String[] cur = entry.getValue();
//            System.out.println(command+"-->"+ Arrays.toString(cur));
//        }
        while(cal.before(end)){
            //每个都检查一遍
            for(Map.Entry<String,String[]> entry:map.entrySet()){
                String command = entry.getKey();
                String[] cur = entry.getValue();
                if(check(cal,cur)){
                    String soo = String.valueOf(cal.get(Calendar.YEAR)) + (cal.get(Calendar.MONTH)+1) + cal.get(Calendar.DAY_OF_MONTH);
                    if(cal.get(Calendar.HOUR_OF_DAY)<10){
                        soo += "0"+cal.get(Calendar.HOUR_OF_DAY);
                    }else{
                        soo += cal.get(Calendar.HOUR_OF_DAY);
                    }
                    if(cal.get(Calendar.MINUTE)<10){
                        soo += "0"+cal.get(Calendar.MINUTE);
                    }else{
                        soo += cal.get(Calendar.MINUTE);
                    }
                    System.out.println(soo +" "+command);
                }
            }
            cal.add(Calendar.MINUTE,1);
        }
    }
    static boolean check(Calendar cal,String[] cur){
        return checkminutes(cal,cur[0],0)&&checkminutes(cal,cur[1],1)
                &&checkminutes(cal,cur[2],2)&&checkMonth(cal,cur[3])
                &&checkdayofweek(cal,cur[4]);
    }
    static boolean checkminutes(Calendar cal,String minute,int index){
        minute = minute.trim();
        if(minute.equals("*")){
            return true;
        }
        if(minute.contains(",")){   //先分割,号
            String[] mn = minute.split(",");
            for (String s : mn) {
                if (checkminutes(cal,s,index)) {
                    return true;
                }
            }
            return false;
        }
        int mi = 0;
        if(index == 0){
            mi = cal.get(Calendar.MINUTE);
        }else if(index == 1){
            mi = cal.get(Calendar.HOUR_OF_DAY);
        }else if (index == 2){
            mi = cal.get(Calendar.DAY_OF_MONTH);
        }else{
            mi = cal.get(Calendar.MONTH);
        }
        if(minute.contains("-")){
            String[] kk = minute.split("-");
            int begin = Integer.parseInt(kk[0]);
            int end = Integer.parseInt(kk[1]);
            return mi>=begin&&mi<=end;
        }
        return mi==Integer.parseInt(minute);
    }
    static boolean checkMonth(Calendar cal,String month){
        month = month.trim();
        if(month.equals("*")){
            return true;
        }
        if (month.contains(",")){
            String[] months = month.split(",");
            for(String s :months){
                if(checkMonth(cal,s)){
                    return true;
                }
            }
            return false;
        }
        int curmonth = cal.get(Calendar.MONTH);
        if(month.contains("-")){
            String[] s1 = month.split("-");
            int begin = 0 ,end = 0;
            if(s1[0].length()==3){
                begin = monthss.getOrDefault(s1[0],0);
            }else{
                begin = Integer.parseInt(s1[0]);
            }if(s1[1].length()==3){
                end = monthss.getOrDefault(s1[1],0);
            }else{
                end = Integer.parseInt(s1[1]);
            }
            return curmonth>=begin&&curmonth<=end;
        }
        int li = 0;
        if(month.length()==3){
            li = monthss.getOrDefault(month,0);
        }else{
            li = Integer.parseInt(month);
        }
        return curmonth==li;
    }
    static boolean checkdayofweek(Calendar cal,String day){
        day = day.trim();
        if(day.equals("*")){
            return true;
        }
        if(day.contains(",")){
            String[] days = day.split(",");
            for(String s:days){
                if(checkdayofweek(cal,s)){
                    return true;
                }
            }
            return false;
        }
        int curday = cal.get(Calendar.DAY_OF_WEEK)-1;
        //System.out.println("curday = "+ curday);
        if(day.contains("-")){
            String[] s1 = day.split("-");
            int begin = 0,end = 0;
            if(s1[0].length()==3){  //字母为3位
                begin = dayss.getOrDefault(s1[0].trim(),0);
            }else{
                begin = Integer.parseInt(s1[0]);
            }
            if(s1[1].length()==3){
                end = dayss.getOrDefault(s1[1].trim(),0);
            }else{
                end = Integer.parseInt(s1[1]);
            }
            return curday>=begin&&curday<=end;
        }
        int li = 0;
        if(day.length()==3){
            li = dayss.get(day);
        }else{
            li = Integer.parseInt(day);
        }
        //System.out.println("li = "+li);
        return curday==li;
    }
}
