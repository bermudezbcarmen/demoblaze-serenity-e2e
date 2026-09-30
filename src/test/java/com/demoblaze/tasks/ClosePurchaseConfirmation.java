package com.demoblaze.tasks;

import com.demoblaze.ui.CheckoutPage;
import com.microsoft.playwright.Page;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.screenplay.playwright.interactions.Click;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ClosePurchaseConfirmation implements Task {

    @Override
    @Step("{0} closes the purchase confirmation and returns to the catalog")
    public <T extends Actor> void performAs(T actor) {
        Page page = BrowseTheWebWithPlaywright.as(actor).getCurrentPage();
        actor.attemptsTo(Click.on(CheckoutPage.OK));

        // OK navigates to the catalog. Wait for that transition before checking the dialog.
        assertThat(page).hasURL("https://www.demoblaze.com/index.html");
        assertThat(CheckoutPage.SUCCESS_MESSAGE.resolveFor(page)).isHidden();
        assertThat(CheckoutPage.OK.resolveFor(page)).isHidden();
        assertThat(page.locator("#tbodyid .card-title a").first()).isVisible();
    }

    public static ClosePurchaseConfirmation andReturnToCatalog() {
        return new ClosePurchaseConfirmation();
    }
}
