@regulartest
Feature:
  Regular test feature
  As a [role], I want [feature], so that I [benefit]

  Background:
    Given run the backround first before scenario


  @Test1
  Scenario: Verify regular test1111
    Given I login as a user, accountType: RC_CA, number: <rc_username>, password: <rc_password> data


  Scenario: with parameter/without parameter match
    When sign in without parameter
    When sign in without parameter:
    When sign in with parameter
    When sign in with parameter:jennifer


  Scenario: (?:is|are), match options strings  is|are, ?match
    Given there is 1 piece of the message
    Given there are 2 pieces of the message


    @resetAccount
  Scenario: escape the dollar sign
    When I have $1000 in my account





  @Test2
  Scenario Outline: Verify regular test2222
    Given I login as a user, accountType: <account_type>, number: <rc_username>, password: <rc_password> data
    Then there is 1 piece of the message
    Then there are 2 pieces of the message
#    When I select $BrandName for Desktop
    Examples:
      | account_type | rc_username  | rc_password |
      | RC_UK        | 448080031114 | Test!123    |
      | RC_US        | 13180031114  | Test!123    |
