package com.canvas.pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

public class Calculation extends BasePage {

	/**
	 * Page Objects and methods are associated based on the steps from the Feature
	 * File
	 */

	private static By calculatorframe = By.xpath("//iframe[@id='fullframe']");
	private static By calculator = By.xpath("//canvas[@id='canvas']");

	public static void presenceOfCalculatorScreen() {
		switchToFrame(calculatorframe);
		Assert.assertTrue(isElementDisplayed(calculator), "There is some issue in Loading Calculator Home Page");
		saveAsImg(calculator);
	}

	public static void clickOnValues(String problem) {
		for (char c : problem.toCharArray()) {
			sendActionKeysOn(Character.toString(c));
			pause(1);
		}
	}

	public static void verifyCalculatedInput(double value) {
		saveAsImg(calculator);
		double answer = verifyImgwithValue();
		Assert.assertEquals(answer, value, "The Value is not same as expected");
	}

}