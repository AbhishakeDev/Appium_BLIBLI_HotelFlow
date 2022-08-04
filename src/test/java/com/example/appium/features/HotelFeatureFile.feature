Feature: Appium Automation Intro

  Scenario: Navigate to hotel's homepage
#    Given open the App and tap back button
#    When click on categories
#    And scroll left container and click Tours and travels
#    Then scroll down right container and Click on Hotel

    Given open the App and tap back button
    When search for hotel in search bar
    And click on Hotel option
    And click on Jakarta favorite destination card
    And click on first hotel card
    And click room button
    And click order button
    And fill in details on order page
    Then click on proceed

