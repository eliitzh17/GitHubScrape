package pages;

import elements.SearchBox;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage
{
    private static final String HOST = "https://github.com/";

    @Getter(lazy = true)
    private final SearchBox searchBox = new SearchBox(driver.findElement(By.className("header-search-input")));

    public MainPage(WebDriver driver)
    {
        super(driver);
        driver.get(HOST);
    }
}
