package FilterTests;

import BaseTests.BaseTests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ProductsPage;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTests extends BaseTests {

    @Test
    public void filterTestT_shirt() {
        driver.get("https://www.trendyol.com/erkek-t-shirt-x-g2-c73");

        HomePage homePage = new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.searchBrand("Bershka");
        productsPage.selectBrandFromSearch("Bershka");

        // URL'de bershka filtresi uygulandı mı?
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("bershka"));
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("bershka"),
                "URL'de Bershka filtresi uygulanmadı!");

        System.out.println("Filtre testi başarılı! URL: " + driver.getCurrentUrl());
    }

    @Test
    public void filterTestShirts() {
        driver.get("https://www.trendyol.com/erkek-gomlek-x-g2-c75");
        HomePage homePage=new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");
        ProductsPage productsPage=new ProductsPage(driver);
        productsPage.searchBrand("Mavi");
        productsPage.selectBrandFromSearch("Mavi");

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("mavi"));
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("mavi"),"Url Doğru değil!!!!");
        System.out.println("Filtre testi başarılı! URL: " + driver.getCurrentUrl());

    }
}