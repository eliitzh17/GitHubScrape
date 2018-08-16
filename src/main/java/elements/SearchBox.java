package elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ResultPage;

/**
 * This object represent the searchAndGetResult box in the top toolbar.
 * */
public class SearchBox extends AbstractElement
{
    public SearchBox(WebElement webElement)
    {
        super(webElement);
    }

    public ResultPage searchAndGetResult(String key, WebDriver driver)
    {
        search(key);
        webElement.sendKeys(Keys.ENTER);

        return new ResultPage(driver);
    }

    public void search(String key)
    {
        webElement.click();
        webElement.sendKeys(key);
    }

    public void clear()
    {
        webElement.clear();
    }
}
