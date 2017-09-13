@regulartest
Feature:
  Regular test feature

  Background:
    Given run the backround first before scenario

  @Test1
  Scenario: Verify regular test1
    Given I login as a user, accountType: RC_CA, number: <rc_username>, password: <rc_password> data


  @Test2
  Scenario Outline: Verify regular test2
    Given I login as a user, accountType: <account_type>, number: <rc_username>, password: <rc_password> data
    Then there is 1 piece of the message
    Then there are 2 pieces of the message
#    When I select $BrandName for Desktop
    When I have $10000 in my account


    Examples:
      | account_type | rc_username  | rc_password |
      | RC_UK        | 448080031114 | Test!123    |
      | RC_US        | 13180031114  | Test!123    |
