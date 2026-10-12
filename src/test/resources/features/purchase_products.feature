Feature: Purchase products in DemoBlaze

  As a customer
  I want to add products to the cart
  So that I can complete a purchase


  Scenario: Customer purchases two products successfully

    Given the customer opens DemoBlaze

    When the customer adds the following products to the cart:
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |

    And the customer opens the shopping cart

    Then the following products should be visible in the cart:
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |

    When the customer completes the purchase form with:
      | name    | Wilson Alzate    |
      | country | Colombia         |
      | city    | Medellin         |
      | card    | 4111111111111111 |
      | month   | 12               |
      | year    | 2027             |

    And the customer confirms the purchase

    Then the purchase should be completed successfully