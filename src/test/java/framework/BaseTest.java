package framework;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    public static Browser driver = new Browser();

    @BeforeMethod
    public void setup() {
        driver.getInstance();
        driver.windowMaximize();
        driver.navigate(PropertyReader.getProperty("base.URL"));
    }

    @AfterTest(alwaysRun = true, description = "Closing browser")
    public void tearDown() {
        driver.exit();
    }
}