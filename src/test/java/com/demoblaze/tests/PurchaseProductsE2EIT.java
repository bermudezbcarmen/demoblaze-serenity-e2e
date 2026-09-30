package com.demoblaze.tests;

import com.demoblaze.models.PurchaseData;
import com.demoblaze.tasks.*;
import com.demoblaze.ui.CartPage;
import com.demoblaze.ui.CheckoutPage;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.screenplay.playwright.assertions.Ensure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
@DisplayName("DemoBlaze purchase flow")
class PurchaseProductsE2EIT {

    private Actor customer;

    @BeforeEach
    void prepareActor() {

        customer = Actor.named("Customer");

        customer.can(
                BrowseTheWebWithPlaywright
                        .usingTheDefaultConfiguration()
        );
    }

    @Test
    @DisplayName("Customer purchases two products successfully")
    void shouldPurchaseTwoProductsSuccessfully() {

        PurchaseData purchaseData =
                new PurchaseData(
                        "Wilson Alzate",
                        "Colombia",
                        "Medellin",
                        "4111111111111111",
                        "12",
                        "2027"
                );

        customer.attemptsTo(

                OpenDemoBlaze.homePage(),
                VerifyEmptyCart.beforeShopping(),

                AddProductToCart.called(
                        "Samsung galaxy s6"
                ),

                AddProductToCart.called(
                        "Nokia lumia 1520"
                ),

                OpenCart.page(),

                Ensure.that(
                        CartPage.productCalled(
                                "Samsung galaxy s6"
                        )
                ).isVisible(),

                Ensure.that(
                        CartPage.productCalled(
                                "Nokia lumia 1520"
                        )
                ).isVisible(),

                CompletePurchaseForm.with(
                        purchaseData
                ),

                FinishPurchase.order(),

                Ensure.that(
                        CheckoutPage.SUCCESS_MESSAGE
                ).hasText(
                        "Thank you for your purchase!"
                )
        );
    }
}
