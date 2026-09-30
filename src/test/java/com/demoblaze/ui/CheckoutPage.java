package com.demoblaze.ui;

import net.serenitybdd.screenplay.playwright.Target;

public class CheckoutPage {

    public static final Target NAME =
            Target.the("customer name")
                    .locatedBy("#name");

    public static final Target COUNTRY =
            Target.the("customer country")
                    .locatedBy("#country");

    public static final Target CITY =
            Target.the("customer city")
                    .locatedBy("#city");

    public static final Target CARD =
            Target.the("credit card")
                    .locatedBy("#card");

    public static final Target MONTH =
            Target.the("expiration month")
                    .locatedBy("#month");

    public static final Target YEAR =
            Target.the("expiration year")
                    .locatedBy("#year");

    public static final Target PURCHASE =
            Target.the("Purchase button")
                    .locatedBy("button:has-text('Purchase')");

    public static final Target SUCCESS_MESSAGE =
            Target.the("purchase confirmation message")
                    .locatedBy(".sweet-alert h2");

    public static final Target CONFIRMATION_DETAILS =
            Target.the("purchase confirmation details")
                    .locatedBy(".sweet-alert p");

    public static final Target OK =
            Target.the("confirmation OK button")
                    .locatedBy(".sweet-alert button.confirm");

    private CheckoutPage() {
    }
}