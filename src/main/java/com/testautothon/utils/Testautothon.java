package com.testautothon.utils;

import com.testautothon.mobile.AppEnv;
import com.testautothon.mobile.DeviceProp;
import com.testautothon.mobile.Initialize;
import com.testautothon.web.SeleniumDriverManager;
import io.appium.java_client.AppiumDriver;
import org.ini4j.Wini;
import org.openqa.selenium.WebDriver;
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
    public void setUp(@Optional String browser, @Optional String baseUrl,
                      @Optional String appActivity, @Optional String appPackage) throws IOException {


        String device = (System.getProperty("device") == null) ? "ALL" : System.getProperty("device");

        Testautothon.browser = browser;
        Testautothon.baseUrl = baseUrl;
       

        appEnv.setDeviceType(DeviceType.ANDROID);

        sedriver = SeleniumDriverManager.createBrowserInstance(browser, baseUrl);

        deviceProps = initialize.bringEnvironmentUp(DeviceEnvironment.valueOf(device), appEnv, false);

    }


    @AfterSuite(alwaysRun = true)
    public void killEmulators() {

        Wini ini = null;

        try {
            ini = new Wini(new File("./res/runningEmulators.ini"));

            for (DeviceProp deviceProp : deviceProps) {
                if (!deviceProp.isRealDevice()) {

                    System.out.println("Bringing the emulator '" + deviceProp.getName() + "' down. Please wait...");

                    BufferedReader r = null;

                    switch (deviceProp.getDeviceType()) {
                        case ANDROID:
                            Process p1 = Runtime.getRuntime()
                                    .exec("adb -s " + deviceProp.getEmulatorDeviceName() + " emu kill");
                            r = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                            break;

                        case IOS:
                            Process p2 = Runtime.getRuntime().exec("killall Simulator");
                            r = new BufferedReader(new InputStreamReader(p2.getInputStream()));
                            break;

                    }

                    while (true) {

                        String emulatorline = r.readLine();
                        if (emulatorline == null) {
                            ini.put(deviceProp.getName(), "isEmulatorUp", false);
                            ini.store();
                            break;
                        }
                    }

                }

            }

        } catch (IOException | NullPointerException e1) {
        }

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
