package LoginTests;

import BaseTests.BaseTests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTests {

    @Test
    public void loginSuccessFull() {
        HomePage homePage=new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");
        homePage.hoverLoginMenu();
        homePage.clickLogin();
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmail(config.getProperty("trendyol.email"));
        loginPage.clickContinue();
        loginPage.enterPassword(config.getProperty("trendyol.password"));
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.textToBe(By.cssSelector(".user-text.navigation-text"), "Hesabım"));
        assertEquals("Hesabım", homePage.getAccountMenuText(),
                "Giriş başarısız - Hesabım görünmüyor");
    }


    @Test
    public void loginFailed(){
        HomePage homePage=new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");
        homePage.hoverLoginMenu();
        homePage.clickLogin();
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmail("asdasdasdas");
        loginPage.clickContinue();
        String expectedMessage = "Geçersiz e-posta adresi";
        String actualMessage = loginPage.getErrorMessage();

        assertTrue(actualMessage.equals(expectedMessage),
                "Beklenen: " + expectedMessage + ", Alınan: " + actualMessage);

    }
    @Test
    public void loginFailedMissingDomain() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");
        homePage.hoverLoginMenu();
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail("hakanblk1093@");// @ var, domain yok

        loginPage.clickContinue();

        String expectedMessage = "Geçersiz e-posta adresi";
        String actualMessage = loginPage.getErrorMessage();

        assertEquals(expectedMessage, actualMessage,
                "Beklenen: " + expectedMessage + ", Alınan: " + actualMessage);
    }

    @Test
    public void loginFailedEmptyEmail() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");
        homePage.hoverLoginMenu();
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);

        // email'i boş bırak, direkt devam et
        loginPage.clickContinue();

        String expectedMessage = "Lütfen bu alanı doldurun.";
        String actualMessage = loginPage.getEmailValidationMessage();

        assertEquals(expectedMessage, actualMessage,
                "Beklenen: " + expectedMessage + ", Alınan: " + actualMessage);
    }

    @Test
    public void loginFailedEmptyPassword() {
        HomePage homePage=new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");
        homePage.hoverLoginMenu();
        homePage.clickLogin();
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmail(config.getProperty("trendyol.email"));
        loginPage.clickContinue();
        loginPage.clickLogin();
        String expectedMessage="E-postanızı ve şifrenizi girin";
        String actualMessage=loginPage.getPasswordErrorMessage();

        assertEquals(expectedMessage,actualMessage,"Hata mesajları uyuşmamaktadır");

    }
     

       @Test
public void loginFailedWrongPassword() {
    HomePage homePage = new HomePage(driver);
    homePage.acceptIfCookiesPresent();
    homePage.selectGender("Erkek");
    homePage.hoverLoginMenu();
    homePage.clickLogin();
    LoginPage loginPage = new LoginPage(driver);

    loginPage.enterEmail(config.getProperty("trendyol.email"));
    loginPage.clickContinue();
    loginPage.enterPassword("yanlissifreyanlissifre");
    loginPage.clickLogin();

    String expectedMessage = "E-posta adresiniz ve/veya şifreniz hatalı.";  // ← "ve/veya" + nokta
    String actualMessage = loginPage.getErrorMessage();

    assertEquals(expectedMessage, actualMessage,
            "Beklenen: " + expectedMessage + ", Alınan: " + actualMessage);
}


}
