package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StaticDropDown {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // --- Select Origin City ---
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        System.out.println("Selected Origin: BLR");
        
        // Use an explicit wait for the destination dropdown to be ready
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By destinationLocator = By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationLocator));
        
        // --- Select Destination City ---
        driver.findElement(destinationLocator).click();
        System.out.println("Selected Destination: MAA");
        
        // End the session
        //driver.quit();
    }
}