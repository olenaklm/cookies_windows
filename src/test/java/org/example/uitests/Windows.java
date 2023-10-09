package org.example.uitests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class Windows extends BaseTestClass {
    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://the-internet.herokuapp.com/windows");
    }

    @Test
    public void switchToWindow() {
        String currentWindow = driver.getWindowHandle();

        driver.findElement(By.linkText("Click Here")).click();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) { // У нас тільки 2 хендли тому обираємо того що не дорівнює збереженому
            if (!(handle.equals(currentWindow))) {
                driver.switchTo().window(handle);
                break;
            }
        }

        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "New Window");

        driver.close();
        driver.switchTo().window(currentWindow);
        driver.findElement(By.linkText("Click Here")).isDisplayed();
    }
}
