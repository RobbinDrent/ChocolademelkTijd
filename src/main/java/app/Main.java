package app;

import io.javalin.Javalin;
import io.javalin.http.Context;


import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public class Main {

    TimeChecker checker;
    Javalin app;

    private Map<String, String> daysOfWeek = Map.of(
            "Montag", "Maandag",
            "Dienstag", "Dinsdag",
            "Mittwoch", "Woensdag",
            "Donnerstag",  "Donderdag",
            "Freitag", "Vrijdag",
            "Sammtag", "Zaterdag",
            "Sonntag", "Zondag"
            );

    Locale locale;

    public Main() {
        checker = TimeChecker.instance();
        app = initJavalin();
        System.out.println(checker.getCurrentTime());
        System.out.println(daysOfWeek.get(checker.getCurrentDay()));
    }

    public static void main(String[] args) {
        new Main();
    }

    private static Javalin initJavalin() {
        return Javalin.create().start(7070);
    }

    private static void renderPage(Context ctx) {
        Page page = new Page();
        page.name = "Henk";
        page.aantalKonijnen = 3;
        ctx.render("hello.jte", Collections.singletonMap("page", page));
    }
}