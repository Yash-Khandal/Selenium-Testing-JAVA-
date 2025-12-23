package yashautomation.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/flights/");   // Flights main [web:7]

        // Give focus
        actions.moveByOffset(10, 10).click().perform();

        // Close chatbot if present
        Thread.sleep(3000);
        List<WebElement> chatbotCloseImg =
                driver.findElements(By.cssSelector("div.tp-dt-header-icon img[alt='minimize']"));
        if (!chatbotCloseImg.isEmpty()) {
            chatbotCloseImg.get(0).click();
        }

        // Close MMT WORK coachmark if present
        Thread.sleep(1000);
        List<WebElement> workCoachmarkClose =
                driver.findElements(By.cssSelector("span.coachmark"));
        if (!workCoachmarkClose.isEmpty()) {
            workCoachmarkClose.get(0).click();
        }

        // ===== Basic booking (one way) =====
        WebElement fromCity = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("fromCity")));
        fromCity.click();
        WebElement fromInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='From']")));
        fromInput.sendKeys("Delhi");
        WebElement fromOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//p[contains(text(),'New Delhi, India')]")));
        fromOption.click();

        WebElement toCity = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("toCity")));
        toCity.click();
        WebElement toInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']")));
        toInput.sendKeys("Bengaluru");
        WebElement toOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//p[contains(text(),'Bengaluru, India')]")));
        toOption.click();

        // Calendar: open via JS and pick date 19
        if (driver.findElements(By.cssSelector("div.DayPicker")).isEmpty()) {
            WebElement depInput = driver.findElement(By.id("departure"));
            js.executeScript("arguments[0].click();", depInput);
        }
        WebElement calendar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.DayPicker")));
        WebElement targetDate = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'DayPicker-Day') " +
                                 "and not(contains(@class,'DayPicker-Day--disabled')) " +
                                 "and @aria-disabled='false'][.//p[text()='19']]")));
        js.executeScript("arguments[0].click();", targetDate);

        // Click Search to simulate booking
        WebElement searchBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@class,'widgetSearchBtn')]")));
        searchBtn.click();

        // Wait for results page a bit
        Thread.sleep(8000);

        // ===== Go back to Flights home so Offers section is visible again =====
        driver.navigate().back();
        Thread.sleep(5000);   // wait for home to load again

        // ===== Scroll to Offers strip =====
        for (int i = 0; i < 8; i++) {
            js.executeScript("window.scrollBy(0,500);");
            Thread.sleep(800);
        }

        // Click Flights tab in Offers strip (top nav: All Offers | Flights | Hotels...)
        List<WebElement> flightsTabs = driver.findElements(
                By.xpath("//div[contains(.,'Offers')]" +
                         "//span[normalize-space()='Flights']"));
        if (!flightsTabs.isEmpty()) {
            flightsTabs.get(0).click();
        }

        Thread.sleep(3000); // wait for flight offers to appear

        // ===== Capture first 4 offer cards and print =====
        // The highlighted text in your screenshot is inside <p data-cy='superOfferPtl0' ...>
        List<WebElement> offerTitleElements = driver.findElements(
                By.cssSelector("p[data-cy^='superOfferPtl']"));

        List<String> firstFourOffers = new ArrayList<>();
        int count = Math.min(4, offerTitleElements.size());

        for (int i = 0; i < count; i++) {
            String text = offerTitleElements.get(i).getText().trim();
            if (!text.isEmpty()) {
                firstFourOffers.add(text);
            }
        }

        System.out.println("=== FIRST 4 FLIGHT OFFERS ON FLIGHTS PAGE ===");
        for (String offer : firstFourOffers) {
            System.out.println("----------------------------");
            System.out.println(offer);
        }
        System.out.println("Total stored offers: " + firstFourOffers.size());

        System.out.println("=====================================");
        System.out.println("MakeMyTrip booking + first 4 FLIGHT OFFERS script ran successfully ✅");
        System.out.println("=====================================");

        Thread.sleep(5000);
        driver.quit();
    }
}
