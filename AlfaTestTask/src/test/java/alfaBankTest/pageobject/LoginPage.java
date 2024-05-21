package alfaBankTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final By emailInputLocator = By.name("LoginForm[username]");
    private final By passwordInputLocator = By.name("LoginForm[password]");
    private final By loginButtonLocator = By.name("login-button");
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage attemptLogin(String username, String password) {
        driver.findElement(emailInputLocator).sendKeys(username);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        return new LoginPage(driver);
    }
}
