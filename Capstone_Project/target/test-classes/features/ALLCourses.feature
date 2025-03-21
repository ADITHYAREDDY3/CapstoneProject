@allCourses
Feature: Search and Filter Course
Background:
	Given user is on the All Courses page 
@categoryFiled
Scenario Outline: User filters courses by category field
  When user selects "<category>" from the category field
  And clicks on the search button
  Then courses related to "<category>" should be displayed

	Examples:
	
  | category                 |
  | Sotfware Testing         |
  | Selenium WebDriver       |
  | Software Development     |
@searchField  
Scenario Outline: User searches courses by Search Course filed
  When user enters "<course>" in the search field
  And clicks on the search button
  Then courses related to course "<course>" should be displayed

  Examples: 
  | course       |
  | selenium     |
  | java         |
  | python       |
 @categoryAndSearch 
Scenario Outline: User filters courses by category and searches for a course
    When user selects "<category>" from the category field
    And user searches for "<course>" in search field
    And clicks on the search button
    Then relevant courses should be displayed 
    
    Examples: 
	| category                 | course   |
  | Sotfware Testing         | selenium |
  | Test Automation          | java     |
  | Software Development     | javascript   |