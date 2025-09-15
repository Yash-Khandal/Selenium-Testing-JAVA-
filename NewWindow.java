package introduction;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewWindow {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Set the ChromeDriver path if needed
        // System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        // Open new window
        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(1000);
        // Get window handles
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindowId = it.next();
        String childWindow = it.next();
        
        // Switch to new window and navigate
        driver.switchTo().window(childWindow);
        driver.get("https://rahulshettyacademy.com/");
        Thread.sleep(1000);
        String courseName = driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p']")).get(1).getText();
        Thread.sleep(1000);
        // Switch back to parent and enter the name
        driver.switchTo().window(parentWindowId);
        WebElement name = driver.findElement(By.cssSelector("[name='name']"));
        name.sendKeys(courseName);
        Thread.sleep(1000);
        // Take screenshot of the field
        File file = name.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("logo.png"));
        Thread.sleep(1000);
        // Print height and width of the field
        System.out.println("Height: " + name.getRect().getDimension().getHeight());
        System.out.println("Width: " + name.getRect().getDimension().getWidth());

        driver.quit();
    }

}
