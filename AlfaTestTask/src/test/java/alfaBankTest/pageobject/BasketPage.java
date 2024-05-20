package alfaBankTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage {

    private final By serverErrorMessageLocator = By.cssSelector("div.alert.alert-danger");
    private WebDriver driver;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getServerErrorMessageText() {
        return driver.findElement(serverErrorMessageLocator).getText();
    }

    public  String getColorErrorMessage () {
        return driver.findElement(serverErrorMessageLocator).getCssValue("background-color");
    }


}
