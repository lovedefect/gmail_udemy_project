package com.appsenseca.util;

import com.appsenseca.pageobjects.OutlookSignInPage;
import com.appsenseca.pageobjects.SignInPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by appken on 15-05-03.
 */
public class WebUtil {
    final static int WAIT_TIME_OUT = 30;

    public static SignInPage goToSignInPage(WebDriver driver) {
        driver.get("http://gmail.com");
        return PageFactory.initElements(driver, SignInPage.class);
    }

    public static void click(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    public static void click(WebDriver driver, WebElement element) {
        element.click();
    }

    public static void waitForElementVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static boolean isElementExist(WebDriver driver, By by) {
        return driver.findElements(by).size() > 0;
    }

    public static void clearAndSendKeys(WebDriver driver, By by, String s) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(s);
    }

    public static String getElementText(WebDriver driver, By by) {
        waitForElementVisible(driver, by);
        WebElement subjectArea = driver.findElement(by);
        return subjectArea.getText();
    }

    public static boolean isElementDisplayed(WebDriver driver, By by) {
        return driver.findElement(by).isDisplayed();
    }

    public static OutlookSignInPage goToOutlookSignInPage(WebDriver driver) {
        driver.get("https://outlook.com");
        return PageFactory.initElements(driver, OutlookSignInPage.class);
    }

    public static String tryDismissAlert(WebDriver driver) {
        String alertText = "";
        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.dismiss();

        } catch (NoAlertPresentException nape) {
            // nothing to do, because we only want to close it when pop up
        }
        return alertText;
    }

    public static void waitForSeconds(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Path takeScreenshotAs(WebDriver driver, String file) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path path = Paths.get(System.getProperty("user.dir"), file);
        FileUtils.copyFile(scrFile, path.toFile());
        return path;
    }
}
