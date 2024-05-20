package alfaBankTest.selenide;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

@Epic("Selenium test task")
@Feature("Stationery")
public class LoginPage {
    @Step("Attempt to Login")

    public static void attemptLogin(String username, String password) {
        $(By.name("LoginForm[username]")).sendKeys(username);
        $(By.name("LoginForm[password]")).sendKeys(password);
        $(By.name("login-button")).click();
    }
}
