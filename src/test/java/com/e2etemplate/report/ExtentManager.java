package com.e2etemplate.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = "target/reports/E2E_TestReport_" + timestamp + "/E2E_TestReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setEncoding("utf-8");
            sparkReporter.config().setDocumentTitle("E2E Test Report");
            sparkReporter.config().setReportName("E2E Automation Results");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Tester", "QA Team");
            extent.setSystemInfo("Environment", "QAS");
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void setTest(ExtentTest test) {
        testThread.set(test);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
