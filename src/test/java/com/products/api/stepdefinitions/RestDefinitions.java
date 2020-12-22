package com.products.api.stepdefinitions;

import com.products.api.dataParsers.CSVData;
import com.products.api.threadVariables.VariableManager;
import com.products.api.assertions.Assertions;
import com.products.api.dataParsers.TableData;
import com.products.api.services.Payload;
import com.products.api.services.RestServices;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.Map;

public class RestDefinitions {
    CSVData csvData = new CSVData();
    Payload payLoad = new Payload();
    RestServices restServices = new RestServices();
    Assertions assertions = new Assertions();
    TableData tableData = new TableData();

    //Post Request
    @Given("Customer {string} read the data from CSV {string} from test case id {string} and create the JSON request using JSON request template {string}")
    public void customer_read_the_data_from_CSV_from_test_case_id_and_creat_the_JSON_request_using_JSON_request_template(String customerName, String csvFileName, String testCaseID, String requestTemplate) {
        Map <String, String> data = this.csvData.readData(csvFileName, testCaseID);
        VariableManager.getInstance().getVariables().setVar("data", data);
        String request = this.payLoad.payLoadPreparation(requestTemplate, data);
        VariableManager.getInstance().getVariables().setVar("customerName", customerName);
        VariableManager.getInstance().getVariables().setVar("request", request);
    }

    //Post Request
    @When("I submit the JSON {string} request with end point {string}")
    public void i_submit_the_JSON_request_with_end_point(String requestMethod, String endPoint) {
        String customerName = VariableManager.getInstance().getVariables().getVar("customerName").toString();
        String request = VariableManager.getInstance().getVariables().getVar("request").toString();
        String response = this.restServices.getResponseFromPostMethod(request, endPoint, customerName);
        VariableManager.getInstance().getVariables().setVar("response", response);
    }

    //Common for both Post and Get Request validations
    @When("I validate {string} from {string} node in JSON response - JSON path {string}")
    public void i_validate_from_node_in_JSON_response_JSON_path(String expectedValue, String nodeName, String jsonPath) {
        String response = VariableManager.getInstance().getVariables().getVar("response").toString();
        String actualValue = this.restServices.getValueFromJsonResponse(response, jsonPath);
        this.assertions.assertValues(nodeName, expectedValue, actualValue);

    }

    //Common for both Post and Get Request validations
    @When("I validate {string} node name in JSON response - JSON path {string}")
    public void i_validate_node_name_in_JSON_response_JSON_path(String nodeName, String jsonPath) {
        Map <Object, Object> data = (Map<Object, Object>) VariableManager.getInstance().getVariables().getVar("data");
        String expectedValue = data.get(nodeName).toString();
        String response = VariableManager.getInstance().getVariables().getVar("response").toString();
        String actualValue = this.restServices.getValueFromJsonResponse(response, jsonPath);
        this.assertions.assertValues(nodeName, expectedValue, actualValue);
    }

}
