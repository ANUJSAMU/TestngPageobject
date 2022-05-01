package common;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.Instant;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	public static String getScreenshot(WebDriver driver) throws IOException {
		String screenshotFilePath;
		String currDir = System.getProperty("user.dir");
		screenshotFilePath= currDir+ "/screenshoots/screenshoots_"+Utilities.timeStamp()+".png";
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile , new File(screenshotFilePath));
		
		return screenshotFilePath;
	}
	public static String timeStamp() {
		Instant instant= Instant.now();
		return instant.toString().replace("-","_").replace(":", "_").replace(".","_"); 

	}

}
