package introduction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignmentagain {
    public static void main(String[] args) throws InterruptedException {

    
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        
        driver.get("http://qaclickacademy.com/practice.php");

      
        driver.findElement(By.xpath("//*[@id='checkbox-example']/fieldset/label[2]/input")).click();
        String opt = driver.findElement(By.xpath("//*[@id='checkbox-example']/fieldset/label[2]")).getText();

        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
        Select s = new Select(dropdown);
        s.selectByVisibleText(opt);
        Thread.sleep(2000);


        driver.findElement(By.name("enter-name")).sendKeys(opt);
        driver.findElement(By.id("alertbtn")).click();

        Thread.sleep(2000);
        String text = driver.switchTo().alert().getText();
        if (text.contains(opt)) {
            System.out.println("Alert message success");
        } else {
            System.out.println("Something wrong with execution");
        }
        driver.switchTo().alert().accept(); // close alert

       
       

        //driver.quit();
    }
}