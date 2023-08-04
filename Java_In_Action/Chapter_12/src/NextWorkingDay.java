import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        // 读取当前日志
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1; // 正常情况 + 1
        // 周五加三
        if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
        else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;   //周六加2
        Temporal plus = temporal.plus(dayToAdd, ChronoUnit.DAYS);   // 增加天数
        return plus;
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            return temporal.plus((dow == DayOfWeek.FRIDAY ? 3 : dow == DayOfWeek.SATURDAY ? 2 : 1),ChronoUnit.DAYS);
        });


        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(t -> {
            DayOfWeek week = DayOfWeek.of(t.get(ChronoField.DAY_OF_WEEK));
            return t.plus((week == DayOfWeek.FRIDAY ? 3 : week == DayOfWeek.SATURDAY ? 2 : 1), ChronoUnit.DAYS);
        });
        date = date.with(nextWorkingDay);

        System.out.println(date);
        date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String s2 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s2);

        // 自定义格式
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = date.format(pattern);
        System.out.println(s3);


    }
}
