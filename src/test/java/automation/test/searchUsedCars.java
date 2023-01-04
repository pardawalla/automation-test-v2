package automation.test;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class searchUsedCars {

       private String workingDir = System.getProperty("user.dir");
       private String myChromeDriverPath = workingDir + "/src/test/resources/drivers/chromedriver";

       // The tests currently verify against listing with id 3900249796. If this
       // listing no longer exists please update the test data below appropriately
       // with the updated listing and correct values for the data parameters.
       // Test data for test(s): SearchUsedCars, ExistingListing
       private String existingListingUrl = "https://www.trademe.co.nz/a/motors/cars/toyota/c-hr/listing/3855382911";
       // Test data for test: SearchUsedCars
       private String keywordsText = "NZU854";
       // For the keywordsText variable, please only enter the number plate. As the keywordsText is set at the expected 
       // Number plate for later tests. Using the number plate as the keyword ensures only one listing is returned. If multiple
       // results are returned, it becomes harder to find the listing we are interested in as the listing order by the search
       // cannot be always guaranteed.
       private String makeText = "Toyota";
       private String modelText = "C-HR";
       private List<String> bodyStyleText = List.of(" RV/SUV ", " Coupe ");
       private String searchResultHeader = "Toyota C-HR for sale";
       // Test data for test(s): ExistingListing
       private String numberPlateExpected = keywordsText;
       private String kilometersExpected = "39,135";
       private String bodyExpected = "RV/SUV";
       private String seatsExpected = "5";

       // Private variables
       private WebDriver driver;
       private WebDriverWait wait;
       private Duration timeoutInSeconds = Duration.ofSeconds(60);
       private Duration pollingTimeInMilliseconds = Duration.ofMillis(10);

       private void waitUntilSelectOptionsPopulated(final Select select) {
              new FluentWait<WebDriver>(driver)
                            .withTimeout(timeoutInSeconds)
                            .pollingEvery(pollingTimeInMilliseconds)
                            .until(new Function<WebDriver, Boolean>() {
                                   public Boolean apply(WebDriver d) {
                                          return (select.getOptions().size() > 1);
                                   }

                            });
       }

       @BeforeClass
       public void Setup() {
              System.setProperty("webdriver.chrome.driver", myChromeDriverPath);
              driver = new ChromeDriver();
              driver.manage().window().maximize();
              wait = new WebDriverWait(driver, timeoutInSeconds);
       }

       @AfterSuite
       public void TearDown() {
              driver.quit();
       }

       // Test data for test OpenBrowser
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

       // Test data for test TrademeMotors
       private String trademeMotorsUrl = "https://www.trademe.co.nz/a/motors";
       private String trademeMotorsSiteTitle = "Cars And Vehicles For Sale | Trade Me Motors";

       @Test(priority = 1)
       public void TradmeMotors() {
              driver.get(trademeMotorsUrl);
              driver.findElement(By.linkText("Motors")).click();
              wait.until(ExpectedConditions.titleContains(trademeMotorsSiteTitle));
              String actualTitle = driver.getTitle();
              Assert.assertEquals(actualTitle, trademeMotorsSiteTitle);

       }

       @Test(priority = 2)
       public void SearchUsedCars() {
              driver.get(trademeMotorsUrl);
              wait.until(ExpectedConditions.titleContains(trademeMotorsSiteTitle));
              // Select Used Cars
              driver.findElement(By.linkText("Used")).click();
              // Enter blue for keywords
              WebElement keywords = driver.findElement(By.name("keyword"));
              keywords.sendKeys(keywordsText);
              // select Honda as the Make
              WebElement make = driver.findElement(By.name("selectedMake"));
              Select makeChosen = new Select(make);
              makeChosen.selectByVisibleText(makeText);

              // Select Civic as the Model
              WebElement model = driver.findElement(By.name("searchParams.model"));
              Select modelChosen = new Select(model);
              waitUntilSelectOptionsPopulated(modelChosen);
              modelChosen.selectByVisibleText(modelText);

              // Select Body Types
              WebElement bodyStyle = driver
                            .findElement(By.className("tm-motors-search-bar__dropdown-multi-select-text"));
              // Expand the Body Style drop-down box
              bodyStyle.click();
              // Select all the options needed
              for (String tmpStr : bodyStyleText) {
                     String xpathStr = "//span[text()='" + tmpStr + "']";
                     System.out.println(xpathStr);
                     driver.findElement(By.xpath(xpathStr)).click();
              }

              // Close the Body Style drop-down box
              bodyStyle.click();

              // Click the Search button
              driver.findElement(By.xpath("//button[@type='submit']")).click();

              wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
              String headerText = driver.findElement(By.tagName("h1")).getText();
              Assert.assertEquals(headerText, searchResultHeader);

              // Only one option should have been returned. So we select that.
              // TO-DO confirm results are always returned
              driver.findElement(By.xpath("(//div[@class='o-card']//a)[1]")).click();
              wait.until(ExpectedConditions.urlToBe(existingListingUrl));
              String currentUrl = driver.getCurrentUrl();
              Assert.assertEquals(currentUrl, existingListingUrl);

       }

       @Test(priority = 3)
       public void ExistingListing() {
              driver.get(existingListingUrl);

              WebElement numberPlate = driver.findElement(By.xpath(("(//span[@class='o-tag__content']//div)[7]")));
              String actualNumberPlate = numberPlate.getText().trim();
              System.out.println(actualNumberPlate);
              // Using assert true, as sometimes the text contain the string "Number plate"
              // and sometimes it doesn't :/
              Assert.assertTrue(actualNumberPlate.contains(numberPlateExpected));

              WebElement vehicleOdometer = driver.findElement(By.xpath(("(//span[@class='o-tag__content']//div)[1]")));
              String actualKilometers = vehicleOdometer.getText().trim();
              System.out.println(actualKilometers);
              // Assert.assertTrue(actualKilometers.contains(kilometersExpected));
              // The km text is always returned as a comma seperated number. When writing the test data, we can either
              // enter the expected value as `39,000` or `39000` km. The code below removes the `,` from the number 
              // string and than simply compares the number string without the `,` seperator.
              String actualKmNum = actualKilometers.replaceAll("[^0-9]", "");
              System.out.println("ACTUAL km number string: " + actualKmNum);
              String expectedKmNum = kilometersExpected.replaceAll("[^0-9]", "");
              System.out.println("EXPECTED km number string: " + expectedKmNum);
              Assert.assertEquals(actualKmNum, expectedKmNum);

              WebElement body = driver.findElement(By.xpath(("(//span[@class='o-tag__content']//div)[2]")));
              String actualBody = body.getText().trim();
              System.out.println(actualBody);
              Assert.assertTrue(actualBody.contains(bodyExpected));

              WebElement seats = driver.findElement(By.xpath(("(//span[@class='o-tag__content']//div)[3]")));
              String actualSeats = seats.getText().trim();
              System.out.println(actualSeats);
              Assert.assertTrue(actualSeats.contains(seatsExpected));
       }

       // @Test
       // public void extractKm(){
       // // Reference: https://attacomsian.com/blog/java-extract-digits-from-string
       // kilometersExpected = "100,103,001";
       // String expectedKmNum = kilometersExpected.replaceAll("[^0-9]", "");
       // System.out.println("\n\n\nEXPECTED the number is " + expectedKmNum);
       // }

       // @Test
       // public void getWorkingDir(){
       // // Reference:
       // https://stackoverflow.com/questions/4032957/how-to-get-the-real-path-of-java-application-at-runtime
       // String workingDir = System.getProperty("user.dir");
       // System.out.println(workingDir);
       // String myDriverPath = workingDir +
       // "/src/test/resources/drivers/chromedriver";
       // System.out.println("driver path is:" + myDriverPath);
       // Assert.assertEquals("/Users/hussain/repos/automation-test-v2/automation-test-v2/src/test/resources/drivers/chromedriver",
       // myDriverPath);
       // }
}
