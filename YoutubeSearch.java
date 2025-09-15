package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class YoutubeSearch {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com");

        // Wait for the page to load
        Thread.sleep(3000);

        // Locate the search input
        WebElement searchBox = driver.findElement(By.name("search_query"));

        // Type 'AAJTAK' using SHIFT key to make it uppercase and press ENTER
        Actions actions = new Actions(driver);
        actions
            .moveToElement(searchBox)
            .click()
            .keyDown(Keys.SHIFT) // hold SHIFT for uppercase
            .sendKeys("aajtak")
            .keyUp(Keys.SHIFT) // release SHIFT
            .sendKeys(Keys.ENTER)
            .build()
            .perform();

        // Wait for results to load (improve with WebDriverWait in real scripts)
        Thread.sleep(4000);

        // Click the first video
        WebElement firstVideo = driver.findElement(By.xpath("//ytd-video-renderer[1]//a[@id='thumbnail']"));
        firstVideo.click();

        // Optionally: driver.quit();
    }
}
