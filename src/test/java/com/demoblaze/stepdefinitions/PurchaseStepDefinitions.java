package com.demoblaze.stepdefinitions;

import com.demoblaze.models.PurchaseData;
import com.demoblaze.tasks.AddProductToCart;
import com.demoblaze.tasks.CompletePurchaseForm;
import com.demoblaze.tasks.FinishPurchase;
import com.demoblaze.tasks.OpenCart;
import com.demoblaze.tasks.OpenDemoBlaze;
import com.demoblaze.ui.CartPage;
import com.demoblaze.ui.CheckoutPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.playwright.abilities.BrowseTheWebWithPlaywright;
import net.serenitybdd.screenplay.playwright.assertions.Ensure;

import java.util.List;
import java.util.Map;

public class PurchaseStepDefinitions {

    private Actor customer;

    @Before
    public void setUp() {
        customer = Actor.named("Customer");
        customer.can(
            BrowseTheWebWithPlaywright.usingTheDefaultConfiguration()
        );
    }

    @Given("the customer opens DemoBlaze")
    public void theCustomerOpensDemoBlaze() {

        customer.attemptsTo(
            OpenDemoBlaze.homePage()
        );
    }

    @When("the customer adds the following products to the cart:")
    public void theCustomerAddsProductsToTheCart(DataTable dataTable) {

        List<String> products = dataTable.asList();

        for (String product : products) {
            customer.attemptsTo(
                AddProductToCart.called(product)
            );
        }
    }

    @When("the customer opens the shopping cart")
    public void theCustomerOpensTheShoppingCart() {

        customer.attemptsTo(
            OpenCart.page()
        );
    }

    @Then("the following products should be visible in the cart:")
    public void theProductsShouldBeVisibleInTheCart(DataTable dataTable) {

        List<String> products = dataTable.asList();

        for (String product : products) {

            customer.attemptsTo(
                Ensure.that(
                    CartPage.productCalled(product)
                ).isVisible()
            );
        }
    }

    @When("the customer completes the purchase form with:")
    public void theCustomerCompletesThePurchaseForm(DataTable dataTable) {

        Map<String, String> data =
                dataTable.asMap(String.class, String.class);

        PurchaseData purchaseData =
                new PurchaseData(
                    data.get("name"),
                    data.get("country"),
                    data.get("city"),
                    data.get("card"),
                    data.get("month"),
                    data.get("year")
                );

        customer.attemptsTo(
            CompletePurchaseForm.with(purchaseData)
        );
    }

    @When("the customer confirms the purchase")
    public void theCustomerConfirmsThePurchase() {

        customer.attemptsTo(
            FinishPurchase.order()
        );
    }

    @Then("the purchase should be completed successfully")
    public void thePurchaseShouldBeCompletedSuccessfully() {

        customer.attemptsTo(
            Ensure.that(
                CheckoutPage.SUCCESS_MESSAGE
            ).hasText("Thank you for your purchase!")
        );
    }
}