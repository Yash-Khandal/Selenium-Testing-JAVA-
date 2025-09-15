package introduction;

import java.util.Arrays;
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sbjimandi3 {

    public static void main(String[] args) {
       
        WebDriver driver = new ChromeDriver();
        
        // FIXED: Correct method name and Duration syntax
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        // FIXED: Correct WebDriverWait constructor
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        String[] itemsNeeded = {"Cucumber", "Brocolli", "Beetroot"};
        
        try {
            driver.get("https://rahulshettyacademy.com/seleniumPractise/");
            
            addItems(driver, itemsNeeded);
            
            // FIXED: Correct method name and selector syntax
            driver.findElement(By.cssSelector("img[alt='Cart']")).click();
            driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
            
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
            driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
            
            explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.promoBtn")));
            driver.findElement(By.cssSelector("button.promoBtn")).click();
            
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
            System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
            
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void addItems(WebDriver driver, String[] itemsNeeded) {
        List<String> itemsNeededList = Arrays.asList(itemsNeeded);
        
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        
        int j = 0;
        for (int i = 0; i < products.size(); i++) {
            String[] name = products.get(i).getText().split("-");
            String formattedName = name[0].trim();
            
            if (itemsNeededList.contains(formattedName)) {
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                
                j++;
                if (j == itemsNeeded.length) {
                    break;
                }
            }
        }
    }
}