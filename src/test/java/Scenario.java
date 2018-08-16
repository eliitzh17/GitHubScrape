import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.System.setProperty;

public class Scenario
{
    private static final String PATH = "src/main/resources/";
    private static final String CHROME_DRIVER = "webdriver.chrome.driver";

    protected WebDriver driver;

    @BeforeEach
    public void settings()
    {
        if (System.getProperty("os.name").contains("Windows"))
        {
            setProperty(CHROME_DRIVER, PATH.concat("chromedriver.exe"));
        } else {
            setProperty(CHROME_DRIVER, PATH.concat("chromedriver"));

        }
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @AfterEach
    public void quit()
    {
        driver.quit();
    }
}
