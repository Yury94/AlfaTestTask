package alfaBankTest.pageobject;

//import io.github.bonigarcia.wdm.WebDriverManager;
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
    public final String expectedServerErrorMessageText = "Internal server error"; //задаем ожидаемую переменную для сравнения

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
        driver.get("https://enotes.pointschool.ru/login");
        LoginPage loginPage = new LoginPage(driver);//инициализируем обьект класса LoginPage
        HomePage homePage = new HomePage(driver);//инициализируем обьект класса HomePage
        ListOfProductsInModuleWindow listOfProductsInModuleWindow = new ListOfProductsInModuleWindow(driver);
        loginPage.attemptLogin("test", "test");//Логинимся

        int productCount = Integer.parseInt(homePage.getProductCount().getText());
        if(productCount > 0) {
            if (productCount == 9) {
                homePage.selectAnyProductWithoutDiscount();//выбор любого акционного товара
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//задаем Явное ожидание
                wait.until(ExpectedConditions.textToBe((By.cssSelector(".basket-count-items.badge.badge-primary")), "10"));
            }
            listOfProductsInModuleWindow.deleteAllDataFromBasket();//очистка корзины для выполнения условия задачи
        }
    }

    @AfterMethod
    protected void teardown() {
        driver.quit();
    }
}
