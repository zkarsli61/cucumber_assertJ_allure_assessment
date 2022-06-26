package com.esprow.pages;

import com.esprow.utilities.BrowserUtils;
import org.openqa.selenium.By;

public class MessageSuccessPage extends BasePage {

    private final By goToExchangesLink = By.linkText("Go to exchanges");

    public boolean verifyPage() {
        return BrowserUtils.verifyPage(goToExchangesLink,"Message Success");
    }

    public ExchangesPage clickGoToExchanges() {
        BrowserUtils.waitForClickability(goToExchangesLink).click();
        return pg.getExchangesPage();
    }


}
