
Feature: Create the product in a api play ground site

  @RestServicePostCSV
  Scenario: 101:Read data from csv and create JSON request and send request and get response and validate it
    Given Customer "APIPlayground" read the data from CSV "CreateProduct.csv" from test case id "tc01" and create the JSON request using JSON request template "APIPlayground_CreateProduct_Template"
    When I submit the JSON "POST" request with end point "/products"
    When I validate "Laptops" from "name" node in JSON response - JSON path "data.name"
    When I validate "Personal" from "type" node in JSON response - JSON path "data.type"
    When I validate "11223344" from "upc" node in JSON response - JSON path "data.upc"
    When I validate "10000" from "price" node in JSON response - JSON path "data.price"
    When I validate "Placing this request to buy personal laptop" from "description" node in JSON response - JSON path "data.description"
    When I validate "i5core" from "model" node in JSON response - JSON path "data.model"

