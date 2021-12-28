import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MenuControl {
    public static void controlSearch(WebDriverWait wait, WebDriver driver, String filmN, String star) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("imdbHeader-navDrawerOpen--desktop"))).click();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Oscars"))).click();
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("1929"))).click();
                    try {
                        Thread.sleep(5000);
                        WebElement filmName = driver.findElement(By.xpath("//div/a[contains(text(), '"+filmN+"')]"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", filmName);
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/a[contains(text(), '"+filmN+"')]"))).click();
                        try {
                            List<WebElement> directors = driver.findElements(By.xpath("//div[@data-testid='title-pc-wide-screen']/ul/li/div/ul/li/a"));
                            String directorName = directors.get(1).getText();
                            System.out.println("Director: " + directorName);
                            try {
                                List<WebElement> writers = driver.findElements(By.xpath("//div[@data-testid='title-pc-wide-screen']/ul/li/div/ul/li/a"));
                                String writerName = writers.get(2).getText();
                                System.out.println("Writer: " + writerName);
                                try {
                                    WebElement stars = driver.findElement(By.xpath("//div[@class='ipc-metadata-list-item__content-container']/ul"));
                                    List<WebElement> starList = stars.findElements(By.tagName("li"));
                                    for (WebElement li : starList) {
                                        String starsName = li.getText();
                                        System.out.println("Stars: " + starsName);
                                    }
                                    try {
                                        wait.until(ExpectedConditions.elementToBeClickable(By.id("home_img_holder"))).click();
                                        SearchBarControl.searchBC(wait,driver,directorName, writerName,filmN);
                                    } catch (Exception e) {
                                        Assert.fail("Home Butonuna Erişim Sağlanamadı");
                                    }
                                } catch (Exception e) {
                                    Assert.fail("Stars bulunumadı");
                                }
                            } catch (Exception e) {
                                Assert.fail("Writer Bilgisi Bulunamadı");
                            }
                        } catch (Exception e) {
                            Assert.fail("Director Bulunamadı");
                        }
                    } catch (Exception e) {
                        Assert.fail("The Circus filmine Erişilemedi");
                    }
                } catch (Exception e) {
                    Assert.fail("1929 Yılına Erişilemedi");
                }
            } catch (Exception e) {
                Assert.fail("Oscars Kelimesine Erişilemedi");
            }
        } catch (Exception e) {
            Assert.fail("Menu Butonuna Erişim Sağlanamadı");
        }
    }
}
