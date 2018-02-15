package com.appsenseca.pageobjects;

import com.appsenseca.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by utestken on 15-09-06.
 */
public class OutlookSignInPage {
    public void fillInUsername(WebDriver driver, String username) {
        // username input
        WebElement userInput = driver.findElement(By.cssSelector("#idDiv_PWD_UsernameExample"));
        Actions actions = new Actions(driver);
        actions.moveToElement(userInput).click().sendKeys(username).perform();
    }

    public void fillInPassword(WebDriver driver, String password) {
        // password input
        WebElement userInput = driver.findElement(By.cssSelector("#idDiv_PWD_PasswordExample"));
        Actions actions = new Actions(driver);
        actions.moveToElement(userInput).click().sendKeys(password).perform();
    }


    public OutlookEmailHomePage clickSignIn(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.id("idSIButton9"));
        WebUtil.click(driver, By.id("idSIButton9"));



        return PageFactory.initElements(driver, OutlookEmailHomePage.class);
    }
}
