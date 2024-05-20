package alfaBankTest.staticpageobject;

//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {

    public final String LIGHT_PINK = "rgba(248, 215, 218, 1)";

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
    }

    @AfterMethod
    protected void teardown() {
        driver.quit();
    }
}
