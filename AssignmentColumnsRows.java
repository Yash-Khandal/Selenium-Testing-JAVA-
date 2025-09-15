package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class AssignmentColumnsRows {

    public static void main(String[] args) {
      
        
        WebDriver driver = new ChromeDriver();


        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        
        WebElement table = driver.findElement(By.id("product"));

       
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int rowCount = rows.size() - 1; // Exclude the header row

        
        List<WebElement> cols = rows.get(0).findElements(By.tagName("th"));
        int colCount = cols.size();

        System.out.println("Number of columns: " + colCount);
        System.out.println("Number of rows: " + rowCount);

        
        List<WebElement> secondRowTds = rows.get(1).findElements(By.tagName("td"));
        System.out.print("Second row text: ");
        for (WebElement cell : secondRowTds) {
            System.out.print(cell.getText() + " | ");
        }
        System.out.println();

        
        driver.quit();
    }
}
