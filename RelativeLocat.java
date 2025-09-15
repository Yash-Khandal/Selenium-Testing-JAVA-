package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class RelativeLocat {

    public static void main(String[] args) throws InterruptedException {
        // Set the chromedriver path if needed
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/angularpractice/");

        // 1. Print the label above the Name textbox
        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
        System.out.println(driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText());
        Thread.sleep(1000);
        // 2. Click the input below the "Date of Birth" label
        WebElement dateofBirth = driver.findElement(By.cssSelector("[for='dateofBirth']"));
        driver.findElement(with(By.tagName("input")).below(dateofBirth)).click();
        Thread.sleep(1000);
        // 3. Click the checkbox left of the "Check me out if you Love IceCreams!" label
        WebElement iceCreamLabel = driver.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(iceCreamLabel)).click();
        Thread.sleep(1000);
        // 4. Print the label to the right of the first radio button
        WebElement rdb = driver.findElement(By.id("inlineRadio1"));
        System.out.println(driver.findElement(with(By.tagName("label")).toRightOf(rdb)).getText());

        driver.quit();
    }
}
