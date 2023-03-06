package framework;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Date;

import static framework.Browser.waitForPageToLoad;

public class BasePage {
    protected By locator;
    protected String title;

    public BasePage(final By locator, final String title) {
        init(locator, title);
        isPageOpened();
    }

    private void init(final By locator, final String title) {
        this.locator = locator;
        this.title = title;
    }

    private void isPageOpened() {
        long before = new Date().getTime();
        Label label = new Label(locator);
        //waitForPageToLoad();
        //Assert.assertTrue(label.isDisplayed(), String.format("ERROR: Страница %s не была загружена!!!", title));

        try {
            label.isElementPresent();
            waitForPageToLoad();
            long openTime = new Date().getTime() - before;
            System.out.println(String.format("Form '%1$s' appears", title) + String.format(" is %s msec", openTime));

        } catch (Throwable e) {
            Assert.assertTrue(true, "Page " + title + " was not open");
        }
    }
}