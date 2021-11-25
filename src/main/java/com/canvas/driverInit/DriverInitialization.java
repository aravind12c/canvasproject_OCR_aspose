package com.canvas.driverInit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.canvas.webUtilities.PropertyUtils;
import com.canvas.webUtilities.Screenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInitialization {

	/**
	 * This method is used to set driver and get the URL
	 */
	@Before
	public static void driverInit() throws MalformedURLException {
		DriverManager.setDriver(getBrowserDriver(PropertyUtils.configProperty("browser")));
		DriverManager.getDriver().get(PropertyUtils.configProperty("URL"));
		DriverManager.getDriver().manage().window().maximize();
	}

	/**
	 * Chrome/Firefox Driver is called based on argument Browser
	 */
	public static WebDriver getBrowserDriver(String browser) throws MalformedURLException {

		URL hubUrl = new URL(PropertyUtils.configProperty("dockerURL")); // HUB URL
		String runmode = PropertyUtils.configProperty("runmode");

		WebDriver browserdriver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
			options.setCapability("platform", "Any");

			if (runmode.equalsIgnoreCase("remote")) {
				browserdriver = new RemoteWebDriver(hubUrl, options);
				((RemoteWebDriver) browserdriver).setFileDetector(new LocalFileDetector());
			} else {
		        WebDriverManager.chromedriver().setup();
				browserdriver = new ChromeDriver(options);
			}

		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("security.OCSP.enabled", 0);
			profile.setPreference("network.http.use-cache", false);

			if (runmode.equalsIgnoreCase("remote")) {
				options.setProfile(profile);
				options.setCapability("platform", "Any");
				browserdriver = new RemoteWebDriver(hubUrl, options);
				((RemoteWebDriver) browserdriver).setFileDetector(new LocalFileDetector());
			} else {
				options.setProfile(profile);
				options.setCapability("marionette", true);
				WebDriverManager.firefoxdriver().setup();
				browserdriver = new FirefoxDriver(options);
			}
		}
		return browserdriver;
	}

	/**
	 * Quit the Driver after each run
	 */
	@After
	public static void teardown(Scenario scenario) throws IOException {
		if(scenario.isFailed()) {
			scenario.attach(Screenshot.getScreenshot(), "image/png", "screenshot");
		}
		DriverManager.getDriver().quit();

	}
}
