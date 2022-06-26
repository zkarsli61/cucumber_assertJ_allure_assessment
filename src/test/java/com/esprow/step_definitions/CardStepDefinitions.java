package com.esprow.step_definitions;

import com.esprow.managers.PageObjectManager;
import com.esprow.pages.CartPage;
import io.cucumber.java.en.Then;

public class CardStepDefinitions {

    CartPage cartPage = new PageObjectManager().getCartPage();

    @Then("click Proceed to Checkout button")
    public void userClickOnAddExchangeButtton() {
        cartPage.clickProceedToCheckOut();

    }

}
