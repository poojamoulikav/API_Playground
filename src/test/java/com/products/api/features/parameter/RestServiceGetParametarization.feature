Feature: Get the user information and validate from gorest api site

  @RestServicePostParamaterization
  Scenario: 101:Get the user information and validate user data
    Given Customer "GoRest" Create JSON request using JSON template "goRest_CreateUser_Template" with parameters
      | NodeName | Values              |
      | name     | Krishna92           |
      | gender   | Male                |
      | status   | Active              |
      | email    | Krishna92@gorest.in |
      |id        | 1348                |
    When I submit the JSON GET request with endpoint "/users/" with parameter "ID"
    When I validate "krishna90" from "name" node in JSON response - JSON path "data.name"
    When I validate "Male" from "gender" node in JSON response - JSON path "data.gender"
    When I validate "krishna90@gorest.in" from "email" node in JSON response - JSON path "data.email"
    When I validate "Active" from "status" node in JSON response - JSON path "data.status"
    When I validate "1348" from "id" node in JSON response - JSON path "data.id"