package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kashi on 11/5/17.
 */
public class SearchResults extends BasePage {
    public SearchResults(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "#Results li[id^='item']")
    List<WebElement> searchResultItems;

    @FindBy(css = "a.next")
    WebElement nextPage;

    @FindBy(css = ".rcnt")
    WebElement resultCount;

    public static final String ITEM_TITLE = ".lvtitle a";
    public static final String ITEM_PRICE = ".lvprice span";


    public List<Item> getResultItems(int limit){
        int arraySize = (limit < getResultCount()) ? limit : getResultCount();
        List<Item> itemsListToReturn = new ArrayList<Item>(arraySize);
        return addItemsToArrayList(arraySize,itemsListToReturn);
        /*int counter = 0;
        while (counter < arraySize){

            for(int i = 0; i < searchResultItems.size() &&  counter < arraySize; i++, counter++){
                itemsListToReturn.add(constructItem(i));

            }
            if (counter < arraySize){
                this.nextPage.click();
            }
        }
        return itemsListToReturn;*/
    }


    private List<Item> addItemsToArrayList(int itemCount, List<Item> arrayList){
        int itemsToAdd = itemCount - arrayList.size();
        for(int i= 0; i < searchResultItems.size() && i < itemsToAdd ; i++){
            arrayList.add(constructItem(i));
        }
        if (arrayList.size() < itemCount){
            this.nextPage.click();
            addItemsToArrayList(itemCount,arrayList);
        }
        return arrayList;
    }


    public int getResultCount(){
        return Integer.parseInt(resultCount.getText().replace(",","").trim());
    }

    public String getItemTitle(int index){
        return this.searchResultItems.get(index).findElement(By.cssSelector(ITEM_TITLE)).getText();
    }

    public Item constructItem(int index){
        float price = getItemPrice(index);
        String title = getItemTitle(index);
        return new Item(title,price);
    }

    public float getItemPrice(int index){
        List<WebElement> priceElements = this.searchResultItems.get(index).findElements(By.cssSelector(ITEM_PRICE));
        String priceText = priceElements.get(0).getText();
        float price = 0;
        try {
            price = NumberFormat.getCurrencyInstance(Locale.US).parse(priceText.trim()).floatValue();
        }
        catch (Exception ex){
            System.out.println("error parsing price for item at index "+index+" text is "+priceText);
            System.out.println(ex.getMessage());
        }
        return price;

    }


}
