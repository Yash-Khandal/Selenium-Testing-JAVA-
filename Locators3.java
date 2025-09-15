package introduction;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Locators3 {

    public static void main(String[] args) {
      
        // With modern Selenium, you don't need System.setProperty.
        // Selenium Manager automatically handles the driver.
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Part 1: XPath Axes Practice
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Finds the first button, then finds its sibling using the CORRECT XPath syntax
        System.out.println(driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[1]")).getText());

        // Finds the first button, goes to parent, then finds the second button
        System.out.println(driver.findElement(By.xpath("//header/div/button[1]/parent::div/button[2]")).getText());
        
        // Part 2: Dynamic Dropdown Practice
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        
        driver.findElement(By.id("divpaxinfo")).click();
        
        // Wait for the passenger increment button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.id("hrefIncAdt")));
        System.out.println("Initial Passengers: " + driver.findElement(By.id("divpaxinfo")).getText());
        
        // Click the "+" button 4 times to make it 5 adults
        for(int i = 0; i < 4; i++) {
            driver.findElement(By.id("hrefIncAdt")).click();
        }

        driver.findElement(By.id("btnclosepaxoption")).click();
        
        String finalPassengerCount = driver.findElement(By.id("divpaxinfo")).getText();
        System.out.println("Final Passengers: " + finalPassengerCount);
        Assert.assertEquals(finalPassengerCount, "5 Adult");
        
        // Finally, close the browser session at the very end
        driver.quit(); 
    }
}