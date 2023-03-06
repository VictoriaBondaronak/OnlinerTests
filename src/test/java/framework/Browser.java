package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static final String DEFAULT_PAGE_LOAD_TIMEOUT = "timeout.for.page.load";
    private static final String DEFAULT_CONDITION_TIMEOUT = "timeout.for.element";

    static final String PROPERTIES_FILE = "config.properties";

    private static Browser instance;
    private static WebDriver driver;

    private static String timeoutForPageLoad;
    private static String timeoutForCondition;

    public static PropertyReader props;

    public boolean isBrowserAlive() {
        return instance != null;
    }


    public Browser getInstance() {
        if (instance == null) {
            initProperties();
            try {
                driver = DriverFactory.getDriver();
                driver.manage().timeouts().implicitlyWait(PropertyReader.getIntProperty(DEFAULT_PAGE_LOAD_TIMEOUT), TimeUnit.SECONDS);
            } catch (Exception e) {
                Assert.fail("Driver does not instance" + e.getMessage());
            }
            instance = new Browser();
        }
        return instance;
    }

    private static void initProperties() {
        props = new PropertyReader(PROPERTIES_FILE);
        timeoutForPageLoad = props.getProperty(DEFAULT_PAGE_LOAD_TIMEOUT);
        timeoutForCondition = props.getProperty(DEFAULT_CONDITION_TIMEOUT);
    }

    public void exit() {
        try {
            if (isBrowserAlive()) driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }

    public static String getTimeoutForCondition() {
        return timeoutForCondition;
    }

    public static String getTimeoutForPageLoad() {
        return  timeoutForPageLoad;
    }

    public static void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(getTimeoutForPageLoad())));
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver d) {
                    if (!(d instanceof JavascriptExecutor)) {
                        return true;
                    }
                    Object result = ((JavascriptExecutor) d)
                            .executeScript("return document['readyState'] ? 'complete' === document.readyState : true");
                    return result instanceof Boolean && (Boolean) result;
                }
            });
        } catch (Exception e) {
            Assert.fail("Page does not Load" + e);
        }
    }

    public void windowMaximize() {
        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            Assert.fail("Page cannot be open ");
        }
    }

    public void navigate(String url) {
        driver.navigate().to(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public String getLocation() {
        return driver.getCurrentUrl();
    }
}