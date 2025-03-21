@PracticePage
Feature: Automate LetsKodeIt Practice Page

  @RadioButton
  Scenario Outline: Verify user can select different radio buttons
    When I select the "<car>" radio button
    Then the "<car>" radio button should be selected

    Examples:
      | car  |
      | BMW  |
      | Benz |
      | Honda |

  @CheckBox
  Scenario Outline: Verify user can check and uncheck checkboxes
    When I select the "<car>" checkbox
    Then the "<car>" checkbox should be selected
    When I deselect the "<car>" checkbox
    Then the "<car>" checkbox should not be selected

    Examples:
      | car  |
      | BMW  |
      | Benz |
      | Honda |

  @NewWindow
  Scenario: Verify user can open a new window and switch back
    When I click the Open Window button
    Then a new window should open
    When I switch to the new window
    And I close the new window and switch back

  @NewTab
  Scenario: Verify user can open a new tab and switch back
    When I click the Open Tab link
    Then a new tab should open
    When I switch to the new tab
    And I close the new tab and switch back

  @DropDown
  Scenario Outline: Verify user can select from the dropdown menu
    When I select "<car>" from the dropdown
    Then "<car>" should be selected in the dropdown

    Examples:
      | car  |
      | BMW  |
      | Benz |
      | Honda |

  @MultipleSelect
  Scenario: Verify user can select multiple options from the Multipleselectoption
    When I select the following options from the multiple select dropdown:
       | Apple  |
       | Orange |
    Then the following options should be selected in the multiple select dropdown:
       | Apple  |
       | Orange |
    When I deselect "Orange" from the multiple select dropdown
    Then the following options should be selected in the multiple select dropdown:
       | Apple  |

  @EnableDisable
  Scenario: Verify user can Disable and Enable the input field
    When I click on the "Disable" button
    Then the input field should be disabled
    When I click on the "Enable" button
    Then the input field should be enabled

  @HideShow
  Scenario: Verify user can hide and show the input field
    When I click on the "Hide" button
    Then the input field should be hidden
    When I click on the "Show" button
    Then the input field should be visible

  @Alerts
  Scenario: Verify Alert and Confirm popups
    When I enter "Aditya Reddy" in the name field
    And I click on the "Alert" button
    Then an alert should appear with text containing "Aditya Reddy"
    When I click on the "Confirm" button
    Then a confirmation alert should appear
    And I accept the confirmation alert

  @MouseHover
  Scenario: Verify Mouse Hover functionality
    When I Hover on the "Mouse Hover" button
    Then the mouse hover options should be visible
    When I click on the "Top" option
    Then the page should scroll to the top
    When I Hover on the "Mouse Hover" button
    And I click on the "Reload" option
    Then the page should reload

  @WebTable
  Scenario: Verify Web Table Data
    Then the table should contain the following courses:
      | Let's Kode It | Selenium WebDriver With Java    | 35    |
      | Let's Kode It | Python Programming Language     | 30    |
      | Let's Kode It | JavaScript Programming Language | 25    |
    When I search for the course "Python Programming Language" in table
    Then the price should be "30"
    
    
@AutoSuggest
Scenario: Verify Auto Suggest functionality
    When I enter "Java" in the search field
    Then I should see the suggestions related to "Java"
    And I select "Selenium WebDriver Java" from the suggestions
    Then the search field should contain "Selenium WebDriver Java"


@iframe
Scenario: Validate iframe and section elements
  Given I am on the Practice Page
  And I switch to the iframe
  Then I verify the iframe page title
  And the following sections should be visible in the iframe:
    | HOME       |
    | ALL COURSES|
    | INTERVIEW  |
    | SUPPORT    |
    | BLOG       |


