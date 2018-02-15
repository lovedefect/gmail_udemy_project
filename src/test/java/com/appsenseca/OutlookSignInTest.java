package com.appsenseca;

import com.appsenseca.categories.Critical;
import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.OutlookEmailHomePage;
import com.appsenseca.pageobjects.OutlookSignInPage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.util.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by utestken on 15-09-06.
 */
public class OutlookSignInTest {
    WebDriver driver;

    @Before
    public void setDriver(){
        String browserName = System.getenv("browser");
        if(browserName!= null && browserName.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        }else{
            driver = new FirefoxDriver();
        }
    }

    @Category({Critical.class})
    @Test
    public void outlookLoginShouldBeSuccessful() throws InterruptedException {
        //1. Go to Gmail website
        OutlookSignInPage outlookSignInPage = WebUtil.goToOutlookSignInPage(driver);

        //1.1 seems you have to click Sign In button first to be able to send keys
        outlookSignInPage.clickSignIn(driver);

        //2. Fill in username --
        // TODO: change to your own user name
        outlookSignInPage.fillInUsername(driver, "udemyken@outlook.com");

        //3. Fill in password --
        // TODO: change to your own password
        outlookSignInPage.fillInPassword(driver, "Uken@123");

        //4. click sign in
        OutlookEmailHomePage outlookEmailHomePage = outlookSignInPage.clickSignIn(driver);

        //5. wait for page to load
        // TODO: change to your own verifications
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
