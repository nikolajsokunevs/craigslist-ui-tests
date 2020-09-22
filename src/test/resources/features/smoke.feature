Feature: Do a smoke test

  Scenario: Verify that sorting by price works properly
    Given Application is deployed and opened
    When User navigates to the Housing screen
    Then Verify that sorting by price works properly

  Scenario: Verify default sorting options
    Given Application is deployed and opened
    When User navigates to the Housing screen
    Then Verify that all sorting options are displayed
      | upcoming |
      | newest   |
      | price ↑  |
      | price ↓  |


  Scenario: Verify extended sorting options
    Given Application is deployed and opened
    When User navigates to the Housing screen
    When User does a search "Luxury"
    Then Verify that all sorting options are displayed
      | upcoming |
      | newest   |
      | relevant |
      | price ↑  |
      | price ↓  |
