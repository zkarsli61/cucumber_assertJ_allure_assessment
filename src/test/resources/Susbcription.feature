@end2end
Feature: Test Suite for Subscription Page

  Scenario: Login the app
    Given user is on the application
    Then click on sign in
    And sign in with credentials
    And verify that "Main" page is displayed

  Scenario: Navigate to Subscription page
    And click on "User Profile" link
    And click on "Subscription" link
    And verify that "Subscription" page is displayed

  Scenario Outline: Add new exchange
    Then click on Add Exchange button
    And select protocol type "<protocolType>"
    And Set Number of Sessions <numSession>
    And click Add button
    And verify that new exchange is listed on page
    And verify Pay button on the screen

    Examples:
      | protocolType | numSession |
      | FIX 4.2      | 2          |
      | FIX 4.3      | 3          |
      | FIX 5.0      | 4          |

  Scenario: Modify Exchanges
    Then Add number of Sessions 1
    Then Subtract number of Sessions 2
    And verify that sessions value are added with "-1"

  Scenario: Confirm Exchanges
    Then Hide Details each section
      | section              |
      | Monthly Subscription |
      | Current Payment      |
    And click Confirm button
    And verify that "Cart" page is displayed

  Scenario: Proceed to checkout
    Then click Proceed to Checkout button
    And verify that "Review" page is displayed

  Scenario: Review checkout
    Then check I agree
    And click Subscribe button
    And verify that "Message Success" page is displayed

  Scenario: Go to Exchanges
    And click Go to exchanges button
    And verify that "Exchanges" page is displayed

  Scenario: Delete all subscription
    And click on "User Profile" link
    And click on "Subscription" link
    And verify that "Main" page is displayed
    And select all records
    And click delete selected subscriptions
    And click Confirm button
    And click Confirm on Alert
    And click on "User Profile" link
    And click on "Logout" link




