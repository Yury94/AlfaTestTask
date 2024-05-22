package alfaBankTest.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

import static com.codeborne.selenide.Browsers.*;

@Epic("Selenium test task")
@Feature("Stationery")
public class TestBase implements ITestListener {
    public final String LIGHT_PINK = "rgba(248, 215, 218, 1)";
    public final String expectedServerErrorMessageText = "Internal server error";
    WebDriver driver;

    @BeforeMethod
    protected void setup() {
        Configuration.browser = CHROME;
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 25000;

        Browser browser = Browser.valueOf(System.getProperty("browser", "edge"));
//        driver = WebDriverManager.chromedriver().create();
        switch (browser) {
            case chrome -> Configuration.browser = CHROME;
            case edge -> Configuration.browser = EDGE;
            case firefox -> Configuration.browser = FIREFOX;
        }

        if (Objects.equals(Configuration.browser, CHROME)) {//запуск в инкогнито
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            Configuration.browserCapabilities = options;
        }
        HomePage homePage = Selenide.open("https://enotes.pointschool.ru/login", LoginPage.class)
                .attemptLogin("test", "test");

        int productCount = Integer.parseInt(homePage.getProductCount().getText());
        if (productCount > 0) {
            if (productCount == 9) {
                homePage.selectAnyProductWithoutDiscount();//выбор любого акционного товара
                homePage.getProductCount().shouldHave(Condition.text(String.valueOf(productCount + 1)));//проверяем, что добавили +1
            }
            ListOfProductsInModuleWindow listOfProductsInModuleWindow = new ListOfProductsInModuleWindow();
            listOfProductsInModuleWindow.deleteAllDataFromBasket();//очистка корзины для выполнения условия задачи
            homePage.getProductCount().shouldHave(Condition.text(String.valueOf(0)));//проверка, что значение корзины =0
        }
    }

    @AfterMethod
    public void onTestFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File screenshort = Selenide.screenshot(OutputType.FILE);
            try {
                Allure.addAttachment(testResult.getMethod().getMethodName(), new FileInputStream(screenshort));
//                ReportPortal.emitLog(testResult.getMethod().getMethodName(), "ERROR", Calendar.getInstance().getTime(), screenshort);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @AfterMethod
    protected void teardown() {
        Selenide.closeWebDriver();
    }
}
