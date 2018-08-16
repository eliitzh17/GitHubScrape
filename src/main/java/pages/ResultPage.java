package pages;

import elements.ResultElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ResultPage extends AbstractPage
{
    private static final String RESULT_CLASS_NAME = "repo-list-item";

    private List<ResultElement> resultList;

    public ResultPage(WebDriver driver)
    {
        super(driver);
    }

    private List<ResultElement> initList()
    {
        if (resultList == null)
        {
            resultList = new ArrayList<ResultElement>();
        }

        for (WebElement webElement : driver.findElements(By.className(RESULT_CLASS_NAME)))
        {
            resultList.add(new ResultElement(webElement));
        }

        return resultList;
    }

    public List<ResultElement> getResultList()
    {
        initList();

        return resultList;
    }

}
