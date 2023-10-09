package org.example.uitests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsTestsWithJS {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void alertTest() {
        clickAlertButton();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You successfully clicked an alert");

    }

    @Test
    public void confirmTestOk() {
        clickConfirmButton();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You clicked: Ok");
    }

    @Test
    public void alertTestJS() {
        pressAlertButton();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You successfully clicked an alert");

    }

    @Test
    public void confirmTestOkJS() {
        pressConfirmButton();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You clicked: Ok");
    }

    @Test
    public void alertTestClickWithJS() {
        clickAlertButtonWithJS();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You successfully clicked an alert");

    }

    @Test
    public void confirmTestOkClickWithJS() {
        clickConfirmButtonWithJS();
        workWithAlert(true);
        String result = getResult();
        Assert.assertEquals(result, "You clicked: Ok");
    }

    @Test
    public void confirmTestCancel() {
        clickConfirmButton();
        workWithAlert(false);
        String result = getResult();
        Assert.assertEquals(result, "You clicked: Cancel");
    }


    @Test
    public void promptTestOk() {
        String textToEnter = "TestString!";
        clickPromptButton();
        workWithAlert(true, textToEnter);
        String result = getResult();
        Assert.assertEquals(result, "You entered: " + textToEnter);
    }

    @Test
    public void promptTestOkJSText() {
        String textToEnter = "TestString!";
        clickPromptButton();
        workWithAlert(true, textToEnter);
        String result = getResultJS();
        Assert.assertEquals(result, "You entered: " + textToEnter);
    }

    @Test
    public void promptTestCancel() {
        String textToEnter = "TestString!";
        clickPromptButton();
        workWithAlert(false, textToEnter);
        String result = getResult();
        Assert.assertEquals(result, "You entered: null");
    }

    private void clickOnButton(Buttons button) {
        WebElement alertButton = driver.findElement(By.xpath("//button[contains(text(),'" + button.getText() + "')]"));
        alertButton.click();
    }

    private void pressOnButtonWithJS(Buttons button) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("return " + button.jsScript);
    }

    private void clickOnButtonWithJS(Buttons button) {
        WebElement alertButton = driver.findElement(By.xpath("//button[contains(text(),'" + button.getText() + "')]"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("return arguments[0].click();", alertButton);
    }

    public String getResult() {
        return driver.findElement(By.id("result")).getText();
    }

    public String getResultJS() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript("return document.getElementById('result').textContent").toString();
    }

    public void clickAlertButton() {
        clickOnButton(Buttons.ALERT);
    }

    public void clickConfirmButton() {
        clickOnButton(Buttons.CONFIRM);
    }

    public void clickPromptButton() {
        clickOnButton(Buttons.PROMPT);
    }


    public void pressAlertButton() {
        pressOnButtonWithJS(Buttons.ALERT);
    }

    public void pressConfirmButton() {
        pressOnButtonWithJS(Buttons.CONFIRM);
    }

    public void pressPromptButton() {
        pressOnButtonWithJS(Buttons.PROMPT);
    }


    public void clickAlertButtonWithJS() {
        clickOnButtonWithJS(Buttons.ALERT);
    }

    public void clickConfirmButtonWithJS() {
        clickOnButtonWithJS(Buttons.CONFIRM);
    }

    public void clickPromptButtonWithJS() {
        clickOnButtonWithJS(Buttons.PROMPT);
    }


    public String workWithAlert(boolean accept, String... textToEnter) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        if (textToEnter.length > 0) {
            alert.sendKeys(textToEnter[0]);
        }
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return text;
    }

    enum Buttons {
        ALERT("Click for JS Alert", "jsAlert()"),
        CONFIRM("Click for JS Confirm", "jsConfirm()"),
        PROMPT("Click for JS Prompt", "jsPrompt()");

        private String text;
        private String jsScript;

        Buttons(String s, String jsScript) {
            this.text = s;
            this.jsScript = jsScript;
        }

        public String getText() {
            return text;
        }
    }

}
