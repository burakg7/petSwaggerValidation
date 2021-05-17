package pages;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PetSwaggerPage {



    public PetSwaggerPage(WebDriver driver) {
        PageFactory.initElements(Driver.getDriver()  , this);
    }

    @FindBy (xpath = "//*[@class='operation-tag-content']/child::span/child::div")
    private List<WebElement> methodList;

    public List<WebElement> getMethodList() {
        return methodList;
    }
}
