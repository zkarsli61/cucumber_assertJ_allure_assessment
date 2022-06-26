@subscription
Feature: Test Suite for Subscription Page
  Background: Create exchanges
    Given user is on the application
    Then click on sign in
    And sign in with credentials
    And verify that "Main" page is displayed
    # Navigate to Subscription page
    And click on "User Profile" link
    And click on "Subscription" link
    And verify that "Subscription" page is displayed
    And Add Exchange with protocol type and number of sessions
      | protocolType | sessions |
      | FIX 4.2      | 2        |
#      | FIX 4.4      | 4        |

  Scenario: modify exchange
    Then Add number of Sessions 1
    Then Subtract number of Sessions 2
    And verify that sessions value are added with "-1"

  Scenario: Delete exchange
    And select all records
    And click "delete selected subscriptions" button
    And verify that exchange status are pending

  Scenario: Discard All Exchanges
    And click "Discard All Changes" button
    And verify that changes are rollbacked

  Scenario: Proceed to checkout
    # Confirm Exchanges
    Then Hide Details each section
      | section              |
      | Monthly Subscription |
      | Current Payment      |
    And click "Confirm" button
    And verify that "Cart" page is displayed
    # Proceed to checkout
    Then click Proceed to Checkout button
    And verify that "Review" page is displayed
    # Review checkout
    Then check I agree
    And click Subscribe button
    And verify that "Message Success" page is displayed
    # Go to Exchanges
    And click Go to exchanges button
    And verify that "Exchanges" page is displayed