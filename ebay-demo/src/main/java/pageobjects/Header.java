package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by kashi on 11/5/17.
 */
public class Header extends BasePage {
    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "gh-ac")
    WebElement searchTextBox;

    @FindBy(id = "gh-btn")
    WebElement searchButton;


    public void enterSearchKeyword(String keyword){
        searchTextBox.sendKeys(keyword);
    }

    public void clickSearchButton(){
        searchButton.click();
    }


}
