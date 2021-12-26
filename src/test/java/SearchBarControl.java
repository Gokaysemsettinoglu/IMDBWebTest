import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class SearchBarControl {
    public static void searchBC(WebDriverWait wait, WebDriver driver, String directorName, String writerName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("suggestion-search"))).sendKeys("The Circus");
            try {
                Thread.sleep(3000);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/title/tt0018773?ref_=nv_sr_srsg_3']"))).click();
                try {
                    WebElement director = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/name/nm0000122/?ref_=tt_ov_dr']")));
                    String directorNameS = director.getText();
                    if (directorName.contains(directorNameS)) {
                        System.out.println("Director Bilgisi Kayıt Edilen ile Aynı");
                    }
                    try {
                        WebElement writer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/name/nm0000122/?ref_=tt_ov_wr']")));
                        String writerNameS = writer.getText();
                        if (writerName.contains(writerNameS)) {
                            System.out.println("Writer Bilgisi Kayıt Edilen ile Aynı");
                        }
                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(By.className("ipc-title__text"))).click();
                            int brokenimgCount = 0;
                            try {
                                List<WebElement> image_list = driver.findElements(By.tagName("img"));
                                System.out.println("Bu sayfada " + image_list.size() + " tane images bulundu.");
                                for (WebElement img : image_list) {
                                    if (img != null) {
                                        HttpClient client = HttpClientBuilder.create().build();
                                        HttpGet request = new HttpGet(img.getAttribute("src"));
                                        HttpResponse response = client.execute(request);
                                        /* For valid images, the HttpStatus will be 200 */
                                        if (response.getStatusLine().getStatusCode() != 200) {
                                            System.out.println(img.getAttribute("outerHTML") + " kırık images.");
                                            brokenimgCount++;
                                        }
                                    }
                                }
                                wait.until(ExpectedConditions.elementToBeClickable(By.className("prevnext"))).click();
                                int brokenimgCountNext = 0;
                                try {
                                    List<WebElement> image_listNext = driver.findElements(By.tagName("img"));
                                    System.out.println("Bu sayfada " + image_listNext.size() + " tane images bulundu.");
                                    for (WebElement imgN : image_listNext) {
                                        if (imgN != null) {
                                            HttpClient client = HttpClientBuilder.create().build();
                                            HttpGet request = new HttpGet(imgN.getAttribute("src"));
                                            HttpResponse response = client.execute(request);
                                            if (response.getStatusLine().getStatusCode() != 200) {
                                                System.out.println(imgN.getAttribute("outerHTML") + " kırık images.");
                                                brokenimgCountNext++;
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println(brokenimgCountNext + " tane kırık images var.");
                                    Assert.fail("Kırık Link Bulundu");
                                }
                            } catch (Exception e) {
                                System.out.println(brokenimgCount + " tane kırık images var.");
                                Assert.fail("Kırık Link Bulundu");
                            }
                            System.out.println("Taranan İmageslerde Kırık Lİnk Bulunamadı");
                        } catch (Exception e) {
                            Assert.fail("Photos Bulunamadı");
                        }
                    } catch (Exception e) {
                        Assert.fail("Writer Bilgisi Aynı Değil");
                    }
                } catch (Exception e) {
                    Assert.fail("Director Bilgisi Aynı Değil");
                }
            } catch (Exception e) {
                Assert.fail("The Circus Searclistde Bulunamadı");
            }
        } catch (Exception e) {
            Assert.fail("SearchBar'a Erişim Sağlanamadı");
        }
    }
}
