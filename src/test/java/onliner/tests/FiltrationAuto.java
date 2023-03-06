package onliner.tests;

import framework.BaseTest2;
import framework.Browser;
import onliner.pageObject.pages.AutobarakholkaPage;
import onliner.pageObject.pages.MainPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/*1. Перейти на кладку Автобарахолка
* 2. Отфильтровать авто по цене до 100000$
* 3. Отфильтровать авто по типу кузова: седан
* 4. Отфильтровать авто по типу коробки передач: автоматическая
* 5. Проверить работу фильтров */
public class FiltrationAuto extends BaseTest2{
    @Test
    public void filtrationTest() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.navigateHeaderMenu("Автобарахолка");
        AutobarakholkaPage autobarakholkaPage = new AutobarakholkaPage();
        autobarakholkaPage.chooseCurrency("USD");
        autobarakholkaPage.filterByRange("0","100000",0);
        autobarakholkaPage.filterClick("Любой");
        autobarakholkaPage.filterSubmenuClick("Седан");
        autobarakholkaPage.checkBoxChoose("Автоматическая"); //не работает ни с одним локатором(
        autobarakholkaPage.checkResults("Автоматическая", "Седан", 0,100000);
    }
}
