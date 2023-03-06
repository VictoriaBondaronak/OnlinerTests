package onliner.pageObject.pages;

import framework.BasePage;
import framework.elements.Label;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CatalogPage extends BasePage {
    private static final Label PAGE_LOCATOR = new Label(By.xpath("//div[@class='catalog-navigation__title' and text()='Каталог']"));
    private static final By pageLocator = By.xpath("//div[@class='catalog-navigation__title' and text()='Каталог']");

    private static final String NAVIGATE_MENU = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'%s')]";
//    private static final Label NAV_SUBMENU_CATEGORY =
//            new Label(By.xpath("//div[@class='catalog-navigation-list__category']//span[@class='catalog-nav" +
//                    "igation-list__dropdown-title' and contains(text(),'Игровые ноутбуки')]"));
    //private static final String NAV_SUBMENU_ITEM = "//div[@class='catalog-navigation-list__aside-title' and contains(text(),'%s')]";
    private static final String NAV_SUBMENU_CATEGORY = "//div[@class='catalog-navigation-list__category']//span[@class='catalog-nav" +
            "igation-list__dropdown-title' and contains(text(),'%s')]";
    private static final String NAV_SUBMENU_ITEM = "//div[@class='catalog-navigation-list__aside-title' and contains(text(),'%s')]";

    public CatalogPage() {
        super(pageLocator, "'Catalog' page");
    }

    @Step("Выбор заголовка 'Компьютеры и сети' в меню навигации.")
    public void navigateToMainMenuItem(String headerMenuItem) {
        Label navMenuOnCatalogPage = new Label(By.xpath(String.format(NAVIGATE_MENU, headerMenuItem)));
        navMenuOnCatalogPage.click();
    }

    @Step("Наведение на  'Ноутбуки, компьютеры, мониторы' подменю.")
    public void navigateToSubMenuItems(String submenuItem) {

        Label navSubmenuItem = new Label(By.xpath(String.format(NAV_SUBMENU_ITEM, submenuItem)));

        navSubmenuItem.moveToElement();
    }

    @Step("Переход на страницу 'Игровые ноутбуки'.")
    public void navigateToSubMenuCategory(String submenuCategory) {

        Label navSubmenuCategory = new Label(By.xpath(String.format(NAV_SUBMENU_CATEGORY, submenuCategory)));

        navSubmenuCategory.moveAndClickByAction();
    }

}
