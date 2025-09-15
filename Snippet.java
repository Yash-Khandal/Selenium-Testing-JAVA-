package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Snippet {

    public static void main(String[] args) throws InterruptedException {
       
        WebDriver driver = new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/angularpractice/");

        // Enter Name
        driver.findElement(By.name("name")).sendKeys("Yash Bhai");
        Thread.sleep(2000);

        // Enter Email
        driver.findElement(By.name("email")).sendKeys("yash@email.com");
        Thread.sleep(2000);

        // Enter Password
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("password123");
        Thread.sleep(2000);

        // Check the 'Love IceCreams' checkbox
        driver.findElement(By.id("exampleCheck1")).click();
        Thread.sleep(2000);

        // Select Gender from dropdown ("Female")
        Select gender = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
        gender.selectByVisibleText("Male");
        Thread.sleep(2000);

        // Select Employment Status (Student)
        driver.findElement(By.id("inlineRadio1")).click();
        Thread.sleep(2000);

        // Enter Date of Birth
        driver.findElement(By.name("bday")).sendKeys("12-12-2000");
        Thread.sleep(2000);

        // Submit the form
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(2000);

        // Extra pause at the end for visibility
        Thread.sleep(2000);

        driver.quit();
    }
}
