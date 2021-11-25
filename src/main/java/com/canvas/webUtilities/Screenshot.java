package com.canvas.webUtilities;

import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.canvas.driverInit.DriverManager;

public class Screenshot {
	
	public static String screndir = System.getProperty("user.dir")+"/images/screenshots/screens.png"; 
	
	public static byte[] getScreenshot() throws IOException {
			 byte[] src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			    return src;
	}

	
}
