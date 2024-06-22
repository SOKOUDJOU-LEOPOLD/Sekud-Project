package util;

import java.util.GregorianCalendar;

/**
 *
 * @author Roger NDJEUMOU
 * @date May 14, 2022
 */
public class MyDate {

    private static final String[] MONTHS = {"January", "February",
        "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"
    };
    private static final String[] DAYS = {"Invalid","Sunday", "Monday",
        "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };
    int year;
    int month;
    int day;
    GregorianCalendar calendar;

    public MyDate() {
        calendar = new GregorianCalendar();
        setDate(calendar.getTimeInMillis());
    }

    public MyDate(long elapsedTime) {
        calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        setDate(elapsedTime);
    }

    public MyDate(int year, int month, int day) {
        calendar = new GregorianCalendar(year, month, month);
        //setDate(calendar.getTimeInMillis());
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setDate(long elapsedTime) {
        this.calendar.setTimeInMillis(elapsedTime);
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public String getWeekDay() {
        return DAYS[calendar.get(GregorianCalendar.DAY_OF_WEEK)];
    }

    @Override
    public String toString() {
        return String.format("%s, %s %02d, %04d",
                DAYS[calendar.get(GregorianCalendar.DAY_OF_WEEK)],
                MONTHS[calendar.get(GregorianCalendar.MONTH)],
                this.day, this.year);
    }
}
