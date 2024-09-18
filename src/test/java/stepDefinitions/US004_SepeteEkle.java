package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.DriverParallel;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class US004_SepeteEkle extends ExceldekiVeriler {

    int sonucAdedi;
    int count;
    WebElement urun;
    List<Integer> siraSayilari;
    int urunSayisi;
    String eklenenUrunIsim;
    WebElement xButton;
    String xpath;
    WebElement element;

    public US004_SepeteEkle() throws IOException {
    }

    @Then("Kategoride {int}'dan fazla urun bulundugunu dogrula")
    public void kategorideDanFazlaUrunBulundugunuDogrula(int sonucExp) {
        sonucAdedi = Integer.parseInt(to.sonuc.getText().replaceAll("\\D", ""));
    }


    @Then("Rastgele verilen {int}. X sirasinda bulunan urunun listede oldugunu dogrula")
    public void rastgeleVerilenXSirasindaBulunanUrununListedeOldugunuDogrula(int sayi) {

        if (siraSayilari == null) { // Eğer liste daha önce oluşturulmadıysa
            count = Math.min(3, sonucAdedi); // Eklenmesi gereken ürün sayısı, toplam ürün sayısını aşmamalı
            siraSayilari = ReusableMethods.rastgeleSayilar(1, sonucAdedi, count); // Listeyi oluştur
        }

        int indeks = siraSayilari.get(sayi - 1);

        String urunXpath = "//section[2]/div[2]/div/div/ul/li[" + indeks + "]/div/a";
        urun = DriverParallel.getDriver().findElement(By.xpath(urunXpath));

        ReusableMethods.scroll(urun);

        wait.until(ExpectedConditions.visibilityOf(urun));
        softAssert.assertTrue(urun.isDisplayed());

    }


    @Then("Urune tikla")
    public void uruneTikla() {

        ReusableMethods.click(urun);

    }

    @Then("Sayfa url'sinin {string} icerdigini test et")
    public void sayfaUrlSininIcerdiginiTestEt(String expUrl) {

        String actUrl = DriverParallel.getDriver().getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOf(to.addToCart));
        Assert.assertTrue(actUrl.contains(expUrl));
    }

    @And("Product Added To Cart! Yazisinin ciktigini dogrula")
    public void productAddedToCartYazisininCiktiginiDogrula() throws IOException {

        element = elementBul("addedMessage");
        softAssert.assertTrue(element.isDisplayed());
    }


    @Then("Bir onceki sayfaya don")
    public void birOncekiSayfayaDon() {

        DriverParallel.getDriver().navigate().back();
    }


    @Then("Listedeki ilk urunun uzerine git")
    public void listedekiIlkUrununUzerineGit() {

        String xpath = "//section[2]/div[2]/div/div/ul/li[1]/div/a";
        urun = DriverParallel.getDriver().findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.visibilityOf(urun));
        softAssert.assertTrue(urun.isDisplayed());

        actions.moveToElement(urun).perform();

    }

    @And("Urun kutusunun uzerinde dururken gorunur olan Add to Cart butonunun tiklanabilirligini test et")
    public void urunKutusununUzerindeDururkenGorunurOlanAddToCartButonununTiklanabilirliginiTestEt() {

        String xpathFloatAdd = "//li[1]/div/div[2]/div[1]";
        WebElement floatButton = DriverParallel.getDriver().findElement(By.xpath(xpathFloatAdd));
        wait.until(ExpectedConditions.elementToBeClickable(floatButton));

        eklenenUrunIsim = DriverParallel.getDriver().findElement(By.xpath("//ul/li[1]/div/div[3]/div/a")).getText(); ////1. ürün i = 1 "//ul/li[" + i + "]/div/div[3]/div/a"
    }


    @Then("Your Cart butonunun sayisininin {int} oldugunu dogrula")
    public void yourCartButonununSayisinininOldugunuDogrula(int sayi) throws IOException {

        element = elementBul("cart");
        String sepetIkonundaGorunenUrunAdedi = element.getText();
        softAssert.assertEquals(sepetIkonundaGorunenUrunAdedi, sayi);

    }

    @Then("Sepette urun bulundugunu test et")
    public void sepetteUrunBulundugunuTestEt() {

        List<WebElement> sepetIcerigi = DriverParallel.getDriver().findElements(By.xpath("//div/div[3]//input"));
        urunSayisi = sepetIcerigi.size();

        softAssert.assertTrue(urunSayisi > 0, "Sepette ürün bulunmamaktadır.");
    }

    @Then("Sepetteki urunun isminin listede eklenen urunun ismi ile ayni oldugunu test et")
    public void sepettekiUrununIsmininListedeEklenenUrununIsmiIleAyniOldugunuTestEt() {

        String xpathName = "//div[1]/div[2]/a"; //1. ürün i = 1 "//div[" + i + "]/div[2]/a"
        WebElement urunIsim = DriverParallel.getDriver().findElement(By.xpath(xpathName));
        String sepettekiIsim = urunIsim.getText();

        softAssert.assertEquals(urunIsim, sepettekiIsim);
    }

    @Then("Urun kutusunun kosesindeki X butonunun tiklanabilirligini test et")
    public void urunKutusununKosesindekiXButonununTiklanabilirliginiTestEt() {

        String sepettekiIlkUrunxpath = "//*[@id='shop-listing']/div[1]/div[1]/div/div[2]/button"; //ilk ürün indeks 2 ile başlıyor "//*[@id='shop-listing']/div[1]/div[1]/div/div[" + i + "]/button"
        xButton = DriverParallel.getDriver().findElement(By.xpath(sepettekiIlkUrunxpath));
    }

    @Then("X butonuna tikla")
    public void xButonunaTikla() {

        ReusableMethods.click(xButton);
    }

    @Then("Wait for it... Yazisi kaybolana kadar bekle")
    public void waitForItYazisiKaybolanaKadarBekleInvisibleELement() throws IOException {

        String xpath = exceldeXPathBul("waitForIt");
        WebElement element = DriverParallel.getDriver().findElement(By.xpath(xpath));

        wait.until(ExpectedConditions.invisibilityOf(element));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    @Then("Arama kutusuna phone yaz ve Enter'a bas")
    public void aramaKutusunaPhoneYazVeEnterABas() throws IOException {

        element = elementBul("Aramakutusu");

        actions.click(element)
                .sendKeys("phone" + Keys.ENTER)
                .perform();
    }


    @Then("ilk urune tikla")
    public void ilkUruneTikla() {

        WebElement urun = DriverParallel.getDriver().findElement(By.xpath("//div[2]/ul/li[1]/div/a"));
        softAssert.assertTrue(urun.isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(urun)).click();
    }

    @Then("Miktar kutusunda + butonuna {int} kere bas")
    public void miktarKutusundaButonunaKereBas(int arg0) throws IOException {

        xpath = exceldeXPathBul("+butonu");
        element = DriverParallel.getDriver().findElement(By.xpath(xpath));
        ReusableMethods.click(element);
        ReusableMethods.click(element);
    }

    @Then("Urun miktarinin {int} oldugunu test et")
    public void urunMiktarininOldugunuTestEt(int expSayi) {

        softAssert.assertEquals(urunSayisi, expSayi);
    }

    @And("Name, address, address2, city, postcode, ülke ve sehir bilgilerini doldur")
    public void nameAddressAddressCityPostcodeÜlkeVeSehirBilgileriniDoldur() throws IOException {

        element = elementBul("isimKutusu");

        wait.until(ExpectedConditions.visibilityOf(element));
        ReusableMethods.click(element);

        actions.sendKeys(firstName + Keys.TAB)
                .sendKeys(email + Keys.TAB)
                .sendKeys(phone + Keys.TAB)
                .sendKeys(address + Keys.TAB)
                .sendKeys(address2 + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(country + Keys.TAB)
                .pause(Duration.ofSeconds(1))
                .sendKeys(state + Keys.TAB)
                .sendKeys(city + Keys.TAB)
                .sendKeys(postcode)
                .perform();
    }

    @Then("{string}nde adres bulundugunu test et")
    public void listesindeAdresBulundugunuTestEt(String bolum) {

        bolum = bolum.toLowerCase();

        if (bolum.contains("billing")) {

            xpath = "//div[1]/div/div[2]/div/div/div[2]/label";
        } else {
            xpath = "//div[2]/div/div[2]/div/div/div[2]/label";
        }

        element = DriverParallel.getDriver().findElement(By.xpath(xpath));

        wait.until(ExpectedConditions.visibilityOf(element));
        softAssert.assertTrue(element.isEnabled());
    }

    @Then("{string}nden adres sec")
    public void listesindeAdresSec(String bolum) {

        bolum = bolum.toLowerCase();

        if (bolum.contains("billing")) {

            xpath = "//div[1]/div/div[2]/div/div/div[2]/label";
            element = DriverParallel.getDriver().findElement(By.xpath(xpath));

            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } else {
            xpath = "//div[1]/div/div[2]/div/div/div[2]/label";
            element = DriverParallel.getDriver().findElement(By.xpath(xpath));

            wait.until(ExpectedConditions.elementToBeClickable(element));
            ReusableMethods.click(element); // Billinge tekrar tikla

            xpath = "//div[2]/div/div[2]/div/div/div[2]/label";
            element = DriverParallel.getDriver().findElement(By.xpath(xpath));

            wait.until(ExpectedConditions.elementToBeClickable(element));
            ReusableMethods.click(element);
        }
    }

    @Then("Delivery address same as billing address kutucuguna tikla")
    public void deliveryAddressSameAsBillingAddressKutucugunaTikla() throws IOException {

        element = elementBul("AyniAddressSec");
        ReusableMethods.click(element);
    }

    @Then("Delivery Address bolumunun gorunur olmadigini test et")
    public void deliveryAddressBolumununGorunurOlmadiginiTestEt() throws IOException {

        element = elementBul("AddAddress2");
        softAssert.assertFalse(element.isDisplayed());
    }

    @Then("Delivery address same as billing address kutucugunu unchecked yap")
    public void deliveryAddressSameAsBillingAddressKutucugunuUncheckedYap() throws IOException {

        element = elementBul("AyniAddressSec");
        ReusableMethods.click(element);
    }

    @Then("Shipping Methods listesinden kargo sec")
    public void shippingMethodsListesindenKargoSec() throws IOException {

        element = elementBul("freeShippingSec");
        ReusableMethods.click(element);
    }

    @Then("Terms and Conditions boxini checkle")
    public void termsAndConditionsBoxiniCheckle() throws IOException {

        element = elementBul("policySec");
        ReusableMethods.click(element);
    }


}

    

    
