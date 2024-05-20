package alfaBankTest.selenide;

//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.Browsers.*;
import static com.codeborne.selenide.Selenide.open;

@Epic("Selenium test task")
@Feature("Stationery")
public class TestBase implements ITestListener {

    public final String LIGHT_PINK = "rgba(248, 215, 218, 1)";

    WebDriver driver;

    @BeforeMethod
    protected void setup() {
        Configuration.browser = CHROME;
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 25000;
        open("https://enotes.pointschool.ru/login");
        Browser browser = Browser.valueOf(System.getProperty("browser", "edge"));
        //        driver = WebDriverManager.chromedriver().create();
        switch (browser) {
            case chrome -> Configuration.browser = CHROME;
            case edge -> Configuration.browser = EDGE;
            case firefox -> Configuration.browser = FIREFOX;
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
