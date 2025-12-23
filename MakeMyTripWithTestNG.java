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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MakeMyTripWithTestNG {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
    }

    @Test
    public void bookFlightAndReadOffers() throws InterruptedException {

        driver.get("https://www.makemytrip.com/flights/");

        // Give focus
        actions.moveByOffset(10, 10).click().perform();

        // Close chatbot
        Thread.sleep(3000);
        List<WebElement> chatbotCloseImg =
                driver.findElements(By.cssSelector("div.tp-dt-header-icon img[alt='minimize']"));
        if (!chatbotCloseImg.isEmpty()) {
            chatbotCloseImg.get(0).click();
        }

        // Close MMT WORK coachmark
        Thread.sleep(1000);
        List<WebElement> workCoachmarkClose =
                driver.findElements(By.cssSelector("span.coachmark"));
        if (!workCoachmarkClose.isEmpty()) {
            workCoachmarkClose.get(0).click();
        }

        // ===== FROM =====
        WebElement fromCity = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("fromCity")));
        fromCity.click();

        WebElement fromInput = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@placeholder='From']")));
        fromInput.sendKeys("Delhi");

        WebElement fromOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//p[contains(text(),'New Delhi, India')]")));
        fromOption.click();

        // ===== TO =====
        WebElement toCity = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("toCity")));
        toCity.click();

        WebElement toInput = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@placeholder='To']")));
        toInput.sendKeys("Bengaluru");

        WebElement toOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//p[contains(text(),'Bengaluru, India')]")));
        toOption.click();

        // ===== CALENDAR =====

        WebElement depInput = driver.findElement(By.id("departure"));
        js.executeScript("arguments[0].click();", depInput);  // open calendar safely

        WebElement calendar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.DayPicker")));

        WebElement targetDate = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'DayPicker-Day')]//p[text()='29']")));
        js.executeScript("arguments[0].click();", targetDate); // JS click date

        // ===== SEARCH =====

        WebElement searchBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@class,'widgetSearchBtn')]")));
        searchBtn.click();

        Thread.sleep(8000);

        // ===== BACK TO SEE OFFERS =====

        driver.navigate().back();
        Thread.sleep(5000);

        // Scroll to Offers strip
        js.executeScript("window.scrollBy(0,600);");
        Thread.sleep(1000);

        // Click Flights tab in Offers strip
        WebElement flightsTab = driver.findElement(
                By.xpath("//span[normalize-space()='Flights']"));
        flightsTab.click();

        Thread.sleep(3000);

        // Grab first 4 offer titles
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

        System.out.println("=== FIRST 4 FLIGHT OFFERS (TestNG run) ===");
        for (String offer : firstFourOffers) {
            System.out.println("----------------------------");
            System.out.println(offer);
        }
        System.out.println("Total stored offers: " + firstFourOffers.size());
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        if (driver != null) {
            driver.quit();
        }
    }
}
