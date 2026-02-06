/*
 * package utility;
 * 
 * import org.testng.ITestContext; import org.testng.ITestListener; import
 * org.testng.ITestResult;
 * 
 * import com.aventstack.extentreports.ExtentReports; import
 * com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.MediaEntityBuilder; import
 * com.aventstack.extentreports.Status; import
 * com.aventstack.extentreports.markuputils.ExtentColor; import
 * com.aventstack.extentreports.markuputils.MarkupHelper;
 * 
 * import action.Action; import baseClass.BaseClass;
 * 
 *//**
	 * @author user
	 *
	 *//*
		 * public class ListenerClass extends Extent implements ITestListener {
		 * 
		 * 
		 * @Override public void onTestStart(ITestResult result) { test =
		 * extent.createTest(result.getName()); //test.createNode(result.getName()); }
		 * 
		 * @Override public void onTestSuccess(ITestResult result) { if
		 * (result.getStatus() == ITestResult.SUCCESS) { //test.log(Status.PASS,
		 * "Pass Test case is: " + result.getName()); test.log(Status.PASS,
		 * MarkupHelper.createLabel("Test Case PASSED : " + result.getName(),
		 * ExtentColor.GREEN)); } }
		 * 
		 * @Override public void onTestFailure(ITestResult result) { if
		 * (result.getStatus() == ITestResult.FAILURE) { try { test.log(Status.FAIL,
		 * MarkupHelper.createLabel(result.getName() + " - Test Case Failed",
		 * ExtentColor.RED)); test.log(Status.FAIL,
		 * MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed",
		 * ExtentColor.RED)); // Screenshot static //String imgPath =
		 * Action.screenShot(Action.driver, result.getName()); String imgPath =
		 * Action.screenShot(BaseClass.driver, result.getName());
		 * test.fail("ScreenShot is Attached",
		 * MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
		 * //test.addScreenCaptureFromPath(Action.screenShot(BaseClass.driver,
		 * result.getName())); String screenshotName = result.getName();
		 * System.out.println("This is the Name Of the screenshot");
		 * System.out.println(screenshotName);
		 * //test.fail(action.screenShot(BaseClass.driver , result.getName()));
		 * //test.addScreencastFromPath("//eclipse-workspace/GahCom/ScreenShots");
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } }
		 * 
		 * @Override public void onTestSkipped(ITestResult result) { if
		 * (result.getStatus() == ITestResult.SKIP) { test.log(Status.SKIP,
		 * "Skipped Test case is: " + result.getName()); } }
		 * 
		 * @Override public void onTestFailedButWithinSuccessPercentage(ITestResult
		 * result) { // TODO Auto-generated method stub }
		 * 
		 * @Override public void onStart(ITestContext context) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void onFinish(ITestContext context) { // TODO Auto-generated
		 * method stub }
		 * 
		 * }
		 * 
		 */