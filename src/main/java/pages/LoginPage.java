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
    private final By closePopup = By.xpath("//button[contains(text(),'Hayır')]");
    private final By passwordError = By.cssSelector("div.label-error");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void enterEmail(String email) {
        waitForElement(emailArea);
        WebElement emailInput = driver.findElement(emailArea);
        emailInput.sendKeys(email);
        emailInput.sendKeys(Keys.ESCAPE);
    }

    public void closePopupIfPresent() {
        try {
            waitForElement(closePopup);
            driver.findElement(closePopup).click();
        } catch (Exception e) {
            // Pop-up yok
        }
    }

    public void enterPassword(String password) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(passwordArea));
        waitForElement(passwordArea);
        driver.findElement(passwordArea).sendKeys(password);
    }
    
    public void clickContinue() {
        waitForElement(clickContinue);
        WebElement button = driver.findElement(clickContinue);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);
        System.out.println("Giriş Yap butonuna tıklandı");
    }
    
    public String getEmailValidationMessage() {
        WebElement emailInput = driver.findElement(emailArea);
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailInput);
    }

    public String getPasswordErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", errorElement);
        return errorElement.getText();
    }
    
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[data-testid='feedback-dialog-body']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", errorElement);
        return errorElement.getText();
    }
}