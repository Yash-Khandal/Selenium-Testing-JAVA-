package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
    WebDriver driver;
    WebDriverWait wait;

    // --- LOCATORS ---
    By listingCards = By.className("listingCard");
    By okayGotItBtn = By.xpath("//button[text()='OKAY, GOT IT!']");
    By airlineNames = By.className("airlineName");
    By flightPrices = By.className("blackText");

    // --- CONSTRUCTOR ---
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // --- PAGE ACTIONS ---
    public boolean isBlackScreenBlocked() {
        // If 0 flight cards are found, the WAF blocked us
        return driver.findElements(listingCards).size() == 0;
    }

    public void closeOverlayPopup() throws InterruptedException {
        List<WebElement> okayBtn = driver.findElements(okayGotItBtn);
        if (okayBtn.size() > 0) {
            okayBtn.get(0).click();
            Thread.sleep(1000);
        }
    }

    public void extractFlightPrices() {
        List<WebElement> flights = driver.findElements(airlineNames);
        List<WebElement> prices = driver.findElements(flightPrices);

        System.out.println("===== TOP 3 FLIGHT PRICES =====");
        for (int i = 0; i < 3 && i < flights.size(); i++) {
            System.out.println("Airline: " + flights.get(i).getText() + " | Price: " + prices.get(i).getText());
        }
    }
}