package setupbase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import page.indexPage;

public class baseSetup {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    // Khởi tạo cấu hình của các Browser
    public WebDriver initChromeDriver() {
        indexPage index = new indexPage(driver);
        ChromeOptions userAgent = new ChromeOptions();
        userAgent.addArguments("disable-notifications");
        driver = new ChromeDriver(userAgent);
        System.out.println("Launching Chrome browser...");
        driver.manage().window().maximize();
        driver.get("https://beta.fastdo.vn/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        index.waitForPageLoaded();
        return driver;
    }
}
