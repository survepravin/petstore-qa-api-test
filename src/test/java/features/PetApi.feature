@api
Feature: Demo Pet Store

  @petstore
  Scenario Outline: To verify 4 APIs of Pet Store are working as expected
    When Store owner should able to search pets based on "<status>"
    And Creates a new pet with status as "<c_status>"
    Then Verify pet details
    And Updates the pet status to "<u_status>"
    Then Verify pet details
    And Deletes the pet
    Then Verify pet is deleted

    Examples: 
      | status    | c_status  | u_status |
      | available | available | sold     |
