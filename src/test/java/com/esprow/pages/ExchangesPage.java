package com.esprow.pages;

import com.esprow.utilities.BrowserUtils;
import org.openqa.selenium.By;

public class ExchangesPage extends BasePage {

    private final By search = By.xpath("//input[@placeholder='Search']");

    public boolean verifyPage() {
        return BrowserUtils.verifyPage(search,"Exchange");
    }
}
