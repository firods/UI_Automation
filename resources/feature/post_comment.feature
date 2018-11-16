#Author: shrikantfirodiya4@gmail.com
@All
Feature: Verify Post comment
    Scenario: Verify post comment with invalid email and valid email
    Given Open website and enter url
    When enter invalid email and post comment
    Then verify incorrect email error message
    When enter valid comment data and post comment
    Then verify post comment message