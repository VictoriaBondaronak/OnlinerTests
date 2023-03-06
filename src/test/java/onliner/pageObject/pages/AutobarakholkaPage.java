package onliner.pageObject.pages;

import framework.BasePage;
import framework.BaseTest2;
import framework.Browser;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AutobarakholkaPage extends BasePage {
    private static final Label PAGE_LOCATOR = new Label(By.xpath("//h1[contains(text(),'Автобарахолка')]"));
    private static final By pageLocator = By.xpath("//h1[contains(text(),'Автобарахолка')]");
    private static final String CURRENCY_XPATH = "//div[@class='vehicle-form__group vehicle-form__group_width_full']//a[text()='%s']";
    private static final String FROM_TO_FORM_XPATH = "//input[@class='input-style input-style_primary input-style_small vehicle-form__input vehicle-form__input_width_full' and @placeholder='%s']";
    private static final String FILTER_MENU_XPATH = "//div[@class='input-style__faux' and contains(text(),'%s')]//..//div[@class='input-style__real']";
    private static final String FILTER_SUBMENU_XPATH = "//div[@class='dropdown-style__checkbox-sign' and contains(text(),'%s')]";
    //private static final String FILTER_CHECKBOX_ITEM_XPATH = "//div[@class='vehicle-form__checkbox-sign' and contains(text(),'Автоматическая')]/../../div[@class='i-checkbox__faux']";
    private static final String FILTER_CHECKBOX_ITEM_XPATH = "//div[@class='vehicle-form__checkbox-sign' and contains(text(),'%s')]/../..";
    private static final String RESULT_ITEMS_XPATH = "//div[@class='vehicle-form__offers-list']/a";
    private static final String RESULT_ITEMS_TRANSMISSION_TYPE_XPATH = "//div[contains(@class,'vehicle-form__description_transmission') and contains(text(),'%s')]";
    private static final String RESULT_ITEMS_BODY_TYPE_XPATH = "//div[contains(@class,'vehicle-form__description_car') and contains(text(),'%s')]";
    private static final String RESULT_ITEMS_CURR_PRICE_XPATH = "//div[contains(@class,'vehicle-form__description_tiny') and contains(text(),'$')]";
    Label footerLogo = new Label(By.xpath("//div[@class='footer-style__logo']"));



    public AutobarakholkaPage() {
        super(pageLocator, "'Autobaraholka' page");
    }


    public void chooseCurrency(String currency){
        scrollToFiltersAreVisible();
        Button currencyButton = new Button(By.xpath(String.format(CURRENCY_XPATH,currency)));
        currencyButton.click();
    }


    public void scrollToFiltersAreVisible(){
        footerLogo.scrollIntoView();
    }

    public void filterByRange(String startPrice, String endPrice, int formIndex) throws InterruptedException {
        scrollToFiltersAreVisible();
        fillFromToInput("от",startPrice,formIndex);
        scrollToFiltersAreVisible();
        fillFromToInput("до",endPrice, formIndex);
        scrollToFiltersAreVisible();
    }

    public void fillFromToInput(String type, String value, int index){
        scrollToFiltersAreVisible();
        List<WebElement> items = Browser.getDriver().findElements(By.xpath(String.format(FROM_TO_FORM_XPATH,type)));
        WebElement item = items.get(index);
        item.click();
        scrollToFiltersAreVisible();
        item.sendKeys(value);
        scrollToFiltersAreVisible();
        item.sendKeys(Keys.ENTER);
    }

    public void filterClick(String filterButtonText){
        scrollToFiltersAreVisible();
        Label filterMenu = new Label(By.xpath(String.format(FILTER_MENU_XPATH,filterButtonText)));
        filterMenu.clickAndWait();

    }
    public void filterSubmenuClick(String item){
        Label filterSubmenuMenu = new Label(By.xpath(String.format(FILTER_SUBMENU_XPATH,item)));
        filterSubmenuMenu.clickAndWait();
    }

    public void checkBoxChoose(String item){

        Label checkbox = new Label(By.xpath(String.format(FILTER_CHECKBOX_ITEM_XPATH,item)));
        scrollToFiltersAreVisible();
        checkbox.isDisplayed();
        checkbox.clickAndWait();
    }

    public void checkResults(String transmission, String bodytype, long startPrice, long endPrice){
        Label resultsList = new Label(By.xpath(RESULT_ITEMS_XPATH));
        int count = resultsList.getElements().size();
        checkCheckboxValue(RESULT_ITEMS_BODY_TYPE_XPATH, count, bodytype);
        //checkCheckboxValue(RESULT_ITEMS_TRANSMISSION_TYPE_XPATH, count, transmission);
        checkPrice(startPrice,endPrice,count);

    }

    public void checkCheckboxValue(String checkingFieldXpath, int count, String textValue){
            scrollToFiltersAreVisible();
            List<WebElement> checkingFields = Browser.getDriver().findElements(By.xpath(String.format(checkingFieldXpath, textValue)));
            int size = checkingFields.size();
            Assert.assertEquals(count, size,String.format("The filter by value '%s' did not work", textValue));
    }

    public void checkPrice(long startPrice, long endPrice, int count){
            List<WebElement> prices = Browser.getDriver().findElements(By.xpath(RESULT_ITEMS_CURR_PRICE_XPATH));
            List<Long> values = new ArrayList<>();
        for (WebElement price : prices) {
            int index = price.getText().indexOf("$");
            String str = price.getText().substring(0,index);
            long value = Long.parseLong(str.replace(" ", ""));
            values.add(value);
        }
        long size = values.stream().filter(num -> num >= startPrice).filter(num -> num <= endPrice).count();
            Assert.assertEquals( size, count, String.format("The filter by value 'Price' did not work"));
    }


}
