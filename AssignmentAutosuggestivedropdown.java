package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssignmentAutosuggestivedropdown {

    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver path
       

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Open the practice page
        driver.get("http://qaclickacademy.com/practice.php");

        // Type "ind" into the autocomplete input
        driver.findElement(By.id("autocomplete")).sendKeys("ind");

        // Wait for suggestions to appear
        Thread.sleep(2000);

        // Press DOWN arrow twice to select the second suggestion
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);

        // Print the currently selected value
        String selectedValue = driver.findElement(By.id("autocomplete")).getAttribute("value");
        System.out.println(selectedValue);

        // Close the browser
        driver.quit();
    }
}
