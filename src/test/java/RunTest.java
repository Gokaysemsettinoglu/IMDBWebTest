import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RunTest {

    String browserType = "webdriver.chrome.driver";
    String browserPath = "driver/chromedriver.exe";
    WebDriverWait wait;
    String pageURL = "https://www.imdb.com/";
    WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String filmCircus = "Şarlo Sirkte";
    @BeforeClass
    public void startBrowser() {
        System.setProperty(browserType, browserPath);
        driver = new ChromeDriver();
        driver.get(pageURL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 12);
    }

    @Test
    public void runCases() {

        MenuControl.controlSearch(wait,driver,filmCircus);

    }
    @AfterClass
    public void tearDown()
    {driver.quit();}
}
