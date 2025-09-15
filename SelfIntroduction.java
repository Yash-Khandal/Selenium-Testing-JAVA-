package introduction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // <-- Missing import was added here

public class SelfIntroduction {

    public static void main(String[] args) {

        // The ChromeOptions are still useful for settings like this
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Instantiate the driver
        // With modern Selenium (4.6.0+), you no longer need System.setProperty.
        // Selenium Manager will automatically download and manage the correct driver.
        WebDriver driver = new ChromeDriver(options);

        // Navigate and interact with the browser
        driver.get("https://rahulshettyacademy.com");
        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Quit the browser session
        // It's better to use quit() to close all windows and end the driver session.
        driver.quit();
    }
}