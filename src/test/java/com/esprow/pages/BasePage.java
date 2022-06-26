package com.esprow.pages;


import com.esprow.managers.Driver;
import com.esprow.managers.PageObjectManager;
import com.esprow.utilities.BrowserUtils;
import com.esprow.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    PageObjectManager pg = new PageObjectManager();
    WebDriver driver = Driver.getDriver();
    By sigInLink = By.xpath("//a[@href='/auth/sign-in']");
    By userProfileLink = By.cssSelector(".sc-fzXfPg");
    By subscriptionLink = By.xpath("//div[contains(@class,'sc-fzXfPH')]//a[text()='Subscription']");
    By logoutLink = By.linkText("Logout");
    By all = By.id("root");

    public WebElement getUserProfileLink() {
        return BrowserUtils.waitForPresence(userProfileLink);
    }

    public BasePage startApp() {
        driver.get(ConfigurationReader.getProperty("url"));
        return this;
    }

    public BasePage closeApp() {
        driver.close();
        return this;
    }

    public BasePage clickOnLogOut() {
        BrowserUtils.waitForClickability(logoutLink).click();
        return this;
    }

    public LoginPage clickOnSigIn() {
        driver.findElement(sigInLink).click();
        return pg.getLoginPage();
    }

    public boolean isUserProfileChanged() {
        return BrowserUtils.waitForUrlChanged("sign-in");
    }


    public BasePage hoverOverUserProfileLink() {
        BrowserUtils.hoverOver(getUserProfileLink());
//        BrowserUtils.waitForClickability(userProfileLink).click();
//        WebElement link = BrowserUtils.waitForPresence(userProfileLink);
//        BrowserUtils.hoverOver(link);
        return this;
    }

    public SubscriptionPage clickOnSubscriptionLink() {
        BrowserUtils.waitForClickability(subscriptionLink).click();
        BrowserUtils.hoverOver(driver.findElement(all));
        return pg.getSubscriptionPage();
    }
}
