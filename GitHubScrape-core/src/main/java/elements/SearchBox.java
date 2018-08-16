package elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ResultPage;

/**
 * This object represent the search box in the top toolbar.
 * */
public class SearchBox extends AbstractElement
{
    public SearchBox(WebElement webElement)
    {
        super(webElement);
    }

    public ResultPage search(String key, WebDriver driver)
    {
        webElement.click();
        webElement.sendKeys(key);
        webElement.sendKeys(Keys.ENTER);

        return new ResultPage(driver);
    }
}
