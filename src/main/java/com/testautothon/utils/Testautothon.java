package com.testautothon.utils;

import com.testautothon.mobile.AppEnv;
import com.testautothon.mobile.DeviceProp;
import com.testautothon.mobile.Initialize;
import com.testautothon.web.SeleniumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.ini4j.Wini;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Testautothon {

    public static String baseUrl, browser;

    public static WebDriver sedriver;
    public static AppiumDriver<?> apdriver;

    public static ArrayList<DeviceProp> deviceProps;

    public Initialize initialize = new Initialize();
    public SoftAssert softAssert = new SoftAssert();
    public Services services = new Services();
    public static AppEnv appEnv = new AppEnv();


    @Parameters({"browser", "baseUrl"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(@Optional String browser, @Optional String baseUrl) throws IOException {


        String device = (System.getProperty("device") == null) ? "ALL" : System.getProperty("device");

        Testautothon.browser = browser;
        Testautothon.baseUrl = baseUrl;
       

//        appEnv.setDeviceType(DeviceType.ANDROID);

        sedriver = SeleniumDriverManager.createBrowserInstance(browser, baseUrl);

//        deviceProps = initialize.bringEnvironmentUp(DeviceEnvironment.valueOf(device), appEnv, false);
       /* DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Android SDK built for x86");
		caps.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("noReset", true);
		
		try {
			apdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}*/
    
        
    }


    @AfterSuite(alwaysRun = true)
    public void killDrivers() {

        if (sedriver != null) {
            sedriver.quit();
            if (sedriver != null) {
                sedriver = null;
            }
        }

    }

    public void resetAfterClassWeb() {
        if (sedriver != null) {
            sedriver.quit();
            if (sedriver != null) {
                sedriver = null;
            }
        }
    }

    public WebDriver getSeDriver() {

        return sedriver;
    }

    public WebDriver getApDriver() {

        return apdriver;
    }

}
