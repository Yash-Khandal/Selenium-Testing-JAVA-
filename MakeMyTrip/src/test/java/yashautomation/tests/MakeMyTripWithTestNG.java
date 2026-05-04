package yashautomation.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    JavascriptExecutor js;

    @BeforeClass
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.setBinary("/usr/bin/google-chrome");

        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        actions = new Actions(driver);

        driver.get("https://www.makemytrip.com/flights/");
    }

    @Test
    public void bookFlightAndGetOffers() throws InterruptedException {

        // wait till page JS is ready
        wait.until(d ->
                ((JavascriptExecutor) d)
                        .executeScript("return document.readyState")
                        .equals("complete"));

        Thread.sleep(3000);

        // 1. Close Login Popup
        List<WebElement> closeBtn =
                driver.findElements(By.cssSelector(".commonModal__close"));
        if (!closeBtn.isEmpty()) {
            closeBtn.get(0).click();
            Thread.sleep(1000);
        }

        // click outside
        actions.moveByOffset(5, 5).click().perform();

        // 2. FROM CITY (CI SAFE)
        By fromCity = By.id("fromCity");
        wait.until(ExpectedConditions.presenceOfElementLocated(fromCity));
        js.executeScript("arguments[0].click();", driver.findElement(fromCity));

        WebElement fromInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='From']")));
        fromInput.sendKeys("Delhi");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("react-autowhatever-1-section-0-item-0"))).click();

        // 3. TO CITY
        By toCity = By.id("toCity");
        wait.until(ExpectedConditions.presenceOfElementLocated(toCity));
        js.executeScript("arguments[0].click();", driver.findElement(toCity));

        WebElement toInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='To']")));
        toInput.sendKeys("Bengaluru");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("react-autowhatever-1-section-0-item-0"))).click();

        // 4. DEPARTURE DATE
        js.executeScript("arguments[0].click();",
                driver.findElement(By.id("departure")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.DayPicker")));

        WebElement date29 = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//p[text()='29']")));
        js.executeScript("arguments[0].click();", date29);

        // 5. SEARCH
        driver.findElement(By.xpath("//a[text()='Search']")).click();
        Thread.sleep(6000);

        // 6. RESULT CHECK
        List<WebElement> listingCards =
                driver.findElements(By.className("listingCard"));

        if (listingCards.isEmpty()) {

            System.out.println("Black screen detected → homepage offers");

            driver.navigate().back();
            Thread.sleep(3000);

            List<WebElement> botClose =
                    driver.findElements(By.xpath("//img[@alt='minimize']"));
            if (!botClose.isEmpty()) {
                botClose.get(0).click();
            }

            WebElement offersSection = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.id("Offers_Listing")));
            js.executeScript("arguments[0].scrollIntoView(true);", offersSection);

            List<WebElement> offerTitles = driver.findElements(
                    By.xpath("//p[contains(@data-cy,'superOfferPt') " +
                            "and not(contains(@data-cy,'x'))]"));

            Assert.assertTrue(offerTitles.size() > 0,
                    "No homepage offers found");

            System.out.println("===== TOP OFFERS =====");
            for (int i = 0; i < Math.min(3, offerTitles.size()); i++) {
                System.out.println(offerTitles.get(i).getText());
            }

        } else {

            System.out.println("Flights loaded successfully");

            List<WebElement> airlines =
                    driver.findElements(By.className("airlineName"));
            List<WebElement> prices =
                    driver.findElements(By.className("blackText"));

            for (int i = 0; i < Math.min(3, airlines.size()); i++) {
                System.out.println(
                        airlines.get(i).getText() +
                                " | Price: " +
                                prices.get(i).getText());
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