import com.mongodb.MongoClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.System.setProperty;

public class Scenario
{
    private static final String PATH = "src/main/resources/";
    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final int DEFAULT_MONGO_PORT = 27017;

    protected WebDriver driver;
    protected MongoClient mongoDB ;

    @BeforeEach
    public void settings()
    {
        if (System.getProperty("os.name").contains("Windows"))
        {
            setProperty(CHROME_DRIVER, PATH.concat("chromedriver.exe"));
            driver = new ChromeDriver();
        } else {
            setProperty(CHROME_DRIVER, PATH.concat("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("--disable-gpu");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("window-size=1200x600");
            options.addArguments("--no-sandbox");
            driver = new ChromeDriver(options);
        }
        mongoDB = new MongoClient("localhost", 27017);

        driver.manage().window().maximize();
    }

    @AfterEach
    public void quit()
    {
        driver.quit();
    }
}
