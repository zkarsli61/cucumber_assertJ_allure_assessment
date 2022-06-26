package com.esprow.pages;

import com.esprow.utilities.BrowserUtils;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By proceedToCheckOut = By.xpath("//button[@data-cb-id='cart_submit']");

    public boolean verifyPage() {
        return BrowserUtils.verifyPage(proceedToCheckOut,"Cart");

    }

    public ReviewPage clickProceedToCheckOut() {
        BrowserUtils.waitForClickability(proceedToCheckOut).click();
        return pg.getReviewPage();
    }


}
