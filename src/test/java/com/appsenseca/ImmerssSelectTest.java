package com.appsenseca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;

/**
 * Created by appken on 2016-01-12.
 * <p>
 * This is an interesting test case posted by student Maryam
 */
public class ImmerssSelectTest {
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


    // TODO: Below code just to show as how it works, it can be refactored by student yourself
    @Test
    public void selectBirthDate() throws InterruptedException {
        driver.get("https://immerss.com/becoming-a-presenter/new_user_profile?utm_campaign=email&utm_source=sendgrid.com&utm_medium=email");

        final String monthToChoose = "January";
        final String dayToChoose = "31";
        final String yearToChoose = "1999";

        // 1. click month
        WebElement monthOptions = driver.findElement(By.cssSelector("#select2-chosen-1"));
        monthOptions.click();

        // 2. select January
        WebElement month = driver.findElement(By.xpath("//div[@id='select2-drop']//div[text()='" + monthToChoose + "']"));
        month.click();


        // 3. click day
        WebElement dayOptions = driver.findElement(By.cssSelector("#select2-chosen-2"));
        dayOptions.click();

        // 4. select 31
        WebElement day = driver.findElement(By.xpath("//div[@id='select2-drop']//div[text()='" + dayToChoose + "']"));
        day.click();

        // 5. click year
        WebElement yearOptions = driver.findElement(By.cssSelector("#select2-chosen-3"));
        yearOptions.click();

        // 6. select 2001
        WebElement year = driver.findElement(By.xpath("//div[@id='select2-drop']//div[text()='" + yearToChoose + "']"));
        year.click();

        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
