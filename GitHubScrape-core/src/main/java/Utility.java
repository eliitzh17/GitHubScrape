import com.mongodb.BasicDBObject;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.util.List;

public class Utility
{
    public static String getLink(WebElement webElement)
    {
        return webElement.getAttribute("href");
    }

    public static BasicDBObject createDoc(String title, String des, List<String> tags, LocalDateTime time,
                                           String language, double start)
    {
        BasicDBObject document = new BasicDBObject();
        document.put("title", title);
        document.put("Description", des);
        document.put("Tags", tags);
        document.put("Time", time.toString());
        document.put("Language",language);
        document.put("Stars", start);
        return document;
    }
}
