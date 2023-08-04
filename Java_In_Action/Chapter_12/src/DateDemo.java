import sun.security.rsa.RSAUtil;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class DateDemo {

    public static void main(String[] args) {

        // 使用 LocalDate 和 LocalTime
        LocalDate date = LocalDate.of(2018, 4, 14);
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int i = date.lengthOfMonth();   // 31
        boolean leapYear = date.isLeapYear();

        // 获取当前日期
        LocalDate today = LocalDate.now();

        int years = date.get(ChronoField.YEAR);
        int months = date.get(ChronoField.MONTH_OF_YEAR);
        date.get(ChronoField.DAY_OF_MONTH);

        // 使用localTime  读取值
        LocalDate time = LocalDate.of(13, 1, 20);
        LocalDate date1 = LocalDate.parse("2014-03-18");

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        System.out.println(dt1);
        LocalDate localDate = dt1.toLocalDate();
        LocalTime localTime = dt1.toLocalTime();

        // 判断时间间隔
        Period tendays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
        System.out.println(tendays);


        //12.2 操纵、解析和格式化日期
        LocalDate dates = LocalDate.of(2014, 3, 18);
        LocalDate d1 = dates.withYear(2011);
        System.out.println(d1);
        LocalDate d2 = dates.withDayOfMonth(25);
        System.out.println(d2);

        // with 是修改，plus 是增加
        LocalDate d3 = dates.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println(d3);


        // 还可以使用 plus方法；
        LocalDate d4 = dates.plusWeeks(1);
        System.out.println(d4);
        dates.plus(6, ChronoUnit.MONTHS);

        // 使用 TemporalAdjuster
        LocalDate d5 = dates.with(nextOrSame(DayOfWeek.SUNDAY));


    }
}
