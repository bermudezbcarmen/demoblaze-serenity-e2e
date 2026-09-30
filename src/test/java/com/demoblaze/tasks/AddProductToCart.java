package com.demoblaze.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.interactions.Click;

import static com.demoblaze.ui.DemoBlazePage.ADD_TO_CART;
import static com.demoblaze.ui.DemoBlazePage.HOME;
import static com.demoblaze.ui.DemoBlazePage.productCalled;

public class AddProductToCart implements Task {

    private final String productName;

    public AddProductToCart(String productName) {
        this.productName = productName;
    }

    @Override
    @Step("{0} adds #productName to the cart")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(productCalled(productName)),
                Click.on(ADD_TO_CART),
                Click.on(HOME)
        );
    }

    public static AddProductToCart called(String productName) {
        return new AddProductToCart(productName);
    }
}