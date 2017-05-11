package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by kashi on 11/5/17.
 */
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
    }

    Header header;

    @FindBy(css = ".b-searchhook__subtitle a")
    List<WebElement> viewSearchResults;

    public SearchResults  searchForProducts(String keyword){
        header.enterSearchKeyword(keyword);
        header.clickSearchButton();
        if (viewSearchResults.size() > 0){
            viewSearchResults.get(0).click();
        }
        return new SearchResults(driver);
    }

}
