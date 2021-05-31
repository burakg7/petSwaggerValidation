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
    //24234
    //423423423232
    ///423423423423
    //34234
    //234234234

    @FindBy (xpath = " //*[@class='operation-tag-content']/child::span/child::div/child::div")
    private List<WebElement> expandedMethodList;
    ////234234234234
    //23423423423423

    public List<WebElement> getMethodList() {
        return methodList;
    }
    //234234234234

    public List<WebElement> getExpandedMethodList() {
        return expandedMethodList;
    }
}
