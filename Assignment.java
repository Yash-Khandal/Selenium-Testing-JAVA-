package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Assignment {

    public static void main(String[] args) {
        // Set the path for the ChromeDriver executable
     

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Open the practice page URL
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // 1. Locate the first checkbox via ID and check it
        WebElement checkbox = driver.findElement(By.id("checkBoxOption1"));
        checkbox.click();

        // 2. Verify if it is checked
        if (checkbox.isSelected()) {
            System.out.println("Checkbox is successfully checked.");
        } else {
            System.out.println("Checkbox is NOT checked.");
        }

        // 3. Uncheck it again
        checkbox.click();

        // 4. Verify if it is unchecked
        if (!checkbox.isSelected()) {
            System.out.println("Checkbox is successfully unchecked.");
        } else {
            System.out.println("Checkbox is still checked.");
        }

        // 5. Count number of checkboxes on the page
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        System.out.println("Total number of checkboxes: " + checkboxes.size());

        // Close browser
        driver.quit();
    }
}
