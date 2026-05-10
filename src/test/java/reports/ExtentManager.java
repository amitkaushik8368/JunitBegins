package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager
{
    static ExtentReports extentReports;
    public static ExtentReports getReporter()
    {
        if (extentReports == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\ExtentReport.html");
            reporter.config().setReportName("Herroku Test Report");
            reporter.config().setDocumentTitle("JUnit Report");
            extentReports = new ExtentReports();
            extentReports.attachReporter(reporter);
            extentReports.setSystemInfo("Tester", "Amit");
            extentReports.setSystemInfo("Framework", "JUnit Selenium");
        }
        return extentReports;
    }
}
