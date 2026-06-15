package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage extends Page{
    private final By addToCartButton = By.xpath("//span[@class='add-to-cart-button-text']");
    private final By basketCount=By.cssSelector(".basket-count");
    private final By sizeSelector=By.cssSelector("button[data-testid='size-box']");
    private final By onboardingCloseButton = By.cssSelector("button.onboarding__default-renderer-primary-button");
    private final By favouritebutton = By.cssSelector("button.product-action-favorite-button");
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }
    public void addToCart(){
        waitForElement(addToCartButton);
        driver.findElement(addToCartButton).click();
    }
    public String getBasketCount() {
        waitForElement(basketCount);
        return driver.findElement(basketCount).getText();
    }

    public void selectSize() {
        try {
            waitForElement(sizeSelector);
            driver.findElement(sizeSelector).click();
        } catch (Exception e) {
            System.out.println("Beden seçimi gerekmiyor, atlandı.");
        }
    }

    public void addToFavourite(){
        waitForElement(favouritebutton);
        driver.findElement(favouritebutton).click();
    }
    public void closeOnboardingIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
            wait.until(ExpectedConditions.elementToBeClickable(onboardingCloseButton)).click();
            System.out.println("Tanıtım turu kapatıldı.");
        } catch (Exception e) {
            System.out.println("Tanıtım turu çıkmadı.");
        }
    }



}
