package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersImplementation extends ExtentReportManager implements ITestListener {
    public static ExtentTest test;

    public void onTestStart(ITestResult result) {
        String className = result.getMethod().getRealClass().getName();
        test = report.createTest(className + "." + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
// TODO Auto-generated method stub


        test.pass("Test Passed: " + result.getName());
        System.out.println("Success of test cases and its details are : " + result.getName());
    }


    public void onTestFailure(ITestResult result) {
// TODO Auto-generated method stub
        System.out.println("Failure of test cases and its details are : " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
// TODO Auto-generated method stub
        test.log(Status.SKIP, result.getThrowable());
        test.log(Status.SKIP, result.getMethod().getMethodName());
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.YELLOW));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
// TODO Auto-generated method stub
        System.out.println("Failure of test cases and its details are : " + result.getName());
    }

    public void onStart(ITestContext context) {
// TODO Auto-generated method stub
        report = ExtentReportManager.getReport();
    }

    public void onFinish(ITestContext context) {
// TODO Auto-generated method stub
        endReport();
        report.flush();
    }
}
