Feature: Login to yandex
  Scenario: Login with correct username and password
    Given I navigate to yandex home page
    And enter username as "username" and password as "password"
    And click submit button
    Then I should see mail logo


  Scenario: Login with incorrect credentials
    Given I navigate to yandex home page
    And enter username as "username" and password as "password1"
    And click submit button
    Then I should see error message