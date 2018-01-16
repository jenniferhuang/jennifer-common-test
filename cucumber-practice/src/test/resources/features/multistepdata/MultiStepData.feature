@jenniferTest1
Feature: Multi scenario data
  Multi scenario data - feature description

  Background:
    Given run the backround first before scenario


  Scenario: data table test
    Given I check account infos as the following:
      | account_type | rc_username  | rc_password |
      | RC_BT        | 448080031114 | Test!123    |
      | RC_CA        | 13180031114  | Test!123    |
    Then there is 1 piece of the message
    Then we have the following account:"448080031114, 13180031114"




  Scenario: Doc Strings test
    Given a blog post named "Random" with Markdown body
"""
  Some Title, Eh?
  ===============
  Here is the first paragraph of my blog post. Lorem ipsum dolor sit amet,
  consectetur adipiscing elit.
  """
    Then there is 2 pieces of the message




  Scenario Outline: list scenario data examples
    Given I login as a user with accountType:"<account_type>" and username:"<rc_username>" and password:"<rc_password>"
    Then we have the following account:"<rc_username>"
    Examples:
      | account_type | rc_username  | rc_password |
      | RC_BT        | 448080031155 | Test!123    |
      | RC_CA        | 13180032222  | Test!123    |


#  mvn test -Dcucumber.options="--tags @jenniferTest1"





#
#Feature
#  Scenario
#    Given, When, Then, And, But (Steps)  "Cucumber does not differentiate between the keywords, but choosing the right one is important for the readability of the scenario as a whole"
#  Background
#  Scenario Outline
#    Examples



