package introduction;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sbjimandi {

    public static void main(String[] args) throws InterruptedException {

        // start driver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String[] itemsNeeded = {"Cucumber", "Brocolli", "Beetroot"};

        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4.product-name")));

        addItems(driver, itemsNeeded);

        driver.quit();          // close browser when done
    }

    public static void addItems(WebDriver driver, String[] itemsNeeded) {

        List<String> itemsNeededList = Arrays.asList(itemsNeeded);   // typed list
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

        int j = 0;
        for (int i = 0; i < products.size(); i++) {

            String[] name = products.get(i).getText().split("-");
            String formattedName = name[0].trim();

            if (itemsNeededList.contains(formattedName)) {

                // click the corresponding “ADD TO CART” button
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();

                j++;
                if (j == itemsNeeded.length) break;   // all items found
            }
        }
    }
}