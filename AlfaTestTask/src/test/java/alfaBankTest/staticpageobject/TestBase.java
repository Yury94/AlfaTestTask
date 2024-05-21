package alfaBankTest.staticpageobject;

//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;

import alfaBankTest.pageobject.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {

    public final String LIGHT_PINK = "rgba(248, 215, 218, 1)";
    public final String expectedServerErrorMessageText = "Internal server error"; //задаем ожидаемую переменную для сравнени

    WebDriver driver;

    @BeforeMethod
    protected void setup() {
//        driver = WebDriverManager.chromedriver().create();

        Browser browser = Browser.valueOf(System.getProperty("browser", "chrome"));

        driver = switch (browser) {
            case edge -> new EdgeDriver();
            case chrome -> new ChromeDriver();
            case firefox -> new FirefoxDriver();
        };

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get("https://enotes.pointschool.ru/login");
        LoginPage.attemptLogin(driver,"test", "test");//Логинимся
        HomePage homePage = new HomePage(driver);//инициализируем обьект класса HomePage

        int productCount = Integer.parseInt(homePage.getProductCount().getText());
        if(productCount > 0) {
            if (productCount == 9) {
                homePage.selectAnyProductWithoutDiscount();//выбор любого акционного товара
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//задаем Явное ожидание
                wait.until(ExpectedConditions.textToBe((By.cssSelector(".basket-count-items.badge.badge-primary")), "10"));
            }
            ListOfProductsInModuleWindow.deleteAllDataFromBasket(driver);//очистка корзины для выполнения условия задачи
        }
    }
    @AfterMethod
    protected void teardown() {
        driver.quit();
    }
}
