package org.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CookiesTests extends BaseTestClass {

    private static final String USER_NAME = "tomsmith";
    private static final String USER_PASS = "SuperSecretPassword!";

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void cookiesTest() {
        driver.findElement(By.id("username")).sendKeys(USER_NAME);
        driver.findElement(By.id("password")).sendKeys(USER_NAME);
        driver.findElement(By.cssSelector(".radius")).click();

        for(Cookie cookie : driver.manage().getCookies()) {
            System.out.println(cookie.getName() + " --- " + cookie.getValue());
        }

        System.out.println();
        Cookie cookie = new Cookie("SomeCookie", "some val");
        driver.manage().addCookie(cookie);

        Cookie cookieSome = driver.manage().getCookieNamed("SomeCookie");
        System.out.println(cookie.getName() + " --- " + cookie.getValue());
        driver.manage().deleteCookieNamed("SomeCookie");

        cookieSome = driver.manage().getCookieNamed("SomeCookie");
        System.out.println(cookieSome);
    }
}
