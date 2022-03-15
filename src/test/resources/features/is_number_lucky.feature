Feature: Is number lucky
    Check a number is lucky

    Scenario: 1 is not lucky
        Given I set 1 to lucky number
        Then Number is not lucky

    Scenario: 2 is lucky
        Given I set 2 to lucky number
        Then Number is lucky

    Scenario: number can be random
        Given I set 1 to lucky number
        When I random the number
        Then Number is not 1