package com.demoblaze.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.interactions.Click;

import static com.demoblaze.ui.DemoBlazePage.CART;

public class OpenCart implements Task {

    @Override
    @Step("{0} opens the shopping cart")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(CART)
        );
    }

    public static OpenCart page() {
        return new OpenCart();
    }
}