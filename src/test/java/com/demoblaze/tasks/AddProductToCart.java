package com.demoblaze.tasks;

import com.microsoft.playwright.Page;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;

import static com.demoblaze.ui.DemoBlazePage.*;

public class AddProductToCart implements Task {

    private final String productName;

    public AddProductToCart(String productName) {
        this.productName = productName;
    }

    @Override
    @Step("{0} adds #productName to the cart")
    public <T extends Actor> void performAs(T actor) {

        Page page = BrowseTheWebWithPlaywright
                .as(actor)
                .getCurrentPage();

        page.locator(
                "a:has-text('" + productName + "')"
        ).click();

        page.onceDialog(dialog -> dialog.accept());

        page.locator(
                "a:has-text('Add to cart')"
        ).click();

        page.locator("#nava").click();
    }

    public static AddProductToCart called(String productName) {
        return new AddProductToCart(productName);
    }
}