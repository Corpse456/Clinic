package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AbstractUITest {

    WebDriver driver;
    static final String BASE_URL = "http://localhost/";

    AbstractUITest() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
    }

    @Before
    public void initDriver() {
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--start-maximized");

        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }
}
