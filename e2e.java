package introduction;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class e2e {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // --- Step 1: Select Cities ---
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='DEL']")).click();
        System.out.println("Selected Origin: DEL");

        By destinationLocator = By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationLocator));
        driver.findElement(destinationLocator).click();
        System.out.println("Selected Destination: MAA");

        // --- Step 2: Select the Date ---
        // This MUST be done before selecting passengers.
        wait.until(ExpectedConditions.attributeContains(By.id("Div1"), "style", "opacity: 0.5"));
        driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
        
        By todayDateLocator = By.cssSelector(".ui-state-default.ui-state-highlight");
        wait.until(ExpectedConditions.elementToBeClickable(todayDateLocator));
        driver.findElement(todayDateLocator).click();
        System.out.println("Successfully selected the date.");

        // --- Step 3: Select Passengers ---
        // This logic is now correctly placed AFTER date selection.
        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
        driver.findElement(By.id("divpaxinfo")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("hrefIncAdt")));

        for (int i = 0; i < 4; i++) {
            driver.findElement(By.id("hrefIncAdt")).click();
        }

        driver.findElement(By.id("btnclosepaxoption")).click();
        Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");
        System.out.println("Selected Passengers: " + driver.findElement(By.id("divpaxinfo")).getText());

        // --- Step 4: Search ---
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        System.out.println("Clicked Search.");
        
        driver.quit();
    }
}