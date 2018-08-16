import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import elements.ResultElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import pages.ResultPage;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScrapeTest extends Scenario
{
    private static final int VALIDATE_RESULT_NUMBER = 5;
    private static final int UNEXPECTED_STATUS_CODE = 404;
    private static final String KEYS = "selenium";
    private static final String DB_NAME = "ScrapeGithubDB";
    private static final String COLLECTION_NAME = "searchResult";
    private static final int DEFAULT_MONGO_PORT = 27017;
    private static final List<String> keysToPerformenceVerify = Arrays.asList("java", "mongo", "oracle", "junit",
                                                                              "allure");
    private MongoClient scrapeDB;

    @Test
    public void test()
    {
        MainPage mainPage = new MainPage(driver);
        ResultPage resultPage = mainPage.getSearchBox().searchAndGetResult(KEYS, driver);

        List<ResultElement> resultList = resultPage.getResultList();
        DBCollection resultCollection = startDB();

        for (int i = 0; i < VALIDATE_RESULT_NUMBER; i++)
        {
            ResultElement resultElement = resultList.get(i);

            BasicDBObject doc = Utility.createDoc(resultElement.getTitle().getText(), resultElement.getDescription(),
                                                  resultElement.getTagsText(), resultElement.getTime(),
                                                  resultElement.getLanguage(), resultElement.getStartsValue());
            resultCollection.insert(doc);

            assertLinkAvailable(resultElement.getTitle());
            resultElement.getTags().forEach(tag -> assertLinkAvailable(tag));
            assertLinkAvailable(resultElement.getStars());
        }
        searchVerify(resultPage);
        pageNavigationVerify(resultPage);

    }

    private DBCollection startDB()
    {
        scrapeDB = new MongoClient("localhost", DEFAULT_MONGO_PORT);
        return scrapeDB.getDB(DB_NAME).getCollection(COLLECTION_NAME);
    }

    private void assertLinkAvailable(WebElement webElement)
    {
        String link = Utility.getLink(webElement);
        assertTrue(get(link).getStatusCode() != UNEXPECTED_STATUS_CODE, link.concat(" is not valid."));
    }

    private void searchVerify(ResultPage page)
    {
        long searchTime = 0;

        for (String key :  keysToPerformenceVerify)
        {
            long startTime = System.currentTimeMillis();
            page.getSearchBox().search(key);
            searchTime += System.currentTimeMillis() - startTime;
            page.getSearchBox().clear();
        }

        System.out.println("Search average is: ".concat(String.valueOf(searchTime / keysToPerformenceVerify.size()).concat("ms")));
    }

    private void pageNavigationVerify(ResultPage resultPage)
    {
        long startTime = System.currentTimeMillis();
        resultPage.getSearchBox().searchAndGetResult("argus", driver);
        System.out.println(System.currentTimeMillis() - startTime + "ms");
    }
}