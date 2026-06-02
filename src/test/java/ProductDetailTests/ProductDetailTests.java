package ProductDetailTests;

import BaseTests.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetailTests extends BaseTests {
    @Test
    public void productDetailTest(){
        HomePage homePage=new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");
        homePage.searchProduct("Sneaker");
        WebElement ilkUrun = driver.findElement(By.cssSelector("a[data-testid='product-card']"));
        String href = ilkUrun.getAttribute("href");
        Assertions.assertNotNull(href);
        driver.get(href);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.closeOnboardingIfPresent();
        productDetailPage.addToCart();
        assertEquals("1", productDetailPage.getBasketCount(),
                "Sepette 1 ürün olmalı, bulunan: " + productDetailPage.getBasketCount());
        System.out.println("Sepete ekleme başarılı! Sepet: " + productDetailPage.getBasketCount());
    }

    @Test
    public void addToFavouriteTest(){
        HomePage homePage=new HomePage(driver);
        homePage.acceptIfCookiesPresent();
        homePage.selectGender("Erkek");
        homePage.searchProduct("Sneaker");
        WebElement ilkUrun = driver.findElement(By.cssSelector("a[data-testid='product-card']"));
        String href = ilkUrun.getAttribute("href");
        driver.get(href);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.closeOnboardingIfPresent();   // ← önce turu kapat
        productDetailPage.addToFavourite();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("giris"));

        String url = driver.getCurrentUrl().toLowerCase();
        assertTrue(url.contains("giris") || url.contains("uyelik"),
                "Favoriye basınca login'e yönlendirilmedi! URL: " + driver.getCurrentUrl());

        System.out.println("Doğru: Giriş olmadan favori login'e yönlendirdi. URL: " + driver.getCurrentUrl());
    }
}


