package com.esprow.step_definitions;

import com.esprow.managers.Driver;
import com.esprow.managers.PageObjectManager;
import com.esprow.pages.SubscriptionPage;
import com.esprow.utilities.BrowserUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.SoftAssertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionStepDefinitions {

    SubscriptionPage subscriptionPage = new PageObjectManager().getSubscriptionPage();
    Map<String, String> lastExchange = new HashMap<>();
    static Map<String, String> newExchange = new HashMap<>();

    SoftAssertions softly = new SoftAssertions();

    @And("select protocol type {string}")
    public void selectProtocolType(String value) {
        newExchange.put("protocolType", value);
        subscriptionPage
                .clickProtocolTypeDropDown()
                .selectProtocolType(value);
    }

    @And("Set Number of Sessions {int}")
    public void setNumberOfSessions(int num) {
        newExchange.put("sessions", String.valueOf(num));
        subscriptionPage.clickOnPlusButton(num);
    }

    @Severity(SeverityLevel.CRITICAL)
    @And("verify that new unpaid exchange is listed on page")
    public void verifyNewRecordThatIsListedOnPage() {
        lastExchange.clear();

        lastExchange = subscriptionPage.getLastExchange();
        assertThat(lastExchange)
                .as("new Exchange doesn't match last exchange")
                .containsExactlyEntriesOf(newExchange);
    }

    @And("verify Pay button on the screen")
    public void verifyPay_button_on_the_screen() {
        assertThat(subscriptionPage.confirmButtonisDisplayed())
                .as("Confirm/Pay button should be visible on the screen")
                .isTrue();
    }

    @And("Add Exchange with protocol type and number of sessions")
    public void addExchangeWithProtocolTypeAndNumberOfSessions(DataTable param) {
        List<Map<String, String>> newExchanges = param.asMaps(String.class, String.class);
        newExchange.put("paymentStatus", "UNPAID");
        for (Map<String, String> each : newExchanges) {
            newExchange.putAll(each);
            subscriptionPage.addNewExchange(each.get("protocolType"), Integer.parseInt(each.get("sessions")));
            subscriptionPage.verifyPage();
            verifyNewRecordThatIsListedOnPage();
        }
    }

    @Given("Hide Details each section")
    public void hide_details_each_section(DataTable sections) {

        List<Map<String, String>> sectionList = sections.asMaps(String.class, String.class);
        for (Map<String, String> each : sectionList) {
            switch (each.get("section")) {
                case "Monthly Subscription":
                    subscriptionPage.hideDetails(subscriptionPage.getMonthlySubsDetails());
                    break;
                case "Current Payment":
                    subscriptionPage.hideDetails(subscriptionPage.getCurrentPaymentDetails());
                    break;
            }
        }
    }

    @And("Add number of Sessions {int}")
    public void AddNumberOfSessions(int num) {
        subscriptionPage.addNumberOfSessions(num);
    }

    @And("Subtract number of Sessions {int}")
    public void SubtractNumberOfSessions(int num) {
        subscriptionPage.subtractNumberOfSessions(num);
    }

    @And("verify that sessions value are added with {string}")
    public void verifyThatSessionsAreAddedBy(String added) {

        lastExchange.clear();
        lastExchange = subscriptionPage.getLastExchange();

        assertThat(added)
                .as("numberOfSessions of updated Exchange doesn't match")
                .isEqualTo(lastExchange.get("sessionsAdded"));
    }

    @And("click {string} button")
    public void clickConfirmButton(String button) {
        switch (button) {
            case "delete selected subscriptions":
                subscriptionPage.clickDeleteSelectedSubsciptions();
                break;
            case "Confirm":
                subscriptionPage.clickConfirmButton();
                break;
            case "Confirm on Alert":
                subscriptionPage.clickAlertConfirmButton();
                break;
            case "Discard All Changes":
                subscriptionPage.clickDiscardAllChanges();
                break;
            case "Add Exchange":
                subscriptionPage.clickAddExchange();
                break;
            case "Add":
                subscriptionPage.clickAddButton();
                break;

        }
    }

    @And("select all records")
    public void selectAllRecords() {
        subscriptionPage.selectAllRecords();
    }


    @And("verify that changes are rollbacked")
    public void verifyThatChangesAreRemoved() {
        Map<String, List<String>> all = new HashMap<>();
        all = subscriptionPage.getAllExchange("paymentStatus,pendingStatus");

        if (all.containsKey("paymentStatus")) {
            softly.assertThat(all.get("paymentStatus"))
                    .as("There should be no UNPAID exchanges on discard all action")
                    .doesNotContain("UNPAID");
        }

        if (all.containsKey("pendingStatus")) {
            softly.assertThat(all.get("pendingStatus"))
                    .as("There should be no exchanges with PENDING status on discard all action")
                    .doesNotContain("PENDING DELETION");
        }

        softly.assertAll();

    }

    @And("verify that exchange status are pending")
    public void verifyThatExchangeStatusArePending() {
        Map<String, List<String>> all = new HashMap<>();
        all = subscriptionPage.getAllExchange("pendingStatus");

        assertThat(all.get("pendingStatus")).filteredOn(x -> !x.contains("PENDING")).hasSize(0);

    }

    @Severity(SeverityLevel.BLOCKER)
    @And("verify that navigate back to subscription page")
    public void verifyThatItIsDeadlock() {
        Driver.getDriver().navigate().back();
        assertThat(Driver.getDriver().getCurrentUrl()).as("Navigate back to subscription page failed").endsWith("subscription");
    }

    @Then("navigate to subscription page")
    public void navigateToSubscriptionPage() {
        Driver.getDriver().get("https://spa-dev.etpmarkets.com:3000/app/subscription");
        BrowserUtils.waitForPageToLoad();
    }

    @And("click remove button on Alert message")
    public void clickRemoveButtonOnAlertMessage() {
        subscriptionPage.clickRemoveProcessPendingSubscription();
    }
}
