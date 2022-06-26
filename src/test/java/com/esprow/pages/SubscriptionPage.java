package com.esprow.pages;

import com.esprow.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubscriptionPage extends BasePage {

    private final By addExchangeButton = By.xpath("//button[text()='Add Exchange']");
    private final By discardAllChangesButton = By.xpath("//button[text()='Discard All Changes']");
    private final By deleteSelectedSubsciptions = By.xpath("//*[@data-tip='Delete selected subscriptions']");
    private final By selectAllRecords = By.xpath("//input[@class='sc-LzLrl idPkBn']");
    private final By subtractSessionsButton = By.xpath("//div[@class='sc-LzLwq WefCk'][1]");
    private final By addSessionsButton = By.xpath("//div[@class='sc-LzLwq WefCk'][2]");
    private final By monthlySubsDetails = By.xpath("//span[contains(text(),'Monthly')]//following::span[contains(text(),'Details')]");
    private final By currentPaymentDetails = By.xpath("//span[contains(text(),'Current Payment')]//following::span[contains(text(),'Details')]");
    private final By confirmButton = By.xpath("//button[contains(@class,'subscription-confirm-button')]");
    private final By alertConfirmButton = By.cssSelector(".kUOSAq .sc-AykKE");
    private final By processPendingSubscription = By.xpath("//button[text()='Remove']");
    private final By protocolType = By.cssSelector("input.sc-LzLtL");
    private final String numOfSessionsBaseLocate = "//span[text()='Number of Sessions']//following::div[1]";
    private final By minusButton = By.xpath(numOfSessionsBaseLocate + "/div[1]");
    private final By numOfSessions = By.xpath(numOfSessionsBaseLocate + "/div[2]");
    private final By plusButton = By.xpath(numOfSessionsBaseLocate + "/div[3]");
    private final By protocol = By.xpath("//span[text()='Protocol:']//following::span");
    private final By sessions = By.xpath("//span[text()='Sessions:']//following::span");
    private final By addButton = By.xpath("//button[text()='Add']");

    private final By columnProtocolType = By.xpath("//div[@class='sc-LzLvd ixVcyL']//p[1]");
    private final By columnPaymentStatus = By.cssSelector("span.sc-LzLwn");
    private final By columnSessions = By.xpath("//span[@class='sc-LzLwr iFBxMS']");
    private final By columnSessionsUpdated = By.xpath("//span[@class='sc-LzLwr iFBxMS']//following-sibling::p");
    private final By columnPendingStatus = By.cssSelector("span.sc-LzLwn.dDxciB");

    public boolean verifyPage() {
        return BrowserUtils.verifyPage(addExchangeButton, "Subscription");
    }

    public Map<String, String> getLastExchange() {
        Map<String, String> map = new HashMap<>();

        List<WebElement> listProtocolType = driver.findElements(columnProtocolType);
        if (listProtocolType.size() != 0) {
            map.put("protocolType", listProtocolType.stream()
                    .filter(i -> (listProtocolType.indexOf(i) % 2) == 0)
                    .map(WebElement::getText)
                    .reduce((first, second) -> second).get());
        }

        List<WebElement> listSessions = driver.findElements(columnSessions);

        if (listSessions.size() != 0) {
            map.put("sessions", listSessions.stream()
                    .map(WebElement::getText)
                    .reduce((first, second) -> second).get());
        }

        List<WebElement> listSessionsAdded = driver.findElements(columnSessionsUpdated);

        if (listSessionsAdded.size() != 0) {
            map.put("sessionsAdded", listSessionsAdded.stream()
                    .map(WebElement::getText)
                    .reduce((first, second) -> second).get());
        }

        List<WebElement> listPaymentStatus = driver.findElements(columnPaymentStatus);

        if (listPaymentStatus.size() != 0) {
            map.put("paymentStatus", listPaymentStatus.stream()
                    .map(WebElement::getText)
                    .reduce((first, second) -> second).get());
        }

        return map;
    }

    public Map<String, List<String>> getAllExchange(String columns) {
        Map<String, List<String>> map = new HashMap<>();

        if (columns.contains("protocolType")) {
            List<WebElement> listProtocolType = driver.findElements(columnProtocolType);
            if (listProtocolType.size() != 0) {
                map.put("protocolType", listProtocolType.stream()
                        .filter(i -> (listProtocolType.indexOf(i) % 2) == 0)
                        .map(WebElement::getText)
                        .collect(Collectors.toList()));
            }
        }
        if (columns.contains("sessions")) {
            List<WebElement> listSessions = driver.findElements(columnSessions);

            if (listSessions.size() != 0) {
                map.put("sessions", listSessions.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList()));
            }
        }
        if (columns.contains("sessionsAdded")) {
            List<WebElement> listSessionsAdded = driver.findElements(columnSessionsUpdated);

            if (listSessionsAdded.size() != 0) {
                map.put("sessionsAdded", listSessionsAdded.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList()));
            }
        }
        if (columns.contains("paymentStatus")) {
            List<WebElement> listPaymentStatus = driver.findElements(columnPaymentStatus);

            if (listPaymentStatus.size() != 0) {
                map.put("paymentStatus", listPaymentStatus.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList()));
            }
        }
        if (columns.contains("pendingStatus")) {
            List<WebElement> listPendingStatus = driver.findElements(columnPendingStatus);

            if (listPendingStatus.size() != 0) {
                map.put("pendingStatus", listPendingStatus.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList()));
            }
        }

        return map;
    }

    public SubscriptionPage clickProtocolTypeDropDown() {
        BrowserUtils.waitForClickability(protocolType).click();
        return this;
    }

    public SubscriptionPage selectProtocolType(String value) {
        String byText = "//div[text()='" + value + "']";
        BrowserUtils.waitForClickability(By.xpath(byText)).click();
        return this;
    }

    public SubscriptionPage clickOnPlusButton(int num) {
        for (int i = 0; i < num; i++) {
            BrowserUtils.waitForClickability(plusButton).click();
        }
        return this;
    }

    public SubscriptionPage clickOnMinusButton(int num) {
        for (int i = 0; i < num; i++) {
            BrowserUtils.waitForClickability(minusButton).click();
        }
        return this;
    }

    public SubscriptionPage clickAddButton() {
        BrowserUtils.waitForClickability(addButton).click();
        return pg.getSubscriptionPage();
    }

    public SubscriptionPage addNewExchange(String protocolType, Integer numOfSessions) {
        clickAddExchange();
        clickProtocolTypeDropDown();
        selectProtocolType(protocolType);
        clickOnPlusButton(numOfSessions);
        clickAddButton();
        return pg.getSubscriptionPage();
    }

    public By getMonthlySubsDetails() {
        return monthlySubsDetails;
    }

    public By getCurrentPaymentDetails() {
        return currentPaymentDetails;
    }

    public SubscriptionPage clickAddExchange() {
        BrowserUtils.waitForClickability(addExchangeButton).click();
        return this;
    }

    public SubscriptionPage addNumberOfSessions(int num) {
        List<WebElement> buttons = driver.findElements(addSessionsButton);
        for (int i = 0; i < num; i++) {
            buttons.get(buttons.size() - 1).click();
        }
        return this;
    }

    public SubscriptionPage subtractNumberOfSessions(int num) {
        List<WebElement> buttons = driver.findElements(subtractSessionsButton);
        for (int i = 0; i < num; i++) {
            buttons.get(buttons.size() - 1).click();
        }
        return this;
    }

    public SubscriptionPage hideDetails(By element) {
        WebElement link = BrowserUtils.waitForClickability(element);
        if (link.getText().equals("Hide Details")) {
            link.click();
        }
        return this;
    }

    public CartPage clickConfirmButton() {

        BrowserUtils.waitForClickability(confirmButton).click();
        return pg.getCartPage();
    }

    public boolean confirmButtonisDisplayed() {
        return driver.findElement(confirmButton).isDisplayed();
    }

    public SubscriptionPage clickDeleteSelectedSubsciptions() {
        BrowserUtils.waitForClickability(deleteSelectedSubsciptions).click();
        return this;
    }

    public SubscriptionPage clickDiscardAllChanges() {
        BrowserUtils.waitForClickability(discardAllChangesButton).click();
        return this;
    }

    public SubscriptionPage selectAllRecords() {
        WebElement selectAll = BrowserUtils.waitForPresence(selectAllRecords);
        BrowserUtils.clickWithJS(selectAll);
        return this;
    }

    public SubscriptionPage clickAlertConfirmButton() {
        BrowserUtils.waitForClickability(alertConfirmButton).click();
        return this;
    }

    public SubscriptionPage clickRemoveProcessPendingSubscription() {
        BrowserUtils.waitForClickability(processPendingSubscription).click();
        return this;
    }

}
