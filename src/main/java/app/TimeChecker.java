package app;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class TimeChecker {

    private final Map<String, String> DAYS_OF_THE_WEEK = Map.of(
            "Montag", "Maandag",
            "Dienstag", "Dinsdag",
            "Mittwoch", "Woensdag",
            "Donnerstag",  "Donderdag",
            "Freitag", "Vrijdag",
            "Samstag", "Zaterdag",
            "Sonntag", "Zondag"
    );
//    private final String[] WORKING_DAYS = {"Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag"};
    private final List<String> WEEKEND = List.of("Zaterdag", "Zondag");
    private final LocalTime WORK_START = LocalTime.parse("09:00:00");
    private final LocalTime EARLY_TIME = LocalTime.parse("13:00:00");
    private final LocalTime END_TIME = LocalTime.parse("17:00:00");

    private static TimeChecker checkerInstance;

    private String dayOfWeek;
    private String chocola;
    private String chocoladeMelkTime;

    private TimeChecker() {
        // Private constructor
    }

    public static TimeChecker instance() {
        if (null == checkerInstance) {
            checkerInstance = new TimeChecker();
        }
        return checkerInstance;
    }

    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

    public String getCurrentDay() {
        DayOfWeek day = getCurrentDate().getDayOfWeek();
        var germanDay = day.getDisplayName(TextStyle.FULL, Locale.GERMANY);
        return DAYS_OF_THE_WEEK.get(germanDay);
    }

    private boolean checkTime() {
       LocalTime now = LocalTime.now();

       if (now.isAfter(EARLY_TIME) && now.isBefore(END_TIME)) {
           chocola = "Ja, geniet ervan!";
           return true;
       } else if (now.isBefore(WORK_START)) {
           chocola =  "Ga eerst maar eens aan het werk!";
       } else if (now.isAfter(WORK_START) && now.isBefore(EARLY_TIME)) {
           chocola = "Na de pauze pas!";
       } else if (now.isAfter(END_TIME)) {
           chocola = "Nee, je moet naar huis!";
       } else {
           chocola = "Er is iets aparts gebeurt!";
       }
       return false;
    }

    private boolean todayIsWeekend() {
        return WEEKEND.contains(getCurrentDay());
    }


    public boolean isChocolademelkTijd() {
        if (!todayIsWeekend() && checkTime()) {
            this.chocoladeMelkTime = "CHOCOLADEMELKTIJD!!!";
            return true;
        } else if (todayIsWeekend()) {
            this.chocola = "Het is weekend, doe wat je niet laten kan!";
        }
        this.chocoladeMelkTime = "Helaas";
         return false;
    }

    public String getChocola() {
        return this.chocola;
    }

    public String getChocoladeMelkTime() {
        return this.chocoladeMelkTime;
    }
}
