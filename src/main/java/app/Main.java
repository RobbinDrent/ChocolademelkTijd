package app;

import io.javalin.Javalin;
import io.javalin.http.Context;


import java.util.Collections;
import java.util.Map;

public class Main {

    TimeChecker checker;
    Javalin app;

    private Main() {
        checker = TimeChecker.instance();
        initJavalin();
        System.out.println(checker.getCurrentDate());
        System.out.println(checker.getCurrentDay());
    }

    public static void main(String[] args) {
        new Main();
    }

    private void initJavalin() {
        Javalin app = Javalin.create().start(7070);
        app.get("/", this::renderPage);
    }

    private void renderPage(Context ctx) {
        Page page = new Page();
        page.name = "Henk";
        page.aantalKonijnen = 3;
        page.dag = checker.getCurrentDay();
        page.chocoladeMelkTijd = checker.getChocola();
        page.chocoTime = checker.getChocoladeMelkTime();;
        if (checker.isChocolademelkTijd()) {

        }

        ctx.render("hello.jte", Collections.singletonMap("page", page));
    }
}