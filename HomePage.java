package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    // --- LOCATORS ---
    By loginPopupCloseBtn = By.cssSelector(".commonModal__close");
    By fromCityBox = By.id("fromCity");
    By fromCityInput = By.xpath("//input[@placeholder='From']");
    By toCityBox = By.id("toCity");
    By toCityInput = By.xpath("//input[@placeholder='To']");
    By suggestionItem = By.id("react-autowhatever-1-section-0-item-0");
    By searchBtn = By.xpath("//a[text()='Search']");
    By rootContainer = By.id("root");
    By chatbotCloseBtn = By.xpath("//img[@alt='minimize']");
    By offersSection = By.id("Offers_Listing");
    By offerTitles = By.xpath("//p[contains(@data-cy, 'superOfferPt') and not(contains(@data-cy, 'x'))]");
    By offerSubtitles = By.xpath("//p[contains(@data-cy, 'superOfferPtx')]");

    // --- CONSTRUCTOR ---
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    // --- PAGE ACTIONS ---
    public void closeLoginPopup() throws InterruptedException {
        List<WebElement> closeBtn = driver.findElements(loginPopupCloseBtn);
        if (closeBtn.size() > 0) {
            closeBtn.get(0).click();
            Thread.sleep(1000);
        }
    }

    public void dismissTooltips() throws InterruptedException {
        actions.moveByOffset(10, 10).click().perform();
        Thread.sleep(1000);
    }

    public void enterFromCity(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(fromCityBox)).click();
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityInput));
        input.sendKeys(city);
        wait.until(ExpectedConditions.elementToBeClickable(suggestionItem)).click();
    }

    public void enterToCity(String city) {
        List<WebElement> toInputList = driver.findElements(toCityInput);
        if (toInputList.size() == 0 || !toInputList.get(0).isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(toCityBox)).click();
        }
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(toCityInput));
        input.sendKeys(city);
        wait.until(ExpectedConditions.elementToBeClickable(suggestionItem)).click();
    }

    public void selectDepartureDate(String dateLabel) {
        // Dynamically locate the date passed from the test
        By dateLocator = By.xpath("//div[@aria-label='" + dateLabel + "']");
        wait.until(ExpectedConditions.elementToBeClickable(dateLocator)).click();
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }

    public void closeChatbot() throws InterruptedException {
        List<WebElement> botClose = driver.findElements(chatbotCloseBtn);
        if (botClose.size() > 0) {
            System.out.println("Chatbot detected! Closing it...");
            botClose.get(0).click();
            Thread.sleep(1000);
        }
    }

    public void scrollAndExtractOffers() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(rootContainer));
        Thread.sleep(2000);

        WebElement offersBlock = wait.until(ExpectedConditions.presenceOfElementLocated(offersSection));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", offersBlock);
        
        // Wait for first offer to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(offerTitles));

        List<WebElement> titles = driver.findElements(offerTitles);
        List<WebElement> subtitles = driver.findElements(offerSubtitles);

        System.out.println("===== TOP 3 HOMEPAGE OFFERS =====");
        for (int i = 0; i < 3 && i < titles.size(); i++) {
            System.out.println("Offer Title: " + titles.get(i).getText());
            if (i < subtitles.size()) {
                System.out.println("Details:     " + subtitles.get(i).getText());
            }
            System.out.println("---------------------------------");
        }
    }
}