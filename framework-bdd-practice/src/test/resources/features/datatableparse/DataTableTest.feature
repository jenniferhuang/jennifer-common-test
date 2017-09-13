Feature:
  Data Table feature

  Background:
    Given run the backround first before scenario



  Scenario: data table test
    Given I login as a user the following:
      | account_type | rc_username  | rc_password |
      | RC_BT        | 448080031114 | Test!123    |
      | RC_CA        | 13180031114  | Test!123    |
    Then there is 1 piece of the message
