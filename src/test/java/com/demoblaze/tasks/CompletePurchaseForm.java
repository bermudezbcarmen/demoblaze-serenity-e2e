package com.demoblaze.tasks;

import com.demoblaze.models.PurchaseData;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.interactions.Click;
import net.serenitybdd.screenplay.playwright.interactions.Enter;

import static com.demoblaze.ui.CartPage.PLACE_ORDER;
import static com.demoblaze.ui.CheckoutPage.*;

public class CompletePurchaseForm implements Task {

    private final PurchaseData data;

    public CompletePurchaseForm(PurchaseData data) {
        this.data = data;
    }

    @Override
    @Step("{0} completes the purchase form")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(PLACE_ORDER),

                Enter.theValue(data.getName()).into(NAME),
                Enter.theValue(data.getCountry()).into(COUNTRY),
                Enter.theValue(data.getCity()).into(CITY),
                Enter.theValue(data.getCard()).into(CARD),
                Enter.theValue(data.getMonth()).into(MONTH),
                Enter.theValue(data.getYear()).into(YEAR)
        );
    }

    public static CompletePurchaseForm with(PurchaseData data) {
        return new CompletePurchaseForm(data);
    }
}