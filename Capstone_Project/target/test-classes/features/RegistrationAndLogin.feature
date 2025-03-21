@UserAuth
Feature: User Registration and Login

  Background: 
    Given User is on the Letskodeit Practise homepage
    When the user clicks the Sign In link
    Then the user is in the login page

  @Register
  Scenario Outline: Successful Registration with valid credentials
    Then the user clicks on the register link
    Then the registration page is displayed on the screen
    When the user enters "<firstName>" and "<lastName>" and "<email>" and "<password>" and "<confirmPassword>"
    And the user ticks the captcha checkbox
    When the user clicks the signup button
    Then the user sees the My Courses page
    When the user clicks the Logout 


    Examples: 
      | firstName     | lastName | email                  | password | confirmPassword |
      | Adithya Reddy | Gooli    | 12a3456543za342q101@gmail.com | Aditya@1 | Aditya@1        |

  @ValidLogin
  Scenario Outline: Successful Login with valid credentials
    When the user enters "<email>" and "<password>"
    And the user clicks the login button for a successful login
    Then the user is redirected to the My Courses page
    When the user clicks the Logout
    Then the user is redirected to the homepage

    Examples: 
      | email          | password  |
      | 123456543210@gmail.com | Aditya@1  |

  @InvalidLogin
  Scenario Outline: Login with Invalid credentials
    When the user enters WongCrendtials "<email>" and "<password>"
    And the user clicks the login button for an unsuccessful login
    Then the login page will show "Incorrect login details" in the display
    When the user clicks the Forget Password button
    Then the user is redirected to the Password RestPage

    Examples: 
      | email             | password |
      | Aditya1@gmail.com | Aditya@1 |
