@HomePage
Feature: Home Page Verification

  @VerifyTitleAndSections
  Scenario: Verify the title and major sections of the Home Page
    Given I open the browser and navigated to home page
    Then the page title should be "Home Page"
    And the following sections should be visible:
      | HOME         |
      | ALL COURSES  |
      | INTERVIEW    |
      | SUPPORT      |
      | BLOG        |