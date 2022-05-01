package testngPageobject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import common.ExcelReader;
import common.ReportLog;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GoogleSearchPage;
import pages.GoogleSearchResultPage;


public class TestCase1 {
	
	WebDriver driver;
	GoogleSearchPage googleSearch ;
	GoogleSearchResultPage googlesearchresults;
	ReportLog logger;
	
	@BeforeSuite
	public void startTestSuite() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		logger= new ReportLog(driver);

	}
	
	@BeforeMethod
	public void startTest(Method m) {
		logger.startTest(m.getName());
		driver.get("https://google.com");
		driver.manage().window().maximize();

	}

	@Test
    public void exampleOfTestNgMaven() {
        System.out.println("This is TestNG-Maven Example");
    }
	
	@Test(dataProvider = "scenarioClaim")
	public  void GoogleSearch_automationScript(String searchText) throws IOException {

		System.out.println(searchText);
		googleSearch= new GoogleSearchPage(driver);
		googlesearchresults= new GoogleSearchResultPage(driver);		
		googleSearch.searchKeyword(searchText);			
		boolean isDisplayed= googlesearchresults.isLinkDisplayed("anuj");		
		if(isDisplayed==true) {
			logger.logPass("automationScript Link is displayed on Google Search results page 1");
		}else {
			logger.logFail("automationScript Link is not displayed on Google Search results page 1");

		}
		String firstLinkText= googlesearchresults.getFirstResult().getAttribute("innerText");		
		if(firstLinkText.contains("automationScript")) {
			logger.logPass("automationScript Link is displayed as 1st link on Google Search results page 1");
		}else {
			logger.logFail("automationScript Link is not displayed as 1st link on Google Search results page 1");

		}
	}
	
	@Test(dataProvider = "scenarioClaim")
	public  void GoogleSearchExpected(Map<String, String> input) throws IOException {

		System.out.println(input.get("searchText"));
		
		googleSearch= new GoogleSearchPage(driver);
		googlesearchresults= new GoogleSearchResultPage(driver);		
		googleSearch.searchKeyword(input.get("searchText"));			
		boolean isDisplayed= googlesearchresults.isLinkDisplayed(input.get("searchText"));		
		if(isDisplayed==true) {
			logger.logPass("automationScript Link is displayed on Google Search results page 1");
		}else {
			logger.logFail("automationScript Link is not displayed on Google Search results page 1");

		}
		String firstLinkText= googlesearchresults.getFirstResult().getAttribute("innerText");		
		if(firstLinkText.contains("automationScript")) {
			logger.logPass("automationScript Link is displayed as 1st link on Google Search results page 1");
		}else {
			logger.logFail("automationScript Link is not displayed as 1st link on Google Search results page 1");

		}
	
	}
	
	@DataProvider (name = "scenarioClaim")
	public Object[][] dpMethod(Method m) throws IOException  {
		String currDir = System.getProperty("user.dir");
		switch (m.getName()) {
		case "GoogleSearch_automationScript":
			Object [][] arrObj = ExcelReader.getExcelData(currDir+"/src/main/resources/testData/TC1.xlsx", "Sheet1");
		    return arrObj;
		case "GoogleSearchExpected":
			Object [][] arrEObj = ExcelReader.getExcelDataHash(currDir+"/src/main/resources/testData/TC2.xlsx", "Sheet1");
		    return arrEObj;
		}
		return null;
		
//		Object [][] arrObj = ExcelReader.getExcelData(currDir+"/src/main/resources/testData/TC1.xlsx", "Sheet1");
//	    return arrObj;
//		
		
	}
	
	
	@AfterMethod
	public void endTest() {
		logger.endTest();

	}

	@AfterSuite
	public void endTestSuite() {
		logger.endTestSuite();
		driver.quit();
	}
}
