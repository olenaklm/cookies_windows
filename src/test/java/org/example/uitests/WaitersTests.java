package org.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitersTests extends BaseTestClass {

    @Test
    public void waiterTest() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.id("start")).findElement(By.xpath(".//button")).click();
        
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading")));
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

        Assert.assertEquals(driver.findElement(By.cssSelector("#finish > h4")).getText(), "Hello World!");

    }

}
