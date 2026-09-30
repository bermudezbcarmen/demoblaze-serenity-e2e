package com.demoblaze.tasks;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
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

        actor.attemptsTo(Click.on(productCalled(productName)));

        Page page = BrowseTheWebWithPlaywright.as(actor).getCurrentPage();
        AtomicReference<String> message = new AtomicReference<>();
        Consumer<Dialog> handleConfirmation = dialog -> {
            String text = dialog.message();
            dialog.accept();
            message.set(text);
        };

        // Subscribe before clicking so a fast response cannot be missed.
        page.onDialog(handleConfirmation);
        try {
            actor.attemptsTo(Click.on(ADD_TO_CART));
            page.waitForCondition(() -> message.get() != null);
            if (!"Product added".equals(message.get()) && !"Product added.".equals(message.get())) {
                throw new AssertionError("Could not add " + productName + ": " + message.get());
            }
        } finally {
            page.offDialog(handleConfirmation);
        }

        actor.attemptsTo(Click.on(HOME));
    }

    public static AddProductToCart called(String productName) {
        return new AddProductToCart(productName);
    }
}
