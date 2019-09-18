package com.testautothon.Listeners;

import com.testautothon.ExtentReports.ExtentManager;
import com.testautothon.ExtentReports.ExtentTestManager;
import com.testautothon.jira.JiraActivities;
import com.testautothon.utils.Testautothon;
import com.relevantcodes.extentreports.LogStatus;
import org.ini4j.Profile;
import org.ini4j.Wini;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class TestListener extends Testautothon implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private static String getTestClassName(ITestResult iTestResult) {
        return iTestResult.getClass().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", sedriver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("\nTest case Started: " + getTestMethodName(iTestResult));
        String packName = (iTestResult.getTestClass().getName() + " -- " + iTestResult.getMethod().getMethodName());
        String desc = iTestResult.getMethod().getDescription();
        System.out.println("TestCase Description is:"+desc);
        ExtentTestManager.startTest(packName.substring(packName.indexOf("tests."), packName.length()), desc);
       
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        if (getTestClassName(iTestResult).startsWith("w"))
            ExtentTestManager.getTest().log(LogStatus.PASS, "Wow !!! Test passed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        Object testClass = iTestResult.getInstance();

        WebDriver webDriver;

         String desc = iTestResult.getMethod().getDescription();
        if (desc.equalsIgnoreCase("mobile"))
            webDriver = ((Testautothon) testClass).getApDriver();
        else
            webDriver = ((Testautothon) testClass).getSeDriver();

        System.out.println("WebDriver variable value::"+webDriver);
        String base64Screenshot = "data:image/png;base64,"
                + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

        File screenPrintJira = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        String trace;

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        Throwable cause = iTestResult.getThrowable();

        if (null != cause) {

            if (cause.getClass().getName().equalsIgnoreCase("java.lang.AssertionError")) {
                trace = cause.getMessage();

               
            } else if (cause.getClass().getName().equalsIgnoreCase("org.openqa.selenium.TimeoutException")) {


                cause.printStackTrace(pw);
                trace = sw.getBuffer().toString();

            } else {

                cause.printStackTrace(pw);
                trace = sw.getBuffer().toString();
            }
        } else {
            trace = "Test failed.";
        }

        System.out.println("trace: " + trace);

        ExtentTestManager.getTest().log(LogStatus.FAIL, trace.replace("\n", "<br>"),
                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(LogStatus.SKIP, "I don't know why but test skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}