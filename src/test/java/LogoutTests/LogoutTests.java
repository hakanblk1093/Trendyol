package LogoutTests;

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

public class LogoutTests extends BaseTests {

    @Test
    public void LogoutTest(){
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
        homePage.logout();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.cssSelector(".user-text.navigation-text"), "Giriş Yap"));
        assertEquals("Giriş Yap", driver.findElement(By.cssSelector(".user-text.navigation-text")).getText(),
                "Logout sonrası Giriş Yap görünmüyor!");
        System.out.println("Logout testi başarılı!");

    }
}
