package listener;

import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;
import utilities.Log;

public class IInvokedMethodListener implements org.testng.IInvokedMethodListener {
    @Override
    public  void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }
    @Override
   public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        switch (testResult.getStatus()){
            case ITestResult.FAILURE:
                Log.info("TestCase" + testResult.getName()+"failed");
                break;
            case ITestResult.SUCCESS:
                Log.info("TestCase"+testResult.getName()+"passed");
                break;
            case ITestResult.SKIP:
                Log.info("TestCase"+testResult.getName()+"skip");
                break;
        }
    }


}
