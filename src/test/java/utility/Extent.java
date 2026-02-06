/*
 * package utility;
 * 
 * import java.io.IOException; import java.text.SimpleDateFormat; import
 * java.util.Date;
 * 
 * import org.testng.ITestContext;
 * 
 * import com.aventstack.extentreports.ExtentReports; import
 * com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.reporter.ExtentSparkReporter; import
 * com.aventstack.extentreports.reporter.configuration.Theme;
 * 
 * public class Extent { public static ExtentSparkReporter htmlReporter; public
 * static ExtentReports extent; public static ExtentTest test;
 * 
 * public static String getSuiteName(ITestContext context){ String a =
 * context.getCurrentXmlTest().getSuite().getName(); Log.info(">>>>>>>>> "+ a);
 * return a; }
 * 
 * 
 * public static void setExtent() throws IOException { //String TestSuitName =
 * a.getCurrentXmlTest().getSuite().getName();
 * 
 * 
 * String dateName = new
 * SimpleDateFormat("EEE, MMM d, yyyy ,hh mm aaa").format(new Date());
 * //htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+
 * "/test-output/ExtentReport/"+"MyReport_"+BaseClass.getCurrentTime()+".html");
 * htmlReporter= new ExtentSparkReporter(System.getProperty("user.dir")+
 * "\\test-output\\ExtentReport" + dateName+".html");
 * 
 * htmlReporter.loadXMLConfig(System.getProperty("user.dir")+
 * "/extent-config.xml");
 * htmlReporter.config().setDocumentTitle("NewTown Parking Project");
 * htmlReporter.config().setReportName("NewTown Test Report");
 * htmlReporter.config().setTheme(Theme.DARK);
 * 
 * extent = new ExtentReports(); extent.attachReporter(htmlReporter);
 * 
 * //extent.setSystemInfo("HostName", "MyHost");
 * extent.setSystemInfo("ProjectName", "NewTown Parking Project");
 * extent.setSystemInfo("Tester", "Tejal Shinde"); extent.setSystemInfo("OS",
 * "Window"); extent.setSystemInfo("Browser", "Chrome");
 * 
 * } public static void endReport() {
 * 
 * extent.flush(); }
 * 
 * }
 */