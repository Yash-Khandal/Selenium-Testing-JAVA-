package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Assertion {

    public static void main(String[] args) {
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // --- Step 1: Verify the checkbox is initially unselected ---
        // Assert.assertFalse() expects the condition to be false.
        // .isSelected() returns false if the checkbox is not checked.
        Assert.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
        System.out.println("Initial state (isSelected): " + driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

        // --- Step 2: Click the checkbox ---
        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
        
        // --- Step 3: Verify the checkbox is now selected ---
        // Assert.assertTrue() expects the condition to be true.
        // .isSelected() now returns true after the click.
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
        System.out.println("Final state (isSelected): " + driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

        // --- Bonus: Count the number of checkboxes on the page ---
        // findElements (plural) returns a list of all matching elements.
        // .size() gets the count of items in the list.
        int checkboxCount = driver.findElements(By.cssSelector("input[type='checkbox']")).size();
        System.out.println("Total number of checkboxes on the page: " + checkboxCount);
        Assert.assertEquals(checkboxCount, 6);

        // --- Step 4: Close the browser ---
        driver.quit();
    }
}