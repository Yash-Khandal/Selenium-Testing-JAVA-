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
        driver.get("https://www.makemytrip.com/flights/");   
       
        actions.moveByOffset(10, 10).click().perform();

       
        Thread.sleep(3000);
        List<WebElement> chatbotCloseImg =
                driver.findElements(By.cssSelector("div.tp-dt-header-icon img[alt='minimize']"));
        if (!chatbotCloseImg.isEmpty()) {
            chatbotCloseImg.get(0).click();
        }

        
        Thread.sleep(1000);
        List<WebElement> workCoachmarkClose =
                driver.findElements(By.cssSelector("span.coachmark"));
        if (!workCoachmarkClose.isEmpty()) {
            workCoachmarkClose.get(0).click();
        }

      
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


        WebElement depInput = driver.findElement(By.id("departure"));

       
        js.executeScript("arguments[0].click();", depInput);

        
        WebElement calendar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.DayPicker")));

    
        WebElement targetDate = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'DayPicker-Day')]//p[text()='29']")));

       
        js.executeScript("arguments[0].click();", targetDate);


       
        WebElement searchBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@class,'widgetSearchBtn')]")));
        searchBtn.click();

  
        Thread.sleep(8000);

       
        driver.navigate().back();  
        Thread.sleep(5000);

       
        for (int i = 0; i < 2; i++) {
            js.executeScript("window.scrollBy(0,500);");
            Thread.sleep(800);
        }

      
        WebElement flightsTab = driver.findElement(
                By.xpath("//span[normalize-space()='Flights']"));
        flightsTab.click();

        Thread.sleep(3000); 

       
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
