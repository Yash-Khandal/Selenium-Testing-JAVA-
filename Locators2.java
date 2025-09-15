package introduction;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




public class Locators2 {

    public static void main(String[] args) throws InterruptedException {

        String name = "rahul";
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String password = getPassword(driver);
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername")).sendKeys(name);
        driver.findElement(By.name("inputPassword")).sendKeys(password);
        driver.findElement(By.className("signInBtn")).click();

        // Wait for <p> tag to appear and get its text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
        String actualMessage = driver.findElement(By.tagName("p")).getText().trim();
        System.out.println("Actual login message: " + actualMessage);
    
      
        
       Assert.assertEquals(actualMessage, "You are successfully logged in.");

        // Wait for greeting message to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='login-container'] h2")));
        String actualGreeting = driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText().trim();
        System.out.println("Actual greeting: " + actualGreeting);
        Assert.assertEquals(actualGreeting, "Hello " + name + ",");

        driver.findElement(By.xpath("//*[text()='Log Out']")).click();
        driver.close();
    }

    public static String getPassword(WebDriver driver) throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.linkText("Forgot your password?")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".reset-pwd-btn")));

        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        String passwordText = driver.findElement(By.cssSelector("form p")).getText();
        
        // Extract password using split
        String[] passwordArray = passwordText.split("'");
        // **FIX IS HERE: The correct index for the password is 1, not 12.**
        String password = passwordArray[1];
        
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        return password;
    }
}