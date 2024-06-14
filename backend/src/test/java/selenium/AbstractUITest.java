package selenium;

import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public abstract class AbstractUITest {

    private static final int WAIT_SECONDS = 20;
    static final String BASE_URL = "http://localhost/";
    static final String TEXT = "\\{0\\}";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMddyyyy");
    public static final String BUTTON_XPATH = ".//button[contains(string(),'" + TEXT + "')]";
    public static final String INPUT_XPATH = ".//input[@placeholder='" + TEXT + "']";
    public static final String ADMIN = "admin";
    public static final String NAME = "name";
    public static final String LAST_NAME = "last name";
    public static final String ALIAS = "nick name";
    public static final String PASSWORD = "password";
    public static final String SIGN_UP = "SignUp";
    public static final String LOGIN = "Login";
    public static final String CREATE_PATIENT_CARD = "Create patient card";
    public static final String BIRTH_DATE = "birth date";
    public static final String SUBMIT = "Submit";
    public static final String LOGOUT = "Logout";
    public static final String SELECT = "//select";

    private WebDriver driver;
    private WebDriverWait wait;

    AbstractUITest() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
    }

    @Before
    public void initDriver() {
        final var options = new ChromeOptions();
        options.addArguments("--incognito", "--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, WAIT_SECONDS);

        getDriver().get(BASE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    WebElement getButton(final String text) {
        return getWebElementByXPath(BUTTON_XPATH, text);
    }

    WebElement getInput(final String placeholder) {
        return getWebElementByXPath(INPUT_XPATH, placeholder);
    }

    Select getSelect() {
        return new Select(getWebElementByXPath(SELECT));
    }

    private WebElement getWebElementByXPath(final String xPath) {
        return getWebElementByXPath(xPath, "");
    }

    private WebElement getWebElementByXPath(final String xPath, final String placeholder) {
        return getWebElements(xPath, placeholder).get(0);
    }

    private List<WebElement> getWebElements(final String xPath) {
        return getWebElements(xPath, "");
    }

    private List<WebElement> getWebElements(final String xPath, final String placeholder) {
        final var by = By.xpath(xPath.replace(TEXT, placeholder));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElements(by);
    }

    private void fillInput(final String placeholder, final String value) {
        final var input = getInput(placeholder);
        fillInput(input, value);
    }

    private void fillInput(final WebElement input, final String value) {
        final var actions = new Actions(driver);
        actions.moveToElement(input);
        actions.click();
        input.clear();
        actions.sendKeys(value);
        actions.build().perform();
    }

    void signUp(final String alias, final String password, final String firstName, final String lastName) {
        getButton(SIGN_UP).click();

        fillInput(ALIAS, alias);
        fillInput(PASSWORD, password);
        fillInput(NAME, firstName);
        fillInput(LAST_NAME, lastName);

        getButton(SIGN_UP).click();
    }

    void loginAsAdmin() {
        fillInput(ALIAS, ADMIN);
        fillInput(PASSWORD, ADMIN);

        getButton(LOGIN).click();
    }

    void createPatientLikeAdmin(final String name, final String lastName, final LocalDate birthDate, final String sex) {
        getButton(CREATE_PATIENT_CARD).click();

        fillInput(NAME, name);
        fillInput(LAST_NAME, lastName);
        getInput(LAST_NAME).sendKeys(Keys.TAB);
        getInput(BIRTH_DATE).sendKeys(birthDate.format(DATE_FORMATTER));

        getSelectOption(sex);

        getButton(SUBMIT).click();
    }

    void logout() {
        getButton(LOGOUT).click();
    }

    private void getSelectOption(final String sex) {
        getSelect().selectByVisibleText(sex);
    }
}
