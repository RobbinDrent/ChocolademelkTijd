package app;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public final class TimeChecker {

    private static TimeChecker checkerInstance;

    private TimeChecker() {
        // Private constructor
    }

    public static TimeChecker instance() {
        if (null == checkerInstance) {
            checkerInstance = new TimeChecker();
        }
        return checkerInstance;
    }

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    public String getCurrentDay() {
        DayOfWeek day = getCurrentTime().getDayOfWeek();
        return day.getDisplayName(TextStyle.FULL, Locale.GERMANY);
    }
}
