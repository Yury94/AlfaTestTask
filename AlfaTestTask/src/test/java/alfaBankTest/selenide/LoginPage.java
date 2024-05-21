package alfaBankTest.selenide;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Epic("Selenium test task")
@Feature("Stationery")
public class LoginPage {
    @Step("Attempt to Login")
    public HomePage attemptLogin(String username, String password) {
        $(By.name("LoginForm[username]")).sendKeys(username);
        $(By.name("LoginForm[password]")).sendKeys(password);
        $(By.name("login-button")).click();
        return Selenide.page(HomePage.class);
    }
}
