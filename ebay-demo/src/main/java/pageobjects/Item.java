package pageobjects;

import org.openqa.selenium.WebDriver;

/**
 * Created by kashi on 11/5/17.
 */
public class Item {

    public Item(String title, float price){
        this.title=title;
        this.price=price;
    }

    private String title;
    private float price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
