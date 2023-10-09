package org.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectTests extends BaseTestClass {

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void selectTest() throws InterruptedException {
        Select selector = new Select(driver.findElement(By.id("dropdown")));
        selector.selectByVisibleText("Option 2");
        Assert.assertEquals(selector.getFirstSelectedOption().getAttribute("value"), "2");

        Thread.sleep(3000);

        selector.selectByValue("1");
        Assert.assertEquals(selector.getFirstSelectedOption().getText(), "Option 1");

        Thread.sleep(3000);
    }
}
