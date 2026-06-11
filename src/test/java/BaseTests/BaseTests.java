package BaseTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTests {
    protected WebDriver driver;
    protected Properties config;

    @BeforeEach
    void setUp(){
        config = new Properties();
        try {
            config.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("config.properties okunamadı!", e);
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.get("https://www.trendyol.com/");
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
        if(driver != null) {
            driver.quit();
        }
    }
}