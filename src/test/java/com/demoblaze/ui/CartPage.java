package com.demoblaze.ui;

import net.serenitybdd.screenplay.playwright.Target;

public class CartPage {

    public static final Target CART_ROWS =
            Target.the("products in cart")
                    .locatedBy("#tbodyid tr");

    public static Target productCalled(String productName) {
        return Target.the("product " + productName + " in cart")
                .locatedBy("#tbodyid tr:has-text('" + productName + "')");
    }

    public static final Target PLACE_ORDER =
            Target.the("Place Order button")
                    .locatedBy("button:has-text('Place Order')");

    private CartPage() {
    }
}