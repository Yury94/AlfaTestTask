package alfaBankTest.staticpageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage {

    private static final By serverErrorMessageLocator = By.cssSelector("div.alert.alert-danger");
        public static String getServerErrorMessageText(WebDriver driver) {
        return driver.findElement(serverErrorMessageLocator).getText();
    }

    public  static String getColorErrorMessage (WebDriver driver) {
        return driver.findElement(serverErrorMessageLocator).getCssValue("background-color");
    }


}
