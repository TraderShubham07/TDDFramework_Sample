package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    static ExtentSparkReporter spark;
    public static ExtentReports report;

    // Extent report manager
    public static ExtentReports getReport() {
        if (report == null) {
            spark = new ExtentSparkReporter("spark.html");
            report = new ExtentReports();
            spark.config().setDocumentTitle("My Automation");
            spark.config().setReportName("My Automation Reports");
            report.attachReporter(spark);
        }
        return report;
    }

    public static void endReport() {
        report.flush();
    }
}
