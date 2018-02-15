package com.appsenseca;

import com.appsenseca.categories.Critical;
import com.appsenseca.categories.Major;
import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.EmailViewPage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.util.WebUtil;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.appsenseca.util.WebUtil.takeScreenshotAs;

public class TakeScreenShotTest {

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

    @Category({Critical.class})
    @Test
    public void gmailLoginShouldBeSuccessful() throws Exception {
        //1. Go to Gmail website
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

        //2. Fill in username
        signInPage.fillInUsername(driver, "udemyken@gmail.com");

        //3. Fill in password
        signInPage.fillInPassword(driver, "udemy13579");

        //4. click sign in
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        //5. verify user did sign in
        Assert.assertTrue("Inbox should exist", emailHomePage.isInboxExist(driver));
        //6. sign out
        signInPage = emailHomePage.signOut(driver);

        //7. verified user did sign out
        try {
            Assert.assertTrue("signIn button should exist", !signInPage.isSignInButtonExist(driver));
        } catch (AssertionError assertionError) {
            String fileName = takeScreenshotAs(driver, "gmailLoginShouldBeSuccessful.png").toString();
            System.out.println("Test Failed, saved screenshot at: " + fileName);
            throw assertionError;
        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
