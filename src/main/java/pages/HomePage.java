package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends Page {
    private final By cookieButton = By.id("onetrust-accept-btn-handler");
    private final By girisYapMenu = By.cssSelector(".user-text.navigation-text");
    private final By loginButton = By.cssSelector(".p-button-wrapper.p-primary.p-small.login-btn");
    private final By kategorilerButton = By.xpath("//span[@class='category-tab-text']");
    private final By genderModalClose = By.cssSelector(".modal-section-close");
    private final By myAccount = By.cssSelector(".user-text.navigation-text");    // Ana sayfada arama kutusu önce bir BUTON olarak görünür
    private final By myFavourites = By.cssSelector(".favorites-text navigation-text");
    private final By searchPlaceholder = By.cssSelector("button[data-testid='suggestion-placeholder']");
    // Butona tıklayınca aktifleşen gerçek input
    private final By searchBox = By.cssSelector("input[data-testid='browsing-search-input']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void acceptIfCookiesPresent() {
        try {
            waitForElement(cookieButton);
            driver.findElement(cookieButton).click();
        } catch (Exception e) {
            System.out.println("Çerez pop-up'ı çıkmadı.");
        }
    }

    public void selectGender(String gender) {
        By genderButton = By.xpath("//div[@class='modal-action-button' and text()='" + gender + "']");
        try {
            waitForElement(genderButton);
            driver.findElement(genderButton).click();
        } catch (Exception e) {
            System.out.println("Cinsiyet seçimi pop-up'ı gelmedi.");
        }
    }

    public void closeGenderModalIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(genderModalClose)).click();
            System.out.println("Cinsiyet modal'ı kapatıldı.");
        } catch (Exception e) {
            System.out.println("Cinsiyet modal'ı çıkmadı.");
        }
    }

    public void hoverLoginMenu() {
        waitForElement(girisYapMenu);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(girisYapMenu)).perform();
    }

    public void clickLogin() {
        waitForElement(loginButton);
        driver.findElement(loginButton).click();
    }

    public void searchProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Placeholder butona tıkla — arama inputu bununla açılır
        wait.until(ExpectedConditions.elementToBeClickable(searchPlaceholder)).click();

        // 2. Aktifleşen gerçek input görünene kadar bekle
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));

        // 3. Yaz ve ara
        element.sendKeys(productName);
        element.sendKeys(Keys.ENTER);
    }

    public void selectCategoryPath(String mainCategory, String subCategory, String item) {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Kategoriler'e HOVER
        WebElement katBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(kategorilerButton));
        actions.moveToElement(katBtn).perform();

        // 2. Ana kategoriye HOVER (Erkek) — section-item linkine
        By mainCatLocator = By.xpath("//a[contains(@class,'section-item')][.//p[normalize-space()='" + mainCategory + "']]");
        WebElement mainCat = wait.until(ExpectedConditions.visibilityOfElementLocated(mainCatLocator));
        actions.moveToElement(mainCat).perform();

        // 3. Alt kategoriye HOVER (Giyim)
        By subCatLocator = By.xpath("//a[contains(@class,'subcategory-link') and normalize-space()='" + subCategory + "']");
        WebElement subCat = wait.until(ExpectedConditions.visibilityOfElementLocated(subCatLocator));
        actions.moveToElement(subCat).perform();

        // 4. Ürüne TIKLA (Tişört)
        By itemLocator = By.xpath("//a[contains(@class,'subcategory-item-children-link') and normalize-space()='" + item + "']");
        wait.until(ExpectedConditions.elementToBeClickable(itemLocator)).click();
    }
    private By getCategoryLocator(String categoryName) {
        return By.xpath("//p[@class='section-name' and contains(text(), '" + categoryName + "')]");
    }

    private By getItemLocator(String itemName) {
        return By.xpath("//a[contains(text(), '" + itemName + "')]");
    }

    public String getAccountMenuText() {
        waitForElement(myAccount);
        return driver.findElement(myAccount).getText();
    }

    public String getFavouritesMenuText() {
        waitForElement(myFavourites);
        return driver.findElement(myFavourites).getText();
    }
}