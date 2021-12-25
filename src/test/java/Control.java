import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Control {
    public static void controlSearch(WebDriverWait wait, WebDriver driver) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("imdbHeader-navDrawerOpen--desktop"))).click();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Oscars"))).click();
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("1929"))).click();
                    try {
                        Thread.sleep(5000);
                        WebElement filmName = driver.findElement(By.linkText("Şarlo Sirkte"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", filmName);
                        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Şarlo Sirkte"))).click();
                        try {
                            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Director")));
                            WebElement director = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/name/nm0000122/?ref_=tt_ov_dr']")));
                            String directorName = director.getText();
                            System.out.println("Director: " + directorName);
                            try {
                                //wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Writer")));
                                WebElement writer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/name/nm0000122/?ref_=tt_ov_wr']")));
                                String writerName = writer.getText();
                                System.out.println("Writer: " + writerName);
                                try {
                                    WebElement stars = driver.findElement(By.xpath("//*[@id='__next']/main/div[2]/section[1]/section/div[3]/section/section/div[3]/div[2]/div[1]/div[3]/ul/li[3]/div/ul"));
                                    List<WebElement> starsList = stars.findElements(By.tagName("li"));
                                    for (WebElement li : starsList) {
                                        System.out.println(li.getText());
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
