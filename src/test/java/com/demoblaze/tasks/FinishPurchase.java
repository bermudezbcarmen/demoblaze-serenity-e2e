package com.demoblaze.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.interactions.Click;

import static com.demoblaze.ui.CheckoutPage.PURCHASE;

public class FinishPurchase implements Task {

    @Override
    @Step("{0} confirms the purchase")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(PURCHASE)
        );
    }

    public static FinishPurchase order() {
        return new FinishPurchase();
    }
}