@shoppingpage
Feature: Shopping Cart Functionality

  Scenario: Add a product to the cart and proceed to checkout
    Given User is on the practice page
    When User navigates to eCommerce Practice Page
    And User clicks on "Shop Now"
    And User selects a product
    And User selects size "L"
    And User adds the product to the bag
    And User proceeds to checkout
    Then User should see "My Bag" page