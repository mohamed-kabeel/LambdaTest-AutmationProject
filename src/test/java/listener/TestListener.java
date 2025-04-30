package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentManager;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getExtentReports();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test Passed");
        extent.flush();

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());
        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip(result.getThrowable());
        extent.flush();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        extent.flush();
    }
}
