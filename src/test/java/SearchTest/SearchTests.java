package SearchTest;

import BaseTests.BaseTests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTests extends BaseTests {

    @Test
    public void searchProductTest(){
        HomePage homePage = new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.closeGenderModalIfPresent();

        homePage.searchProduct("ayakkabı");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("ayakkab"));
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).toLowerCase().contains("ayakkab"),
                "Arama sonucu URL'ye yansımadı!");

        System.out.println("Arama testi başarılı! URL: " + driver.getCurrentUrl());
    }
}