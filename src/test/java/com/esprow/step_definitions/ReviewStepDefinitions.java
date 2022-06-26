package com.esprow.step_definitions;

import com.esprow.managers.PageObjectManager;
import com.esprow.pages.CartPage;
import com.esprow.pages.ReviewPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ReviewStepDefinitions {

    ReviewPage reviewPage = new PageObjectManager().getReviewPage();

    @Then("check I agree")
    public void check_I_agree() {
        reviewPage.clickAgreeCBox();
    }

    @And("click Subscribe button")
    public void click_Subscribe_button() {
        reviewPage.clickSubcribeButton();
    }

}
