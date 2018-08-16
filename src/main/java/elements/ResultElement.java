package elements;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.*;

/**
 * This object represent single result after search.
 * */
public class ResultElement extends AbstractElement
{
    public ResultElement(WebElement webElement)
    {
        super(webElement);
    }

    @Getter(lazy = true)
    private final WebElement title = webElement.findElement(By.className("v-align-middle"));
    @Getter(lazy = true)
    private final String description = webElement.findElement(By.className("pr-4")).getText();
    @Getter(lazy = true)
    private final List<WebElement> tags = webElement.findElements(By.className("topic-tag"));
    @Getter(lazy = true)
    private final LocalDateTime time = initTime();
    @Getter(lazy = true)
    private final String language = webElement.findElement(By.className("flex-auto")).getText();
    @Getter(lazy = true)
    private final WebElement stars = webElement.findElement(By.cssSelector("a[href*='stargazers']"));

    // Delete the 'Z' char from the string to initialize the dateTime object.
    private LocalDateTime initTime()
    {
        String element = webElement.findElement(By.tagName("relative-time")).getAttribute("datetime").split("Z")[0];

        return LocalDateTime.parse(element);
    }

    public List<String> getTagsText()
    {
        List<String> tagsList = new ArrayList<>();

        getTags().forEach(webElement -> tagsList.add(webElement.getText()));

        return tagsList;
    }

    public double getStartsValue()
    {
        return parseDouble(getStars().getText().substring(0, getStars().getText().length()-1));
    }
}
