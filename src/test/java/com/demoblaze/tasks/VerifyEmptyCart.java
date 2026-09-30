package com.demoblaze.tasks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.screenplay.playwright.interactions.Click;

import static com.demoblaze.ui.DemoBlazePage.HOME;

public class VerifyEmptyCart implements Task {

    @Override
    @Step("{0} verifies that the new session starts with an empty cart")
    public <T extends Actor> void performAs(T actor) {
        Page page = BrowseTheWebWithPlaywright.as(actor).getCurrentPage();
        // An empty table alone can pass before the asynchronous cart load finishes.
        Response response = page.waitForResponse(
                candidate -> candidate.url().equals("https://api.demoblaze.com/viewcart")
                        && "POST".equals(candidate.request().method()),
                () -> actor.attemptsTo(OpenCart.page()));
        if (!response.ok()) {
            throw new AssertionError("Could not load the initial cart: HTTP " + response.status());
        }
        JsonObject cart = JsonParser.parseString(response.text()).getAsJsonObject();
        if (!cart.has("Items") || !cart.get("Items").isJsonArray()
                || !cart.getAsJsonArray("Items").isEmpty()) {
            throw new AssertionError("Expected an empty cart for the new browser session: " + cart);
        }
        actor.attemptsTo(Click.on(HOME));
    }

    public static VerifyEmptyCart beforeShopping() {
        return new VerifyEmptyCart();
    }
}
