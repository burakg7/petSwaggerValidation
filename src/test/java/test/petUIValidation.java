package test;

import Utils.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.PetSwaggerPage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class petUIValidation {


    WebDriver driver;
    PetSwaggerPage petSwaggerPage ;
    List<WebElement> methods;
    List<WebElement> expandedMethods;


    @BeforeTest
    public void setUp(){
        //Initializing driver and page
        driver= Driver.getDriver();
        petSwaggerPage=new PetSwaggerPage(driver);
        //Get the url ,maximize and wait
        driver.get("https://petstore.swagger.io/#/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    @Test
    public void expandCollapseTest()  {
        //Store all methods to the list of webElement
        methods= petSwaggerPage.getMethodList();
        //First loop through all off them and click one by one
        for (int i = 0; i < methods.size(); i++) {
            WebElement tempWebElement = methods.get(i);
            tempWebElement.click();
        }
        //Founded the common attribute after I expand for each method
        //The common attribute value after I clicked is "open"

        for (WebElement w: methods) {
            //Assert that every class name has open except the one has been deprecated.
            if(!w.getAttribute("class").contains("deprecated"))
            Assert.assertTrue(w.getAttribute("class").contains("open"),
                    "The element "+ w.getAttribute("class") +" is unable to expanded");
        }

        expandedMethods= petSwaggerPage.getExpandedMethodList();

        // loop through all off them and collapse one by one
        for (int i =expandedMethods.size()-1; i >=0; i--) {
            WebElement tempWebElement = expandedMethods.get(i);
            tempWebElement.click();
        }

        for (WebElement w: methods) {
            //Assert False that every class name has doesn't contains open except the one has been deprecated.
            if(!w.getAttribute("class").contains("deprecated"))
                Assert.assertFalse(w.getAttribute("class").contains("open"),
                        "The element "+ w.getAttribute("class") +" is unable to collapsed");
        }

    }


}
