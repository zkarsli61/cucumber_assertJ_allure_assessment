package com.esprow.step_definitions;

import com.esprow.managers.PageObjectManager;
import com.esprow.pages.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
//import org.junit.Assert;

public class BaseStepDefinitions {

    PageObjectManager pg = new PageObjectManager();
    BasePage basePage = pg.getBasePage();

    @Given("user is on the application")
    public void user_is_on_the_app() {
        basePage.startApp();
    }

//    @And("close the application")
//    public void close_the_app() {
//        basePage.closeApp();
//    }

    @Given("click on sign in")
    public void user_click_sign_in() {
        basePage.clickOnSigIn();
    }

    @And("click on {string} link")
    public void clickthelink(String link) {
        switch (link) {
            case "Subscription":
                basePage.clickOnSubscriptionLink();
                break;
            case "User Profile":
                basePage.hoverOverUserProfileLink();
                break;
            case "Logout":
                basePage.clickOnLogOut();
                break;
        }
    }

    @Then("verify that {string} page is displayed")
    public void verify_page(String page) {

        boolean actual=true;

        switch (page) {
            case "Login":
                actual = pg.getLoginPage().verifyPage();
                break;
            case "Main":
                actual = basePage.isUserProfileChanged();
                break;
            case "Subscription":
                actual = pg.getSubscriptionPage().verifyPage();
                break;
            case "Cart":
                actual = pg.getCartPage().verifyPage();
                break;
            case "Review":
                actual = pg.getReviewPage().verifyPage();
                break;
            case "Message Success":
                actual = pg.getMessageSuccessPage().verifyPage();
                break;
            case "Exchanges":
                actual = pg.getExchangesPage().verifyPage();
                break;
        }
        Assertions.assertThat(actual)
                .as("verifying "+page+" page failed")
                .isTrue();

    }
}