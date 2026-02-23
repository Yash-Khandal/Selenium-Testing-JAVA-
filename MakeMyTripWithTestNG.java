package yashautomation.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MakeMyTripWithTestNG {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/flights/");
    }

    @Test
    public void bookFlightAndGetOffers() throws InterruptedException {
        
        Thread.sleep(3000); 

        // 1. Close Login Popup safely
        List<WebElement> closeBtn = driver.findElements(By.cssSelector(".commonModal__close"));
        if (closeBtn.size() > 0) {
            closeBtn.get(0).click();
            Thread.sleep(1000); 
        }

        // 2. Click outside to close tooltips
        actions.moveByOffset(10, 10).click().perform();
        Thread.sleep(1000);

        // 3. FROM CITY
        wait.until(ExpectedConditions.elementToBeClickable(By.id("fromCity"))).click();
        WebElement fromInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='From']")));
        fromInput.sendKeys("Delhi");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-autowhatever-1-section-0-item-0"))).click();

        // 4. TO CITY
        List<WebElement> toInputList = driver.findElements(By.xpath("//input[@placeholder='To']"));
        if (toInputList.size() == 0 || !toInputList.get(0).isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("toCity"))).click();
        }
        
        WebElement toInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='To']")));
        toInput.sendKeys("Bengaluru");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-autowhatever-1-section-0-item-0"))).click();

        // 5. DEPARTURE DATE
        String myDate = "//div[@aria-label='Fri Feb 27 2026']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(myDate))).click();

        // 6. CLICK SEARCH
        driver.findElement(By.xpath("//a[text()='Search']")).click();
        Thread.sleep(5000); 

        // 7. CHECK FOR BLACK SCREEN vs REAL RESULTS
        List<WebElement> flightsCheck = driver.findElements(By.className("listingCard"));
        
        if (flightsCheck.size() == 0) {
            // === SCENARIO A: BLACK SCREEN DETECTED ===
            System.out.println("Black screen detected! Navigating back to homepage...");
            driver.navigate().back();
            
            // Wait until the main container is visible again
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("root")));
            Thread.sleep(2000);

            // --- FIX 1: CLOSE CHATBOT ---
            // Using the alt attribute from your screenshot
            List<WebElement> botClose = driver.findElements(By.xpath("//img[@alt='minimize']"));
            if (botClose.size() > 0) {
                System.out.println("Chatbot detected! Closing it...");
                botClose.get(0).click();
                Thread.sleep(1000); // Wait for animation to finish
            }

            // Scroll down to the Offers section
            WebElement offersSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Offers_Listing")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", offersSection);
            
            // --- FIX 2: USING CONTAINS FOR DYNAMIC LOCATORS ---
            // Wait for at least one offer title to render
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@data-cy, 'superOfferPt')]")));

            // 'superOfferPt' matches both titles and subtitles. We use 'not(contains(..., 'x'))' to ensure we only get the titles (e.g., Pt10, Pt12).
            List<WebElement> offerTitles = driver.findElements(By.xpath("//p[contains(@data-cy, 'superOfferPt') and not(contains(@data-cy, 'x'))]"));
            
            // 'superOfferPtx' specifically grabs the subtitles (e.g., Ptx0, Ptx2).
            List<WebElement> offerSubtitles = driver.findElements(By.xpath("//p[contains(@data-cy, 'superOfferPtx')]"));

            Assert.assertTrue(offerTitles.size() > 0, "Failed: No homepage offers were found!");

            System.out.println("===== TOP 3 HOMEPAGE OFFERS =====");
            for (int i = 0; i < 3 && i < offerTitles.size(); i++) {
                System.out.println("Offer Title: " + offerTitles.get(i).getText());
                
                if (i < offerSubtitles.size()) {
                    System.out.println("Details:     " + offerSubtitles.get(i).getText());
                }
                System.out.println("---------------------------------");
            }
            
        } else {
            // === SCENARIO B: NO BLACK SCREEN ===
            System.out.println("Flights loaded successfully! Extracting flight prices...");
            
            List<WebElement> okayBtn = driver.findElements(By.xpath("//button[text()='OKAY, GOT IT!']"));
            if (okayBtn.size() > 0) {
                okayBtn.get(0).click();
                Thread.sleep(1000);
            }

            List<WebElement> flights = driver.findElements(By.className("airlineName"));
            List<WebElement> prices = driver.findElements(By.className("blackText"));

            for (int i = 0; i < 3 && i < flights.size(); i++) {
                System.out.println("Airline: " + flights.get(i).getText() + " | Price: " + prices.get(i).getText());
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
