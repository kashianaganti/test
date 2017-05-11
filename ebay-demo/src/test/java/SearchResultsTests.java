import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.Item;
import pageobjects.SearchResults;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * Created by kashi on 11/5/17.
 */
public class SearchResultsTests {

    WebDriver driver;

    @Parameters({"baseUrl","seleniumHubUrl"})
    @BeforeTest
    public void setUp(String baseUrl,String seleniumHubUrl) throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(new URL(seleniumHubUrl), DesiredCapabilities.chrome());
        this.driver = driver;
        driver.navigate().to(baseUrl);

    }

    @Parameters({"keyword","searchItemsLimit"})
    @Test
    public void searchResultsTest(String keyword, int itemsLimit){
        HomePage homePage = new HomePage(driver);
        SearchResults results = homePage.searchForProducts(keyword);
        Assert.assertEquals(itemsLimit <= results.getResultCount(),true,"Can not return "+itemsLimit+" items, Actual returned items are "+results.getResultCount());
        List<Item> items = results.getResultItems(itemsLimit);
        System.out.println(items);
        System.out.println("items count "+items.size());

    }

    @AfterTest
    public void tearDown(){
        if (driver != null){
            driver.close();
            driver.quit();
        }
    }
}
