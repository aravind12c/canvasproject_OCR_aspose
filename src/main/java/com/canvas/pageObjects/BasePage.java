package com.canvas.pageObjects;

import java.awt.Rectangle;
import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import com.aspose.ocr.AsposeOCR;
import com.canvas.driverInit.DriverManager;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage {

	/**
	 * All common functions are associated here
	 */

	private static String imagepath = System.getProperty("user.dir") + "/images/calculater.png";
	private static String waitstrategyvisible = "Visible";
	private static String waitstrategyclickable = "Clickable";

	/**
	 * this method press keyboard keys
	 */
	public static void sendActionKeysOn(String controlkey) {
		Actions actionClass = new Actions(DriverManager.getDriver());
		actionClass.sendKeys(controlkey).build().perform();
	}

	/**
	 * this method press keyboard keys
	 */
	public static void sendActionKeysEnter() {
		Actions actionClass = new Actions(DriverManager.getDriver());
		actionClass.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * this method to save base64 Image as image
	 */
	public static void saveAsImg(By by) {
		WebElement element = performExplicitWait(waitstrategyclickable, by);
		JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
		String base64img = jse.executeScript("return arguments[0].toDataURL('image/png').substring(21);", element)
				.toString();
		byte[] data = DatatypeConverter.parseBase64Binary(base64img.split(",")[1]);
		File file = new File(imagepath);
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			outputStream.write(data);
		} catch (IOException e) {
			Assert.fail("Unable to write Image" + e.getStackTrace());
		}
	}

	/**
	 * this method to save base64 Image as image
	 */
	public static double verifyImgwithValue() {
		AsposeOCR api = new AsposeOCR();
		try {
			ArrayList<Rectangle> rectangle = new ArrayList<Rectangle>();
			rectangle.add(new Rectangle(20, 30, 500, 70));
			String result = api.RecognizePage(imagepath, rectangle).replaceAll("[^0-9-.]", "");
			System.out.println("Ans is: "+result);
			return Double.parseDouble(result);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * this method to verify whether the element is displayed and returns boolean
	 * value
	 */
	protected static boolean isElementDisplayed(By by) {
		boolean flag = false;
		WebElement element = performExplicitWait(waitstrategyvisible, by);
		if (element.isDisplayed()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * this method is used to switch into frame using frame element
	 */
	protected static void switchToFrame(By by) {
		WebElement element = performExplicitWait(waitstrategyclickable, by);
		DriverManager.getDriver().switchTo().frame(element);
	}

	/**
	 * this method is for to switch back to default frame or ancestor frame if we
	 * went into the frames
	 */
	protected static void switchToDefault() {
		DriverManager.getDriver().switchTo().defaultContent();
	}

	/**
	 * this method is used as static wait where it stops execution for given period
	 * of time
	 */
	protected static void pause(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	/**
	 * this method waits for 30 Seconds to load the Element to be displayed before
	 * throwing the NoSuchElementException
	 */
	protected static WebElement performExplicitWait(String waitstrategy, By by) {
		WebElement element = null;
		if (waitstrategy.equalsIgnoreCase("Clickable")) {
			element = new WebDriverWait(DriverManager.getDriver(), 30)
					.until(ExpectedConditions.elementToBeClickable(by));
		} else if (waitstrategy.equalsIgnoreCase("Visible")) {
			element = new WebDriverWait(DriverManager.getDriver(), 30)
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} else if (waitstrategy.equalsIgnoreCase("None")) {
			element = DriverManager.getDriver().findElement(by);
		}
		return element;
	}

	/**
	 * this method returns the browsername in form of String
	 */
	protected static String getBrowserName() {
		return ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities().getBrowserName();
	}

}
