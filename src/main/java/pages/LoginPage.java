package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends Page{
    private final By emailArea= By.id("login-email");
    private final By clickContinue = By.cssSelector(".p-button-wrapper.p-primary.p-large.p-fluid.email-check-button");
    private final By loginButton = By.cssSelector(".p-button-wrapper.p-primary.p-large.p-fluid.login-button");
    private final By passwordArea = By.xpath("//input[@type='password']");
    private final By closePopup = By.xpath("//button[contains(text(),'Hayır')]");// veya doğru locator
    private final By passwordError = By.cssSelector("div.label-error");
    public LoginPage(WebDriver driver)
    {

        super(driver);
    }
    public void enterEmail(String email)
    {
        waitForElement(emailArea);
        WebElement emailInput = driver.findElement(emailArea);
        emailInput.sendKeys(email);
        emailInput.sendKeys(Keys.ESCAPE);   // otomatik tamamlama önerisini kapat
    }

    public void closePopupIfPresent(){
        try {
            waitForElement(closePopup);
            driver.findElement(closePopup).click();
        } catch (Exception e) {
            // Pop-up yok
        }
    }

    public void enterPassword(String password){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(passwordArea));
        waitForElement(passwordArea);
        driver.findElement(passwordArea).sendKeys(password);
    }
    public void clickContinue(){
        waitForElement(clickContinue);
        WebElement button = driver.findElement(clickContinue);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Elementini bul ve görünür olmasını bekle
        WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));

        // Scroll et
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);

        // Tıklanabilir olmasını bekle ve tıkla
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        System.out.println("Giriş Yap butonuna tıklandı");
    }
    public String getErrorMessage(){
        try {
            Thread.sleep(2000); // pop-up'ın çıkmasını bekle
            return driver.findElement(By.cssSelector("p[data-testid='feedback-dialog-body']")).getText();
        } catch (Exception e) {
            return "Pop-up bulunamadı";
        }
    }
    public String getEmailValidationMessage() {
        WebElement emailInput = driver.findElement(emailArea);
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailInput);
    }

    public String getPasswordErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Elementini bul ve görünür olmasını bekle
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));

        // Scroll et (mesaj ekranın aşağısında olabilir)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", errorElement);

        return errorElement.getText();
    }
}


