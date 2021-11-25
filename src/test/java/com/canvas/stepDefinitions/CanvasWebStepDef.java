package com.canvas.stepDefinitions;

import com.canvas.pageObjects.Calculation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CanvasWebStepDef {

	@Given("I am on the calculater screen")
	public void iamOnCalculatorScreen() {
		Calculation.presenceOfCalculatorScreen();
	}

	@When("I provide calculation {string} as an input")
	public void iProvideCalculationAsAnInput(String problem) {
		Calculation.clickOnValues(problem);
	}

	@Then("I get {double} as calculated for given input")
	public void iGetCalculatedInput(double value) {
		Calculation.verifyCalculatedInput(value);	
	}

}