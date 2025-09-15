package introduction;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class vegeterian {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://datatables.net/examples/data_sources/ajax.html");
        
        // Wait for table to load (should really use WebDriverWait in real code)
        Thread.sleep(2000);

        // We will find the row where Name contains "Ashton".
        String searchName = "Ashton";
        boolean found = false;

        do {
            // Get all visible rows (each <tr>)
            List<WebElement> rows = driver.findElements(By.cssSelector("#example tbody tr"));

            // Stream/filter to get the row where 1st <td> contains "Ashton"
            List<WebElement> matchedRows = rows.stream()
                .filter(tr -> {
                    List<WebElement> cells = tr.findElements(By.tagName("td"));
                    // Defensive: only filter data rows
                    return !cells.isEmpty() && cells.get(0).getText().contains(searchName);
                })
                .collect(Collectors.toList());

            if (matchedRows.size() > 0) {
                // Print the full row (all columns)
                for (WebElement row : matchedRows) {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    String rowText = cells.stream()
                                         .map(WebElement::getText)
                                         .collect(Collectors.joining(" | "));
                    System.out.println("Row found for '" + searchName + "': " + rowText);
                }
                found = true;
                break;
            }

            // Click Next button if available
            WebElement nextBtn = driver.findElement(By.id("example_next"));
            if (!nextBtn.getAttribute("class").contains("disabled")) {
                nextBtn.click();
                Thread.sleep(500); // Brief wait for next page to load
            } else {
                System.out.println("'" + searchName + "' not found in any page.");
                break;
            }
        } while (!found);

        driver.quit();
    }
}
