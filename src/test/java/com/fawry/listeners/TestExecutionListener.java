package com.fawry.listeners;

import com.fawry.utilities.ExcelReader;
import com.fawry.utilities.TestResult;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static com.fawry.tests.base.TestBase.driver;

public class TestExecutionListener implements ITestListener, ITest {

    private String suiteName;
    //    private LocalDateTime startTime;
    private final ZoneId egyptZone = ZoneId.of("Africa/Cairo");
    private LocalDateTime startTime;
    private int totalTests;
    private int passedTests;
    private int failedTests;
    private int skippedTests;
    private final List<TestResult> passedTestResults = new ArrayList<>();
    private final List<TestResult> failedTestResults = new ArrayList<>();
    private final List<TestResult> skippedTestResults = new ArrayList<>();
    private final ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeMethod
    public void setTestName(Method m, Object[] testData) {
        if (testData.length > 0)
        {
            Object element = testData[0];
            Object[] innerArray = (Object[]) element;
            testName.set(innerArray[1].toString());//test case name index in Excel sheet
        } else  {
            testName.set(m.getName());
        }
    }

    public String getTestName() {
        return testName.get();
    }

    @Override
    public void onStart(ITestContext context) {
        suiteName = context.getSuite().getName();
        startTime = LocalDateTime.now(egyptZone).plusHours(0);
    }


    @Override
    public void onFinish(ITestContext context) {
        TestResult testResult = new TestResult();
        LocalDateTime endTime = LocalDateTime.now(egyptZone).plusHours(0);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        Duration testDuration = Duration.between(startTime, endTime);

        String reportPath = "target/reports/report.html";
        try {
            File file = new File(reportPath);
            if (!file.exists()) {
                boolean success = file.createNewFile();
                if (!success) {
                    // Handle file creation failure
                    System.out.println("Error creating file at path: " + reportPath);
                }
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("<html><head><title>" + suiteName + " Test Report</title>");
            bw.write("<link href='https://fonts.googleapis.com/css?family=Maven+Pro' rel='stylesheet'>");
            bw.write("<style>body{background-color: #1e1e1e; color: #fff; font-family: 'Maven Pro', sans-serif; font-size: 16px;}");
            bw.write("table{border-collapse: collapse; width: 100%;}");
            bw.write("th, td{border: 1px solid #404040; padding: 8px;}");
            bw.write("th{background-color: #2d2d2d;}");
            bw.write("tr.passed{background-color: #557C55; color: #fff;}");
            bw.write("tr.failed{background-color: #b94646; color: #fff;}");
            bw.write("tr.skipped{background-color: #EA906C; color: #fff;}");
            bw.write("</style>");

            // JavaScript functions for toggling visibility
            bw.write("<script>");
            bw.write("function showTable(tableId) {");
            bw.write("var tables = document.getElementsByTagName('table');");
            bw.write("for (var i = 0; i < tables.length; i++) {");
            bw.write("tables[i].style.display = 'none';");
            bw.write("}");
            bw.write("document.getElementById(tableId).style.display = 'table';");
            bw.write("}");
            bw.write("</script>");

            // Styling for buttons
            bw.write("<style>");
            bw.write("button {");
            bw.write("margin: 5px;");
            bw.write("padding: 10px;");
            bw.write("border: 2px solid #3498db;");
            bw.write("border-radius: 5px;");
            bw.write("background-color: #3498db;");
            bw.write("color: #fff;");
            bw.write("cursor: pointer;");
            bw.write("}");
            bw.write("button:hover { background-color: #2980b9; }");
            bw.write("</style>");

            bw.write("</head><body>");

            bw.write("<style> h1 { font-size: 18px; font-weight: bold;} table { font-size: 14px; } </style>");
            bw.write("<h1>" + suiteName + " Test Report</h1>");
            bw.write("<p><strong>Started:</strong> " + startTime.format(dateFormatter) + ", " + startTime.format(timeFormatter) + "</p>");
            bw.write("<p><strong>Took:</strong> " + testResult.summaryFormatDuration(testDuration) + "</p>");
            bw.write("<p><strong>Total Tests:</strong> " + totalTests + "</p>");
            bw.write("<p><strong>Passed Tests:</strong> " + passedTests + "</p>");
            bw.write("<p><strong>Failed Tests:</strong> " + failedTests + "</p>");
            bw.write("<p><strong>Skipped Tests:</strong> " + skippedTests + "</p>");

            // Passed, Failed, and Skipped buttons
            bw.write("<button onclick=\"showTable('passedTable')\">Show Passed</button>");
            bw.write("<button onclick=\"showTable('failedTable')\">Show Failed</button>");
            bw.write("<button onclick=\"showTable('skippedTable')\">Show Skipped</button>");

            // Passed tests table
            if (passedTests > 0) {
                bw.write("<table id='passedTable' style='display:none;'>");
                bw.write("<caption>Passed Tests</caption>");
                bw.write("<tr>" +
                        "<th style='width:30%'>Test Case Name</th>" +
                        "<th style='width:7.5%'>Environment</th>" +
                        "<th style='width:7.5%'>Page</th>" +
                        "<th style='width:30%'>URL</th>" +
                        "<th style='width:5%'>Status</th>" +
                        "<th style='width:5%'>Duration</th>" +
                        "</tr>");
                for (TestResult result : passedTestResults) {
                    bw.write("<tr class='Passed'>" +
                            "<td>" + result.getName() + "</td>" +
                            "<td>" + result.getEnvironment() + "</td>" +
                            "<td>" + result.getPage() + "</td>" +
                            "<td>" + result.getURL() + "</td>" +
                            "<td>" + result.getStatus() + "</td>" +
                            "<td>" + result.getDuration() + "</td>" +
                            "</tr>");
                }
                bw.write("</table>");
            }

            // Failed tests table
            if (failedTests > 0) {
                bw.write("<table id='failedTable' style='display:none;'>");
                bw.write("<caption>Failed Tests</caption>");
                bw.write("<tr>" +
                        "<th style='width:30%'>Test Case Name</th>" +
                        "<th style='width:7.5%'>Environment</th>" +
                        "<th style='width:7.5%'>Page</th>" +
                        "<th style='width:30%'>URL</th>" +
                        "<th style='width:5%'>Status</th>" +
                        "<th style='width:5%'>Duration</th>" +
                        "</tr>");
                for (TestResult result : failedTestResults) {
                    bw.write("<tr class='Failed'>" +
                            "<td>" + result.getName() + "</td>" +
                            "<td>" + result.getEnvironment() + "</td>" +
                            "<td>" + result.getPage() + "</td>" +
                            "<td>" + result.getURL() + "</td>" +
                            "<td>" + result.getStatus() + "</td>" +
                            "<td>" + result.getDuration() + "</td>" +
                            "</tr>");
                }
                bw.write("</table>");
                bw.write("</table>");
            }

            // Skipped tests table
            if (skippedTests > 0) {
                bw.write("<table id='skippedTable' style='display:none;'>");
                bw.write("<caption>Skipped Tests</caption>");
                bw.write("<tr>" +
                        "<th style='width:30%'>Test Case Name</th>" +
                        "<th style='width:7.5%'>Environment</th>" +
                        "<th style='width:7.5%'>Page</th>" +
                        "<th style='width:30%'>URL</th>" +
                        "<th style='width:5%'>Status</th>" +
                        "<th style='width:5%'>Duration</th>" +
                        "</tr>");
                for (TestResult result : skippedTestResults) {
                    bw.write("<tr class='skipped'>" +
                            "<td>" + result.getName() + "</td>" +
                            "<td>" + result.getEnvironment() + "</td>" +
                            "<td>" + result.getPage() + "</td>" +
                            "<td>" + result.getURL() + "</td>" +
                            "<td>" + result.getStatus() + "</td>" +
                            "<td>" + result.getDuration() + "</td>" +
                            "</tr>");
                }
                bw.write("</table>");
            }

            bw.write("</body></html>");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onTestStart(ITestResult result) {
        // do nothing
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TestResult testResult = new TestResult();
        testResult.setName(result.getName());
        testResult.setStatus("Passed");
        testResult.setDuration(testResult.detailsFormatDuration(Duration.ofMillis(result.getEndMillis() - result.getStartMillis())));
        testResult.setUrl(driver.getCurrentUrl());
        testResult.setEnvironment(testResult.getEnvironment());
        testResult.setPage(testResult.getPage());
        passedTestResults.add(testResult);
        passedTests++;
        totalTests++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TestResult testResult = new TestResult();
        testResult.setName(result.getName());
        testResult.setStatus("Failed");
        testResult.setDuration(testResult.detailsFormatDuration(Duration.ofMillis(result.getEndMillis() - result.getStartMillis())));
        testResult.setUrl(driver.getCurrentUrl());
        testResult.setEnvironment(testResult.getEnvironment());
        testResult.setPage(testResult.getPage());
        failedTestResults.add(testResult);
        failedTests++;
        totalTests++;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TestResult testResult = new TestResult();
        testResult.setName(result.getName());
        testResult.setStatus("Skipped");
        testResult.setDuration(testResult.detailsFormatDuration(Duration.ofMillis(result.getEndMillis() - result.getStartMillis())));
        testResult.setUrl(driver.getCurrentUrl());
        testResult.setEnvironment(testResult.getEnvironment());
        testResult.setPage(testResult.getPage());
        skippedTestResults.add(testResult);
        skippedTests++;
        totalTests++;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // do nothing
    }


}
