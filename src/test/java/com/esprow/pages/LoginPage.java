package com.esprow.pages;


import com.esprow.utilities.BrowserUtils;
import org.openqa.selenium.By;


//everything that is in common among pages
//can go here
//for example top menu elements don't belong to specific page
//top menu appears on every single page
//so we can keep them here
public class LoginPage extends BasePage {

    By email = By.xpath("//input[@name='email']");
    By password = By.xpath("//input[@name='password']");
    By sigInButton = By.xpath("//button[text()='Sign In']");

    public boolean verifyPage() {
        return BrowserUtils.verifyPage(sigInButton,"Login");
    }
    public BasePage login(String user, String pwd) {
        BrowserUtils.waitForPresence(email).sendKeys(user);
        BrowserUtils.waitForPresence(password).sendKeys(pwd);
        BrowserUtils.waitForClickability(sigInButton).click();
        return pg.getBasePage();
    }

}
