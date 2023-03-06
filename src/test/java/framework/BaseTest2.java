package framework;

import org.testng.annotations.*;


//@Listeners(TestListener.class)
public class BaseTest2 {
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
