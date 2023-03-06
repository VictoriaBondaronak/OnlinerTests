package framework;//package framework;
//
//import io.qameta.allure.Attachment;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.concurrent.TimeUnit;
//
//public abstract class TestListener implements ITestListener {
//
//    public void onTestStart(ITestResult iTestResult) {
//        System.out.println((String.format("======================================== STARTING TEST %s ========================================", iTestResult.getName())));
//    }
//
//    public void onTestSuccess(ITestResult iTestResult) {
//        System.out.println(String.format("======================================== FINISHED TEST %s Duration: %ss ========================================", iTestResult.getName(), getExecutionTime(iTestResult)));
//    }
//
//    public void onTestFailure(ITestResult iTestResult) {
//        System.out.println(String.format("======================================== FAILED TEST %s Duration: %ss ========================================", iTestResult.getName(), getExecutionTime(iTestResult)));
//        takeScreenshot();
//    }
//
//    public void onTestSkipped(ITestResult iTestResult) {
//        System.out.println(String.format("======================================== SKIPPING TEST %s ========================================", iTestResult.getName()));
//    }
//
//    private long getExecutionTime(ITestResult iTestResult) {
//        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
//    }
//
//    @Attachment(value = "Last screen state", type = "image/png")
//    private static byte[] takeScreenshot() {
//        return ((TakesScreenshot) Browser.getDriver()).getScreenshotAs(OutputType.BYTES);
//    }
//
//    @Attachment(value = "Expected result", fileExtension = ".txt")
//    public static byte[] expectedResult(String resourceName) throws IOException {
//        return Files.readAllBytes(Paths.get("src/test/resources", resourceName));
//    }
//
//    @Attachment(value = "Actual result", fileExtension = ".txt")
//    public static byte[] actualResult(String resourceName) throws IOException {
//        return Files.readAllBytes(Paths.get("src/test/resources", resourceName));
//    }
//}