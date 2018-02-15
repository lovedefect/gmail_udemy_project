package com.appsenseca;

import com.appsenseca.categories.Major;
import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.util.WebUtil;
import com.google.common.base.Stopwatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeCompareTest {

    WebDriver driver;

    @Before
    public void setDriver() {
        String browserName = System.getenv("browser");
        if (browserName != null && browserName.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
    }

    @Category({Major.class})
    @Test
    public void gmailSendAndReceiveEmailTest() {
        // 1. Click sign in
        //1. Go to Gmail website
        SignInPage signInPage = WebUtil.goToSignInPage(driver);
        //2. Fill in username
        signInPage.fillInUsername(driver, "udemyken@gmail.com");

        //3. Fill in password
        signInPage.fillInPassword(driver, "udemy13579");

        //4. click sign in
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        //5. Click Inbox
        emailHomePage.clickFolderByName(driver, "Inbox");

        // hard-coded wait to make sure no caches and fair start for xpath and css
        WebUtil.waitForSeconds(10);

        // run 5 times, and calculate the average time cost to find new emails
        long totalXpathTime = 0;
        long totalCssTime = 0;
        final Stopwatch stopwatch = Stopwatch.createStarted();

        for(int i=0; i<5; i++){
            // xpath begins
            stopwatch.reset().start();
            List<WebElement> xpathEmails = driver.findElements(By.xpath("//tr[@class='zA zE']"));
            long oneXpathTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("XPATH TEST: elapsed time = " + oneXpathTime + " MILLISECONDS");
            System.out.println("emails found: " + xpathEmails.size());

            totalXpathTime = oneXpathTime + totalXpathTime;

            // css begins
            stopwatch.reset().start();
            List<WebElement> cssEmails = driver.findElements(By.cssSelector("tr[class='zA zE']"));
            long oneCssTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("CSS TEST: elapsed time = " + oneCssTime + " MILLISECONDS");
            System.out.println("emails found: " + cssEmails.size());

            totalCssTime = oneCssTime + totalCssTime;
        }
        Double averageXpathTime = totalXpathTime/5.0;
        Double averageCssTime = totalCssTime/5.0;

        System.out.println("Average XPATH Time: " + averageXpathTime);
        System.out.println("Average CSS Time: " + averageCssTime);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
