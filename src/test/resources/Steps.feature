Feature: Sign up to the Komunal
  Scenario: Successful open register
    Given I am on Komunal deposit web page
    When I click on the register link
    Then I should be taken to the sign up page

  Scenario: Fill data deposan
    Given I am on deposan form
    When I fill all form with valid data
    Then I should be taken to dashboard page
