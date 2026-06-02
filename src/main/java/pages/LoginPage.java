package pages;

import org.openqa.selenium.*;

public class LoginPage extends Page{
    private final By emailArea= By.id("login-email");
    private final By clickContinue = By.cssSelector(".p-button-wrapper.p-primary.p-large.p-fluid.email-check-button");
    private final By loginButton = By.cssSelector(".p-button-wrapper.p-primary.p-large.p-fluid.login-button");
    private final By passwordArea = By.xpath("//input[@type='password']");
    private final By closePopup = By.xpath("//button[contains(text(),'Hayır')]");// veya doğru locator
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

    public void clickLogin(){
        try {
            // Sayfayı scroll et
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(loginButton));
            Thread.sleep(1000);
            waitForElement(loginButton);
            driver.findElement(loginButton).click();
            System.out.println("Giriş Yap butonuna tıklandı");
        } catch (Exception e) {
            System.out.println("Giriş Yap hatası: " + e.getMessage());
            e.printStackTrace();
        }
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
}

