Feature: Create the user in a gorest api site

  @RestServicePostParamaterization
  Scenario: 101:Read data from csv and create JSON request and send request and get response and validate it
    Given Customer "GoRest" Create JSON request using JSON template "goRest_CreateUser_Template" with parameters
      | NodeName | Values              |
      | name     | Krishna92           |
      | gender   | Male                |
      | status   | Active              |
      | email    | Krishna92@gorest.in |
    When I submit the JSON "POST" request with end point "/users/"
    When I validate "krishna92" from "name" node in JSON response - JSON path "data.name"
    When I validate "Male" from "gender" node in JSON response - JSON path "data.gender"
    When I validate "krishna92@gorest.in" from "email" node in JSON response - JSON path "data.email"
    When I validate "Active" from "status" node in JSON response - JSON path "data.status"
