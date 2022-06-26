package com.esprow.pages;

import com.esprow.utilities.BrowserUtils;
import org.openqa.selenium.By;

public class ReviewPage extends BasePage {

    private final By subcribeButton = By.xpath("//button[@data-cb-id='review_submit']");
    private final By agreeCBox = By.id("tos_agreed");

    public boolean verifyPage() {
        return BrowserUtils.verifyPage(agreeCBox,"Review");
    }
    public ReviewPage clickAgreeCBox() {
        BrowserUtils.waitForClickability(agreeCBox).click();
        return this;
    }

    public MessageSuccessPage clickSubcribeButton() {
        BrowserUtils.waitForClickability(subcribeButton).click();
        return pg.getMessageSuccessPage();
    }



}
