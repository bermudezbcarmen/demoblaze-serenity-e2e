package com.demoblaze.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.interactions.Open;

public class OpenDemoBlaze implements Task {

    @Override
    @Step("{0} opens DemoBlaze")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Open.url("https://www.demoblaze.com/")
        );
    }

    public static OpenDemoBlaze homePage() {
        return new OpenDemoBlaze();
    }
}