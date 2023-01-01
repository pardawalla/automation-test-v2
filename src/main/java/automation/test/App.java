package automation.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.trademe.co.nz");
        driver.quit();
    }
}
