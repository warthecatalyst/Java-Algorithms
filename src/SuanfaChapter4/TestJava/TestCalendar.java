package SuanfaChapter4.TestJava;

import java.util.Calendar;

public class TestCalendar {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(2020,Calendar.FEBRUARY,31,0,0);
        System.out.println(cal);
        cal.add(Calendar.DATE,1);
        System.out.println(cal);
    }
}
