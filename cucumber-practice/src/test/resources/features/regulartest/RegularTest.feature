@regulartest
Feature:
  Regular test feature

  Background:
    Given run the backround first before scenario


  @Test1
  Scenario: Verify regular test1
    Given I login as a user, accountType: RC_CA, number: <rc_username>, password: <rc_password> data


  Scenario:
    When sign in without parameter
    When sign in without parameter:
    When sign in with parameter
    When sign in with parameter:jennifer

  @Test2
  Scenario Outline: Verify regular test2
    Given I login as a user, accountType: <account_type>, number: <rc_username>, password: <rc_password> data
    Then there is 1 piece of the message
    Then there are 2 pieces of the message
#    When I select $BrandName for Desktop

    When test sign in with parameter:"<account_type>"

    When [Entry Point] Sign In With User: "test1" And Go To "<account_type>" With Parameter:"<rc_password>"
    When [Entry Point] Sign In With User: "test1" And Go To "<account_type>"
    When [Entry Point] Sign In With User: "test1" And Go To "<account_type>" With Parameter










    Examples:
      | account_type | rc_username  | rc_password |
      | RC_UK        | 448080031114 | Test!123    |
      | RC_US        | 13180031114  | Test!123    |
