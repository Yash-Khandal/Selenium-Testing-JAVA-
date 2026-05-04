package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultsPage;

public class MakeMyTripTest {

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        
        // Initialize Page Objects
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        
        driver.get("https://www.makemytrip.com/flights/");
    }

    @Test
    public void bookFlightAndGetOffers() throws InterruptedException {
        Thread.sleep(3000); // Initial load wait

        // Step 1: Clean up the homepage UI
        homePage.closeLoginPopup();
        homePage.dismissTooltips();

        // Step 2: Fill out the flight details
        homePage.enterFromCity("Delhi");
        homePage.enterToCity("Bengaluru");
        homePage.selectDepartureDate("Fri Feb 27 2026");
        homePage.clickSearch();
        Thread.sleep(5000); // Wait for results to process

        // Step 3: Handle the outcome (WAF Block vs Normal Results)
        if (searchResultsPage.isBlackScreenBlocked()) {
            System.out.println("Black screen detected! Navigating back to homepage...");
            driver.navigate().back();
            
            homePage.closeChatbot();
            homePage.scrollAndExtractOffers();
        } else {
            System.out.println("Flights loaded successfully! Extracting flight prices...");
            
            searchResultsPage.closeOverlayPopup();
            searchResultsPage.extractFlightPrices();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}