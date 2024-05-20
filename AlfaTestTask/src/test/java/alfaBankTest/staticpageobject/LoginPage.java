package alfaBankTest.staticpageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private static final By emailInputLocator = By.name("LoginForm[username]");
    private static final By passwordInputLocator = By.name("LoginForm[password]");
    private static final By loginButtonLocator = By.name("login-button");

    public static void attemptLogin(WebDriver driver, String username, String password) {
        driver.findElement(emailInputLocator).sendKeys(username);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }
}
