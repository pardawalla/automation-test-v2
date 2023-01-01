package automation.test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tests {

       private WebDriver driver;
       private WebDriverWait wait;
       private Duration timeoutInSeconds = Duration.ofSeconds(60);
       private Duration pollingTimeInMilliseconds = Duration.ofMillis(10);

       @BeforeClass
       public void Setup() {
              System.setProperty("webdriver.chrome.driver",
                            "/Users/hussain/repos/java-se-cucumber-tests/drivers/chromedriver");
              driver = new ChromeDriver();
              driver.manage().window().maximize();
              wait = new WebDriverWait(driver, timeoutInSeconds);
       }

       // Used in Test(s): OpenBrowser
       private String tradmeUrl = "https://www.trademe.co.nz/";
       private String trademeSiteTitle = "Buy & Sell on NZ's #1 Auction & Classifieds Site | Trade Me";
       @Test(priority = 0)
       public void OpenBrowser() {
              Reporter.log("This test verifies the current selenium compatibility with TestNG by launching the chrome driver");
              Reporter.log("Launching Google Chrome Driver version 108.0.5359.124 for this test");
              driver.get(tradmeUrl);
              Reporter.log("Using https://www.trademe.co.nz/ for testing", true);
              String actualTitle = driver.getTitle();
              Assert.assertEquals(actualTitle, trademeSiteTitle);
       }
}
