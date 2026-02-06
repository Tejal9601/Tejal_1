package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import baseClass.BaseClass;
//import utility.Extent;
import utility.Log;

public class Action extends BaseClass implements ITestListener {
	ExtentReports extent;
	static ExtentTest test;
//	Extent manager;

	public static void mouseClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		waitForVisible(driver, element);
		act.moveToElement(element);
		// Perform mouse click action
		act.click().perform();
	}

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// Method to print text with color
	public static void printWithColor(String text, String color) {
		System.out.println(color + text + ANSI_RESET);
	}

	public static void waitAndClick(WebElement element, String description) {
		Action.waitForClickable(driver, element);
		element.click();
		System.out.println(description);
	//	test = Extent.test.info(description);
		Log.info(description);
	}

	public static void waitAndSendKeys(WebElement element, String value, String Description) {
		Action.waitForVisible(driver, element);
		element.sendKeys(value);
		System.out.println(Description);
	//	test = Extent.test.info(Description);
		Log.info(Description);
	}

	public static boolean moveToElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions = new Actions(driver);
			// actions.moveToElement(driver.findElement(locator)).build().perform();
			actions.moveToElement(ele).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void scrollBy(WebDriver driver, int xOffset, int yOffset) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + xOffset + "," + yOffset + ")");
	}

	public static String Recent_File_in_Folder(String dirPath) {

		File dir = new File(dirPath);

		File[] files = dir.listFiles();

		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile.getAbsolutePath();

	}

	public static void save_data(String value) throws IOException {

		String path = System.getProperty("user.dir") + "\\Resources\\test.txt";
		FileWriter file = new FileWriter(path);
		file.write(value + System.lineSeparator());

		file.flush();

	}

	public static void scrollToElementRight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center', inline: 'end' });",
				element);
	}

	public static String selectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		return select.getOptions().get(index).getText();
	}

	public static void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'nearest'});",
				element);
	}

	public static void scrollToHeight(WebDriver driver, int height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, arguments[0]);", height);
	}

	private static final Calendar endDate = new GregorianCalendar(2024, Calendar.MAY, 16);

	public static String RandomStartDate() {
		// Create an instance of the Random class
		Random random = new Random();

		// Define a start date
		Calendar startDate = new GregorianCalendar(2024, Calendar.MAY, 20);

		// Calculate the difference in milliseconds between the start and end dates
		long startMillis = startDate.getTimeInMillis();

		// Generate a random number between 0 and the number of days in the range
		long randomMillis = startMillis
				+ (long) (random.nextDouble() * ((endDate.getTimeInMillis() - startMillis) + 1));

		// Create a Calendar instance with the random date
		Calendar randomDate = new GregorianCalendar();
		randomDate.setTimeInMillis(randomMillis);

		// Extract year, month, and day from the random date
		int year = randomDate.get(Calendar.YEAR);
		int month = randomDate.get(Calendar.MONTH) + 1; // Month is 0-based, so add 1
		int day = randomDate.get(Calendar.DAY_OF_MONTH);

		// Format the date
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = String.format("%02d-%02d-%04d", day, month, year);

		return formattedDate;
	}

	public static String RandomTime() {
		// Create an instance of the Random class
		Random random = new Random();

		// Define a start time (00:00:00)
		Calendar startTime = new GregorianCalendar();
		startTime.set(Calendar.HOUR_OF_DAY, 0);
		startTime.set(Calendar.MINUTE, 0);
		startTime.set(Calendar.SECOND, 0);

		// Define an end time (23:59:59)
		Calendar endTime = new GregorianCalendar();
		endTime.set(Calendar.HOUR_OF_DAY, 23);
		endTime.set(Calendar.MINUTE, 59);
		endTime.set(Calendar.SECOND, 59);

		// Calculate the difference in milliseconds between the start and end times (24
		// hours)
		long startMillis = startTime.getTimeInMillis();
		long endMillis = endTime.getTimeInMillis();

		// Generate a random number between 0 and the number of milliseconds in the
		// range (24 hours)
		long randomMillis = startMillis + (long) (random.nextDouble() * (endMillis - startMillis));

		// Create a Calendar instance with the random time
		Calendar randomTime = new GregorianCalendar();
		randomTime.setTimeInMillis(randomMillis);

		// Extract hour and minute from the random time
		int hour = randomTime.get(Calendar.HOUR_OF_DAY);
		int minute = randomTime.get(Calendar.MINUTE);

		// Format the time
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		String formattedTime = timeFormat.format(randomTime.getTime());

		return formattedTime;
	}

	public static String RandomEndDate(String startDateString) throws ParseException {
		// Create an instance of the Random class
		Random random = new Random();

		// Define the start date and end date as the last possible date in the range
		Calendar startDate = new GregorianCalendar();
		startDate.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(startDateString));
		Calendar endDate = new GregorianCalendar(2024, Calendar.FEBRUARY, 30);

		// Calculate the difference in milliseconds between the start and end dates
		long startMillis = startDate.getTimeInMillis();
		long endMillis = endDate.getTimeInMillis();

		// Generate a random number between the start and end dates
		long randomMillis = startMillis + (long) (random.nextDouble() * (endMillis - startMillis));

		// Create a Calendar instance with the random date
		Calendar randomDate = new GregorianCalendar();
		randomDate.setTimeInMillis(randomMillis);

		// Extract year, month, and day from the random date
		int year = randomDate.get(Calendar.YEAR);
		int month = randomDate.get(Calendar.MONTH) + 1; // Month is 0-based, so add 1
		int day = randomDate.get(Calendar.DAY_OF_MONTH);

		// Format the date
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = String.format("%02d-%02d-%04d", day, month, year);

		return formattedDate;
	}

	public static String RandomDateTime() {
		// Create an instance of the Random class
		Random random = new Random();

		// Define a date range (start and end dates)
		Calendar startDate = new GregorianCalendar(2025, Calendar.JANUARY, 02);
		Calendar endDate = new GregorianCalendar(2025, Calendar.DECEMBER, 30);

		// Calculate the difference in milliseconds between the start and end dates
		long startMillis = startDate.getTimeInMillis();
		long endMillis = endDate.getTimeInMillis();
		long randomMillis = startMillis + (long) (random.nextDouble() * (endMillis - startMillis));

		// Create a Calendar instance with the random date
		Calendar randomDate = new GregorianCalendar();
		randomDate.setTimeInMillis(randomMillis);

		// Extract year, month, day, hour, minute, and second from the random date
		int year = randomDate.get(Calendar.YEAR);
		int month = randomDate.get(Calendar.MONTH) + 1; // Month is 0-based, so add 1
		int day = randomDate.get(Calendar.DAY_OF_MONTH);
		int hour = randomDate.get(Calendar.HOUR_OF_DAY);
		int minute = randomDate.get(Calendar.MINUTE);

		// Format the date and time
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
		String formattedDateTime = String.format("%02d-%02d-%04d" + Keys.ARROW_RIGHT + "T%02d:%02d", day, month, year,
				hour, minute);

		return formattedDateTime;
	}

	public static void TestLog(String str) {
		Reporter.log("<p style=\"color:blue; font-size:20px; font-weight:bold;\">" + str + "</p>");
	}

	public static void screencapture() throws IOException {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String pageTitle = driver.getTitle().replace("|", "");
		File screenshotName = new File(
				"C:\\Users\\TEJAL.SHINDE\\Downloads\\Screenshot_Selenium\\" + pageTitle + ".png");
		FileUtils.copyFile(screenshotFile, screenshotName);
		Reporter.log(
				"<br> <img src='file:///" + screenshotName.getAbsolutePath() + "' height='400' width='800' /><br>");
	}

	static Workbook workbook;

	public static void writeExcelDataInputFile(String Inputpath) throws IOException {

		FileInputStream fis = new FileInputStream(Inputpath);

		workbook = new XSSFWorkbook(fis);

	}

	public static String getCurrentDate(String format) {

		LocalDate currentDate = LocalDate.now();

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

		String formattedCurrentDate = currentDate.format(dateFormatter);

		return formattedCurrentDate;

	}

	public static void writeExcelDataOutputFile(String OutputPath) throws IOException {

		FileOutputStream out = new FileOutputStream(new File(OutputPath));

		workbook.write(out);
		out.close();

	}

	public static boolean isWordPresent(String sentence, String word) {

		String[] s = sentence.split(" ");

		// To temporarily store each individual word
		for (String temp : s) {

			// Comparing the current word
			// with the word to be searched
			if (temp.compareTo(word) == 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean isServicePresent(String sentence, String word) {

		String[] s = sentence.split(" ");

		// To temporarily store each individual word
		for (String temp : s) {

			// Comparing the current word
			// with the word to be searched
			if (temp.compareTo(word) == 0) {
				Log.info("Service Present");

				return true;
			}
		}
		Log.info("Service Not Present");
		return false;
	}

	/*
	 * public static boolean isSubjectPresent(String sentence, String word) {
	 * 
	 * String[] s = sentence.split(" ");
	 * 
	 * // To temporarily store each individual word for (String temp : s) {
	 * 
	 * // Comparing the current word // with the word to be searched if
	 * (temp.compareTo(word) == 0) { Log.info("Subject Present"); test =
	 * Extent.test.log(Status.PASS, "Subject Present");
	 * 
	 * return true; } }
	 * 
	 * Log.info("Subject Not Present");
	 * 
	 * test = Extent.test.log(Status.FAIL,
	 * MarkupHelper.createLabel("Subject Not Present", ExtentColor.WHITE));
	 * 
	 * return false; }
	 */
	public static boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			// System.out.println("Location not found: "+locatorName);
			flag = false;
		} finally {
			if (flag) {
				Log.info("Successfully Found element at");

			} else {
				Log.info("Unable to locate element at");
			}
		}
		return flag;
	}

	public static boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				Log.info("The element is Displayed");
			} else {
				Log.info("The element is not Displayed");

			}
		} else {

			Log.info("Not displayed");

		}
		return flag;
	}

	public boolean isSelected(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				Log.info("The element is Selected");
			} else {
				Log.info("The element is not Selected");
			}
		} else {
			Log.info("Not selected ");
		}
		return flag;
	}

	public boolean isEnabled(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isEnabled();
			if (flag) {
				Log.info("The element is Enabled");
			} else {
				Log.info("The element is not Enabled");
			}
		} else {
			Log.info("Not Enabled ");
		}
		return flag;
	}

	public static boolean type(WebElement ele, String text) {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);

			Log.info("Entered text........... :" + text);
			flag = true;
		} catch (Exception e) {
			Log.info("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				Log.info("Successfully entered value");
			} else {
				Log.info("Unable to enter value");
			}

		}
		return flag;
	}

	public boolean selectBySendkeys(String value, WebElement ele) {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				Log.info("Select value from the DropDown");
			} else {
				Log.info("Not Selected value from the DropDown");
				// throw new ElementNotFoundException("", "", "")
			}
		}
	}

	public boolean selectByValue(WebElement element, String value) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				Log.info("Option selected by Value");
			} else {
				Log.info("Option not selected by Value");
			}
		}
	}

	public boolean selectByVisibleText(String visibletext, WebElement ele) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				Log.info("Option selected by VisibleText");
			} else {
				Log.info("Option not selected by VisibleText");
			}
		}
	}

	public static void jsClick(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			// Handle exceptions if necessary
			e.printStackTrace();
		}
	}

	public boolean switchToFrameByIndex(WebDriver driver, int index) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				Log.info("Frame with index \"" + index + "\" is selected");
			} else {
				Log.info("Frame with index \"" + index + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 *
	 * @param idValue : Frame ID wish to switch
	 *
	 */

	public boolean switchToFrameById(WebDriver driver, String idValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				Log.info("Frame with Id \"" + idValue + "\" is selected");
			} else {
				Log.info("Frame with Id \"" + idValue + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 *
	 * @param nameValue : Frame Name wish to switch
	 *
	 */

	public boolean switchToFrameByName(WebDriver driver, String nameValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				Log.info("Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				Log.info("Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}

	public boolean switchToDefaultFrame(WebDriver driver) {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				// SuccessReport("SelectFrame ","Frame with Name is selected");
			} else if (!flag) {
				// failureReport("SelectFrame ","The Frame is not selected");
			}
		}
	}

	public boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count) {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();

			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count - 1]);

			if (driver.getTitle().contains(windowTitle)) {
				flag = true;
			} else {
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			// flag = true;
			return false;
		} finally {
			if (flag) {
				Log.info("Navigated to the window with title");
			} else {
				Log.info("The Window with title is not Selected");
			}
		}
	}

	public static boolean switchToNewWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				Log.info("Window is Navigated with title");
			} else {
				Log.info("The Window with title: is not Selected");
			}
		}
	}

	public boolean switchToOldWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				Log.info("Focus navigated to the window with title");
			} else {
				Log.info("The Window with title: is not Selected");
			}
		}
	}

	public int getColumncount(WebElement row) {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			Log.info(column.getText());
			Log.info("|");
		}
		return a;
	}

	public int getRowCount(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	/**
	 * Verify alert present or not
	 *
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 *
	 */

	public boolean Alert(WebDriver driver) {
		boolean presentFlag = false;
		// Alert alert = null imported package by shubham
		Alert alert = null;

		try {
			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert and also did changes on this line
			alert.accept();
//			presentFlag t = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				Log.info("The Alert is handled successfully");
			} else {
				Log.info("There was no alert to handle");
			}
		}

		return presentFlag;
	}

	public boolean launchUrl(WebDriver driver, String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				Log.info("Successfully launched \"" + url + "\"");
			} else {
				Log.info("Failed to launch \"" + url + "\"");
			}
		}
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}

	public String getTitle(WebDriver driver) {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			Log.info("Title of the page is: \"" + text + "\"");
		}
		return text;
	}

	public static String getCurrentURL(WebDriver driver) {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			Log.info("Current URL is: \"" + text + "\"");
		}
		return text;
	}

	public static boolean click1(WebElement locator, String locatorName) {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				Log.info("Able to click on \"" + locatorName + "\"");
			} else {
				Log.info("Click Unable to click on \"" + locatorName + "\"");
			}
		}

	}

//	public static void fluentWait(WebDriver driver,WebElement element, int timeOut) {
//	    Wait<WebDriver> wait = null;
//	    try {
//	        wait = new FluentWait<WebDriver>((WebDriver) driver)
//	        		.withTimeout(20, int)
//	        	    .pollingEvery(Duration.ofSeconds(2))
//	        	    .ignoring(Exception.class);
//	        wait.until(ExpectedConditions.visibilityOf(element));
//	        element.click();
//	    }catch(Exception e) {
//	    }
//	}

	public static void implicitWait(WebDriver driver, int timeOut) {

		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public void explicitWait(WebDriver driver, WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
	}

	// ******************************************************************************************************************
	public static String getText(WebElement element) {
		boolean flag = false;

		String text = element.getText();
		if (flag) {
			Log.info("Current Entered text is: \"" + text + "\"");
		}
		return text;
	}

	public static String waitForVisibleAndGetText(WebDriver driver, WebElement element) {
		try {
			Thread.sleep(1000);
			Log.info("Waiting for element visibility");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String text = element.getText();
		System.out.println("Text Displayed : " + text);
		return text;
	}

	// Wait until the element is clickable
	public static void waitForClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForVisible(WebDriver driver, WebElement element) {
		try {
			Thread.sleep(1000);
			Log.info("Waiting for element visibility");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void waitForVisibleAndClick(WebDriver driver, WebElement element) {
		try {
			Thread.sleep(1000);
			Log.info("Waiting for element visibility");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(element)).click();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//******************************************************************************************************************
	public static String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/ScreenShots/" + filename + "_" + dateName + ".png";
		// String destination = System.getProperty("user.dir") + "/GahCom/ScreenShots/"
		// + filename + "_" + dateName + ".png";

		Log.info("This is the Path of the Screenshhot : " + destination);

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}

		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename
				+ "_" + dateName + ".png";
		return destination;

		// return newImageString;

	}

	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}

	// *******************************************************************************************************************
}
