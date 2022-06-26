@bug
Feature: Test Suite for Defects

  Background:
    Given user is on the application
    Then click on sign in
    And sign in with credentials
    And verify that "Main" page is displayed
    And click on "User Profile" link
    And click on "Subscription" link
    And verify that "Subscription" page is displayed

  Scenario: When adding more than 3-4 exchanges based on the screen resolution, Payment button is not clickable / visible
    And Add Exchange with protocol type and number of sessions
      | protocolType | sessions |
      | FIX 4.2      | 2        |
      | FIX 4.3      | 3        |
      | FIX 4.4      | 4        |
      | FIX 5.0      | 5        |
    And verify Pay button on the screen

  Scenario: After logging out, unpaid exchanges has lost.
    And verify that new unpaid exchange is listed on page

  Scenario: When user with paid exchanges confirms his new unpaid exchanges and logs out
    And Add Exchange with protocol type and number of sessions
      | protocolType | sessions |
      | FIX 4.2      | 2        |
    Then Hide Details each section
      | section              |
      | Monthly Subscription |
      | Current Payment      |
    And click "Confirm" button
    And verify that "Cart" page is displayed
    And verify that navigate back to subscription page

