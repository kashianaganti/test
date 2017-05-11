package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by kashi on 11/5/17.
 */
public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
}
