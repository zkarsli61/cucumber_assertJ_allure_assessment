package com.esprow.step_definitions;

import com.esprow.managers.PageObjectManager;
import com.esprow.pages.MessageSuccessPage;
import com.esprow.pages.ReviewPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MesssageSucccessStepDefinitions {

    MessageSuccessPage messageSuccessPage = new PageObjectManager().getMessageSuccessPage();

    @And("click Go to exchanges button")
    public void click_Subscribe_button() {
        messageSuccessPage.clickGoToExchanges();
    }

}
