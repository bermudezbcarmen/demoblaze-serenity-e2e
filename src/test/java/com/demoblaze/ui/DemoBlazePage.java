package com.demoblaze.ui;

import net.serenitybdd.screenplay.playwright.Target;

public class DemoBlazePage {

    public static Target productCalled(String productName) {
        return Target.the("product " + productName)
                .locatedBy("a:has-text('" + productName + "')");
    }

    public static final Target ADD_TO_CART =
            Target.the("Add to cart link")
                    .locatedBy("a:has-text('Add to cart')");

    public static final Target HOME =
            Target.the("Home menu")
                    .locatedBy("#nava");

    public static final Target CART =
            Target.the("Cart menu")
                    .locatedBy("#cartur");

    private DemoBlazePage() {
    }
}