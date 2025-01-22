package com.e2etemplate.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.e2etemplate.report.ExtentManager;
import com.e2etemplate.utils.ConfigProperties;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestBase {
    private static ExtentReports extent;
    private static ExtentTest test;
    private final Integer defaultTimeout;
    private final Boolean headless;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Getter
    public Page page;
    private Browser browser;
    private Playwright playwright;
    private BrowserContext context;

    public TestBase() {
        defaultTimeout = ConfigProperties.getIntProperty("default.timeout");
        headless = ConfigProperties.getBooleanProperty("headless");
    }

    @BeforeClass
    public void launchBrowser() {
        extent = ExtentManager.createInstance();
//        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
    }

    @AfterClass
    public void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
        ExtentManager.flush();
    }

    @BeforeMethod
    public void createContextAndPage(Method method) {
        String testName = method.getName();
        Test testAnnotation = method.getAnnotation(Test.class);
        String testDescription = testAnnotation.description();
        test = extent.createTest(testName, testDescription);
        ExtentManager.setTest(test);
        logger.info("=== Início do Teste: {} ===", testName);
        if (!testDescription.isEmpty()) {
            logger.info("Descrição do Teste: {}", testDescription);
        }
//        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        context = browser.newContext();
        page = context.newPage();
        page.setDefaultTimeout(defaultTimeout);
        page.setDefaultNavigationTimeout(defaultTimeout);
    }

    @AfterMethod
    public void closeContext(Method method) {
        String testName = method.getName();
        logger.info("=== Fim do Teste: {} ===", testName);
        if (context != null) {
            context.close();
        }
    }
}
