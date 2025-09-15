package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignmentnested {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Nested Frames")).click();

        // Switch to the top frameset, then to the middle frame
        driver.switchTo().frame("frame-top");      // Switch to frame-top first
        driver.switchTo().frame("frame-middle");   // Now switch to frame-middle

        // Now you can access the element inside the middle frame
        String text = driver.findElement(By.id("content")).getText();
        System.out.println(text); // This should print: MIDDLE

        // Switching back to the main page is good practice
        driver.switchTo().defaultContent();
        
         driver.quit(); // Enable if you want to close the browser
    }
}
