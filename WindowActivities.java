package introduction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowActivities {

    public static void main(String[] args) {
       
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // driver.get() waits for the page to fully load
        driver.get("http://google.com");

        // driver.navigate.to() does not wait for the page to fully load
        driver.navigate().to("https://rahulshettyacademy.com");

        // Navigates back to the previous page (Google)
        driver.navigate().back();

        // Navigates forward to the page (Rahul Shetty Academy)
        driver.navigate().forward();
        
        driver.quit(); // It's good practice to close the browser
    }
}