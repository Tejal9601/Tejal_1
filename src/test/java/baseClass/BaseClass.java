package baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import action.Action;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;

	@BeforeSuite
	public void loadConfig() throws IOException {

		 // Extent.setExtent();

//		DOMConfigurator.configure("log4j.xml");
		prop = new Properties();
		try {
			InputStream ip = getClass()
			        .getClassLoader()
			        .getResourceAsStream("config.properties");
			// System.getProperty("\\Configuration\\config.properties"));
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void launchApp() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--no-sandbox");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		String browserName = prop.getProperty("browser");
		System.out.println("Lunching Browser name :" + browserName);

		if (browserName.equalsIgnoreCase("Chrome")) {

			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		Action.implicitWait(driver, 10);
		Action.pageLoadTimeOut(driver, 30);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	public void launchUserSignUpPage() {
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--no-sandbox");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		String browsers = prop.getProperty("browser");
		System.out.println("Lunching Browser name :" + browsers);
		if (browsers.equalsIgnoreCase("Chrome")) {

			driver = new ChromeDriver(options);
		} else if (browsers.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		Action.implicitWait(driver, 10);
		Action.pageLoadTimeOut(driver, 30);
		driver.get(prop.getProperty("userUrl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		//Extent.endReport();
	}
}