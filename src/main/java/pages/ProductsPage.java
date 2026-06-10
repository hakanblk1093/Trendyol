package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductsPage extends Page {

    private final By filterSearchArea = By.cssSelector("input[placeholder*='Marka']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void searchBrand(String brandName) throws InterruptedException {
        waitForElement(filterSearchArea);
        driver.findElement(filterSearchArea).sendKeys(brandName);

        Thread.sleep(2500);  // Arama sonuçlarının gelmesini bekle

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='checkbox-label' and contains(text(), '" + brandName + "')]")
        ));
    }

    public void selectBrandFromSearch(String brandName) {
      By resultLocator = By.xpath("//span[@class='checkbox-label' and contains(text(), '" + brandName + "')]");
     waitForElement(resultLocator);
    driver.findElement(resultLocator).click();
    System.out.println(brandName + " seçildi");
    }
}