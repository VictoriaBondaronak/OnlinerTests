package onliner.tests;

import framework.BaseTest2;
import io.qameta.allure.Description;
import onliner.pageObject.pages.CatalogPage;
import onliner.pageObject.pages.MainPage;
import org.testng.annotations.Test;

public class NavigationTest2 extends BaseTest2 {

    @Test
    @Description("Переход на страницу 'Ноутбуки, компьютеры, мониторы'. Ожидаемый результат: Страница 'Ноутбуки, компьютеры, мониторы' загрузилась.")
    public void navigationMenuTest() {
        MainPage mainPage = new MainPage();
        mainPage.navigateHeaderMenu("Каталог");

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.navigateToMainMenuItem("Компьютеры и");
        catalogPage.navigateToSubMenuItems("Ноутбуки, компьютеры, мониторы");
        catalogPage.navigateToSubMenuCategory("Игровые ноутбуки");
    }

    /*
     * 1. Перейти на вкладку Каталог
     * 2. Выбрать Компьютеры и сети в меню навигации
     * 3.  В подменю навести на  Ноутбуки, компьютеры, мониторы
     * 4. Перейти на страницу Игровые ноутбуки*/
}
